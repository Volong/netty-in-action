package github.io.volong.juejin.chapter13;

import github.io.volong.juejin.chapter12.LoginRequestHandler;
import github.io.volong.juejin.chapter12.MessageRequestHandler;
import github.io.volong.juejin.chapter12.PacketDecoder;
import github.io.volong.juejin.chapter12.PacketEncoder;
import github.io.volong.juejin.chapter14.LifeCyCleTestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyServer {

    public static void main(String[] args) {
        
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        
        serverBootstrap.group(bossGroup, workerGroup)
                       .channel(NioServerSocketChannel.class)
                       .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                            // 单独使用这一个会看到粘包半包的现象
//                            ch.pipeline().addLast(new FirstServerHandler());
                            ch.pipeline().addLast(new LifeCyCleTestHandler());
                            ch.pipeline().addLast(new Spliter());
                            ch.pipeline().addLast(new PacketDecoder());
                            ch.pipeline().addLast(new LoginRequestHandler());
                            ch.pipeline().addLast(new MessageRequestHandler());
                            ch.pipeline().addLast(new PacketEncoder());
                        }
                    }).bind(8000);
    }
}
