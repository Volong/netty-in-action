package github.io.volong.juejin.chapter12;

import java.util.Date;

import github.io.volong.juejin.chapter10.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.println(new Date() + " : 收到服务端消息: " + msg.getMessage());
    }

}
