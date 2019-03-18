package github.io.volong;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    /**
     *
     * 接收到客户端发来的数据之后被回调
     *
     * @param ctx
     * @param msg
     *
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println("服务端读到的数据为：" + byteBuf.toString(Charset.forName("UTF-8")));

        ByteBuf buffer = ctx.alloc().buffer();

        byte[] bytes = "welcome come to netty".getBytes("UTF-8");

        buffer.writeBytes(bytes);

        System.out.println("server already reply client");
        ctx.channel().writeAndFlush(buffer);
    }
}
