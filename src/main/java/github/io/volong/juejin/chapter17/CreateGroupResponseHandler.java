package github.io.volong.juejin.chapter17;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        System.out.println("群聊创建成功，ID 为: " + createGroupResponsePacket.getGroupId());
        System.out.println("群里面有: " + createGroupResponsePacket.getUserNameList());
    }
}
