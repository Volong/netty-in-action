package github.io.volong.juejin.chapter17;

import github.io.volong.juejin.chapter17.packet.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class Spliter extends LengthFieldBasedFrameDecoder {

    private static final int LENGTH_FIELD_OFFSET = 7;
    
    private static final int LENGTH_FIELD_LENGTH = 4;
    
    public Spliter() {
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }
    
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        int anInt = in.getInt(in.readerIndex());

        if (anInt != PacketCodeC.MAGIC_NUMBER) {
            System.out.println("魔数不相同，连接关闭");

            ctx.channel().close();
            return null;
        }
        
        return super.decode(ctx, in);
    }
}
