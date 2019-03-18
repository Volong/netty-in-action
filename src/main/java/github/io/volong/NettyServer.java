package github.io.volong;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {

    public static void main(String[] args) {

        // 监听端口，专门负责新建连接
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // 负责每条连接的数据读写
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workerGroup)
                       // 指定 IO 模型
                       .channel(NioServerSocketChannel.class)
                       // 处理新连接数据的读写处理逻辑
                       .childHandler(new ChannelInitializer<NioSocketChannel>() {

                           @Override
                           protected void initChannel(NioSocketChannel ch) throws Exception {

                           }
                       });


        bind(serverBootstrap, 8000);
    }

    /**
     *
     * 递增绑定端口号，直到绑定为止
     *
     * @param serverBootstrap
     * @param port
     */
    private static void bind(ServerBootstrap serverBootstrap, int port) {

        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {

            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口绑定成功");
                    System.out.println("绑定的端口为：" + port);
                } else {
                    System.out.println("端口绑定失败");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}
