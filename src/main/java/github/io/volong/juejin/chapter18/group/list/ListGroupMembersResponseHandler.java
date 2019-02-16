package github.io.volong.juejin.chapter18.group.list;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket listGroupMembersResponsePacket) throws Exception {
        System.out.println("群：" + listGroupMembersResponsePacket.getGroupId() + " 中包括的人为：" + listGroupMembersResponsePacket.getSessionList());
    }
}
