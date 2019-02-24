package github.io.volong.chapter02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {

    private final int port;
    
    public EchoServer(int port) {
        this.port = port;
    }
    
    public static void main(String[] args) throws InterruptedException {
        new EchoServer(3000).start();
    }
    
    public void start() throws InterruptedException {
        EchoServerHandler echoServerHandler = new EchoServerHandler();
        
        // 1.
        EventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        
        // 2.
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        
        serverBootstrap.group(nioEventLoopGroup)
                       .channel(NioServerSocketChannel.class) // 3. 
                       .localAddress(new InetSocketAddress(port)) // 4.
                       .childHandler(new ChannelInitializer<Channel>() { // 5.
                           
                           /*
                            * 当一个新的连接被接受时，一个新的子Channel将会被创建，
                            * 而 ChannelInitializer 将会把一个你的EchoServerHandler的实例添加到该Channel的ChannelPipeline中。
                            * 这个ChannelHandler将会收到有关入站消息的通知。
                            */
                           protected void initChannel(Channel ch) throws Exception {
                               ch.pipeline().addLast(echoServerHandler);
                           }
                    });
        
        ChannelFuture future = serverBootstrap.bind().sync(); // 6. 
        future.channel().closeFuture().sync(); // 7.
        
    }
}
