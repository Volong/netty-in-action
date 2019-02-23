package github.io.volong.gitchat.chapter04;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.atomic.AtomicInteger;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     *
     * 当服务端监听到客户端连接，并且完成三次握手后回调
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--- accepted client ---");
        ctx.fireChannelActive();
    }

    /**
     *
     * 当服务端收到客户端发来的数据时被回调
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println(atomicInteger.getAndIncrement() + " receive client info: " +  msg);

        String sendContent = "hello client, im server";

        ByteBuf seneMsg = Unpooled.buffer(sendContent.length());

        seneMsg.writeBytes(sendContent.getBytes());

        ctx.writeAndFlush(seneMsg);

        System.out.println("send info to client: " + sendContent);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
