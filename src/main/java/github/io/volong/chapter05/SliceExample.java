package github.io.volong.chapter05;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class SliceExample {

    public static void main(String[] args) {
        Charset utf8 = CharsetUtil.UTF_8;
        ByteBuf copiedBuffer = Unpooled.copiedBuffer("Netty in Action rocks", utf8);
        
        // 共用同一份内存地址
        ByteBuf slice = copiedBuffer.slice(0, 15);
        
        // Netty in Action
        System.out.println(slice.toString(utf8));
        
        copiedBuffer.setByte(0, 'J');
        
        // Jetty in Action
        System.out.println(slice.toString(utf8));
    }
}
