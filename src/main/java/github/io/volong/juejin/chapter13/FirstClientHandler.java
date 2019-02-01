package github.io.volong.juejin.chapter13;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 1000; i++) {
            ctx.channel().writeAndFlush(getByteBuf(ctx));
        }
    }
    
    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "volong is learning netty in action".getBytes(CharsetUtil.UTF_8);
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        
        return buffer;
    }
}
