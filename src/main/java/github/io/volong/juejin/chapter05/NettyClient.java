package github.io.volong.juejin.chapter05;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    private static final int MAX_RETRY = 5;
    
    public static void main(String[] args) {
        
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        
        Bootstrap bootstrap = new Bootstrap();
        
        bootstrap.group(workerGroup) // 指定线程模型
                 .channel(NioSocketChannel.class) // 指定 IO 类型为
                 .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        
                    }
                });
        
        connect(bootstrap, "so.mama.cn", 800, MAX_RETRY);
    }
    
    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功");
            } else if (retry == 0) {
                System.out.println("重用次数用完，连接失败");
            } else {
                int order = MAX_RETRY - retry + 1;
                int delay = 1 << order;
                System.out.println(new Date() + ": 连接失败，第 " + order + " 次重连...");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }
}
