package github.io.volong.gitchat.chapter05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.atomic.AtomicInteger;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private final byte[] request;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public NettyClientHandler() {
        request = "hello server,im a clinet|".getBytes();
    }

    /**
     *
     * 当客户端与服务端连接建立完毕后被回调
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("--- client already connected ---");

        ByteBuf message = null;

        for (int i = 0; i < 10; i++) {
            message = Unpooled.buffer(request.length);
            message.writeBytes(request);
            ctx.writeAndFlush(message);
        }
    }

    /**
     *
     * 客户端接受 buffer 里面的数据就绪后被回调
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println(atomicInteger.getAndIncrement() + " receive from server:" + msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
