package github.io.volong;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    /**
     *
     * 客户端连接建立成功之后被调用
     *
     * @param ctx
     * @throws Exception
     */

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        // 1. 先获取一个 buffer
        ByteBuf buffer = ctx.alloc().buffer();

        byte[] bytes = "hello world".getBytes("UTF-8");

        // 2. 将数据填充到 buffer 中
        buffer.writeBytes(bytes);

        System.out.println("data is already send to server");
        // 将 buffer 发送给服务端
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buffer = (ByteBuf) msg;

        System.out.println("client receive reply from server: " + buffer.toString(Charset.forName("UTF-8")));
    }
}
