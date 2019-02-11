package github.io.volong.chapter05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class CopyExample {

    public static void main(String[] args) {
        
        Charset utf8 = CharsetUtil.UTF_8;
        ByteBuf copiedBuffer = Unpooled.copiedBuffer("Netty in Action rocks", utf8);
        
        // 拷贝一份副本
        ByteBuf copy = copiedBuffer.copy(0, 15);
        
        // Netty in Action
        System.out.println(copy.toString(utf8));
        
        copiedBuffer.setByte(0, 'J');
        
        // Netty in Action
        System.out.println(copy.toString(utf8));
    }
}
