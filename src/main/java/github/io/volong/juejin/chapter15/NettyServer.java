package github.io.volong.juejin.chapter15;

import github.io.volong.juejin.chapter12.MessageRequestHandler;
import github.io.volong.juejin.chapter12.PacketDecoder;
import github.io.volong.juejin.chapter12.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer {

    public static void main(String[] args) {

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        serverBootstrap.group(group)
                       .channel(NioServerSocketChannel.class)
                       .childHandler(new ChannelInitializer<NioSocketChannel>() {
                           @Override
                           protected void initChannel(NioSocketChannel ch) throws Exception {
                               ch.pipeline().addLast(new PacketDecoder());
                               ch.pipeline().addLast(new LoginRequestHandler());
                               ch.pipeline().addLast(new AuthHandler());
                               ch.pipeline().addLast(new MessageRequestHandler());
                               ch.pipeline().addLast(new PacketEncoder());

                           }
                       });



    }
}