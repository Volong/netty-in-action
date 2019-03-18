package github.io.volong;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.TimeUnit;

public class NettyClient {

    public static void main(String[] args) {

        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(workerGroup)
                 .channel(NioSocketChannel.class) // 指定 IO 线程为 NIO

                 .handler(new ChannelInitializer<SocketChannel>() {

                     @Override
                     protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new FirstClientHandler());
                     }
                 });

        connect(bootstrap, "127.0.0.1", 8000, 5);

    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("连接成功");
                } else if (retry == 0) {
                    System.out.println("重连次数使用完成，放弃连接");
                } else {
                    int count = (5 - retry) + 1; // 第几次重连

                    System.out.println("连接失败，准备第 " + count + " 次连接");

                    bootstrap.config() // 返回的是 BootstrapConfig
                             .group() // 返回的是 workerGroup
                             .schedule(() -> connect(bootstrap, host, port, retry - 1),
                                     count << 1,
                                     TimeUnit.SECONDS);
                }
            }
        });
    }
}
