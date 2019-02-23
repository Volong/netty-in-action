package github.io.volong.gitchat.chapter05;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class NioClient {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    static final ByteBuf delimiter = Unpooled.copiedBuffer("|".getBytes());

    public static void main(String[] args) throws InterruptedException {


        // 创建线程池，用来处理 io 请求，默认线程个数为 cpu * 2
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {

            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class) // 指定创建客户端 NIO 通道的 Class 对象
             .option(ChannelOption.TCP_NODELAY, true)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 protected void initChannel(SocketChannel ch) throws Exception {

                     ChannelPipeline p = ch.pipeline();

                     p.addLast(new DelimiterBasedFrameDecoder(1000, delimiter));
                     p.addLast(new StringDecoder());
                     p.addLast(new NettyClientHandler());
                 }
             });

            // 启动连接
            ChannelFuture f = b.connect(HOST, PORT).sync();

            // 同步等待连接断开
            f.channel().closeFuture().sync();
        } finally {
            // 优雅的关闭线程池
            group.shutdownGracefully();
        }


    }
}
