package github.io.volong.chapter05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class BackingArray {

    
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(1024);
        
        if (buffer.hasArray()) { // 是否有支撑数组
            byte[] array = buffer.array();
            
            // 计算第一个字节的偏移量
            int offset = buffer.arrayOffset() + buffer.readerIndex();
            
            // 可读字节数
            int readableBytes = buffer.readableBytes();
        }
    }
}
