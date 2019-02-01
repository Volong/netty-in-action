package github.io.volong.juejin.chapter12;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer {

    public static void main(String[] args) {
        
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        
        serverBootstrap.group(bossGroup, workerGroup)
                       .channel(NioServerSocketChannel.class)
                       .childHandler(new ChannelInitializer<NioSocketChannel>() {

                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new PacketDecoder());
                            ch.pipeline().addLast(new LoginRequestHandler());
                            ch.pipeline().addLast(new MessageRequestHandler());
                            ch.pipeline().addLast(new PacketEncoder());
                        }
                    }).bind(8000);
    }
}
