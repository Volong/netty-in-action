package github.io.volong.juejin.chapter17;

import github.io.volong.juejin.chapter17.group.join.JoinGroupRequestHandler;
import github.io.volong.juejin.chapter17.group.create.CreateGroupRequestHandler;
import github.io.volong.juejin.chapter17.group.list.ListGroupMembersRequestHandler;
import github.io.volong.juejin.chapter17.group.quit.QuitGroupRequestHandler;
import github.io.volong.juejin.chapter17.login.LoginRequestHandler;
import github.io.volong.juejin.chapter17.message.MessageRequestHandler;
import github.io.volong.juejin.chapter17.packet.PacketDecoder;
import github.io.volong.juejin.chapter17.packet.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

public class NettyServer {

    private static final int PORT = 8000;


    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boosGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            protected void initChannel(NioSocketChannel ch) {
                ch.pipeline().addLast(new Spliter());
                ch.pipeline().addLast(new PacketDecoder());
                ch.pipeline().addLast(new LoginRequestHandler());
                ch.pipeline().addLast(new AuthHandler());
                ch.pipeline().addLast(new MessageRequestHandler());
                ch.pipeline().addLast(new CreateGroupRequestHandler());
                ch.pipeline().addLast(new JoinGroupRequestHandler());
                ch.pipeline().addLast(new QuitGroupRequestHandler());
                ch.pipeline().addLast(new ListGroupMembersRequestHandler());


                ch.pipeline().addLast(new PacketEncoder());
            }
        });



        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
