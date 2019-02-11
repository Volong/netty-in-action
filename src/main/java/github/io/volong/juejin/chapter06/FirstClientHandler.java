package github.io.volong.juejin.chapter06;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端写出数据");
        
        ByteBuf buffer = getByteBuf(ctx);
        
        ctx.channel().writeAndFlush(buffer);
    }
    
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + ": 客户端读到数据 -> " + byteBuf.toString(CharsetUtil.UTF_8));
    }
    
    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();
        
        byte[] bytes = "Volong".getBytes(CharsetUtil.UTF_8);
        
        buffer.writeBytes(bytes);
        
        return buffer;
    }
}
