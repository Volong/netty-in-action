package github.io.volong.juejin.chapter13;

import github.io.volong.juejin.chapter10.MessageRequestPacket;
import github.io.volong.juejin.chapter12.LoginResponseHandler;
import github.io.volong.juejin.chapter12.MessageResponseHandler;
import github.io.volong.juejin.chapter12.PacketDecoder;
import github.io.volong.juejin.chapter12.PacketEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyClient {

    public static void main(String[] args) {
        
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        
        bootstrap.group(group)
                 .channel(NioSocketChannel.class)
                 .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
//                        ch.pipeline().addLast(new FirstClientHandler());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                }).connect("127.0.0.1", 8000).addListener(new GenericFutureListener<Future<? super Void>>() {

                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            new Thread(() -> {
                                for (int i = 0; i < 1000; i++) {
                                    MessageRequestPacket packet = new MessageRequestPacket();
                                    packet.setMessage("volong is learning netty in action");
                                    Channel channel = ((ChannelFuture) future).channel();
                                    channel.writeAndFlush(packet);
                                }
                            }).start();
                        }
                    }
                });
    }
}
