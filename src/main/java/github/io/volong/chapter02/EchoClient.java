package github.io.volong.chapter02;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {

    private final String host;
    private final int port;
    
    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    
    public void start() throws InterruptedException {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(nioEventLoopGroup)
                 .channel(NioSocketChannel.class)
                 .remoteAddress(new InetSocketAddress(host, port))
                 .handler(new ChannelInitializer<Channel>() {

                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new EchoClientHandler());
                    }
                });
        
        ChannelFuture future = bootstrap.connect().sync();
        future.channel().closeFuture().sync();
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        new EchoClient("127.0.0.1", 3000).start();
        
    }
}
