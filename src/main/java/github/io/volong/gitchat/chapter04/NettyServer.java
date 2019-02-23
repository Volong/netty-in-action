package github.io.volong.gitchat.chapter04;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer {

    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    public static void main(String[] args) throws InterruptedException {

        // 创建主从 reactor 线程池
        NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boosGroup, workerGroup) // 设置主从线程池
             .channel(NioServerSocketChannel.class) // 指定用于创建服务端 NIO 通道的 Class 对象
             .option(ChannelOption.SO_BACKLOG, 100)
             .handler(new LoggingHandler(LogLevel.INFO)) // 设置日志
             .childHandler(new ChannelInitializer<SocketChannel>() {

                 @Override
                 protected void initChannel(SocketChannel ch) throws Exception {
                     ChannelPipeline p = ch.pipeline();

                     // 因为 hello server,im a client 的长度为 24
                     p.addLast(new FixedLengthFrameDecoder(24));
                     p.addLast(new StringDecoder());
                     p.addLast(new NettyServerHandler());
                 }
             });

            // 启动服务器
            ChannelFuture f = b.bind(PORT).sync();
            System.out.println("--- server started ---");

            // 同步等待服务 socket 关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅关闭线程池组
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
