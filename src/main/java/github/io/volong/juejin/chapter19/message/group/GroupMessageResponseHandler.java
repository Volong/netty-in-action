package github.io.volong.juejin.chapter19.message.group;

import github.io.volong.juejin.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        String fromGroupId = groupMessageResponsePacket.getFromGroupId();
        Session fromUser = groupMessageResponsePacket.getFromUser();

        System.out.println("收到群：" + fromGroupId + " 中的用户：" + fromUser + " 的消息：" + groupMessageResponsePacket.getMessage());
    }
}
