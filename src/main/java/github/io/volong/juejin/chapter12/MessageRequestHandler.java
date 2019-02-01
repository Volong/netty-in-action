package github.io.volong.juejin.chapter12;

import java.util.Date;

import github.io.volong.juejin.chapter10.MessageRequestPacket;
import github.io.volong.juejin.chapter10.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(new Date() + " : 收到客户端消息: " + msg.getMessage());
        
        messageResponsePacket.setMessage("收到！！！");
        ctx.channel().writeAndFlush(messageResponsePacket);
    }

}
