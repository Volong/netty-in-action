package github.io.volong.juejin.chapter09;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public static void main(String[] args) {
        
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        
        Bootstrap bootstrap = new Bootstrap();
        
        bootstrap.group(nioEventLoopGroup)
                 .channel(NioSocketChannel.class)
                 .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ClientHandler());
                    }
                }).connect("127.0.0.1", 8000);
        
    }
}
