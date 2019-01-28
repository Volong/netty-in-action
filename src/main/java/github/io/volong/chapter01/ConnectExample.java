package github.io.volong.chapter01;


import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ConnectExample {

    private static final Channel NIO_SOCKET_CHANNEL = new NioSocketChannel();
    
    public static void connect() {
        Channel channel = NIO_SOCKET_CHANNEL;
        
        // 异步连接到远程节点
        ChannelFuture future = channel.connect(new InetSocketAddress("hostname", 3000));
        
        // 注册一个监听器，在操作完成时获得通知
        future.addListener(new ChannelFutureListener() {
            
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                
                if (future.isSuccess()) { // 如果操作成功
                    ByteBuf copiedBuffer = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                    // 将数据异步发送到远程节点
                    ChannelFuture writeAndFlush = future.channel().writeAndFlush(copiedBuffer);
                } else {
                    Throwable cause = future.cause();
                    cause.printStackTrace();
                }
            }
        });
    }
}
