package github.io.volong.juejin.chapter18.group.join;

import github.io.volong.juejin.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {

        // 获取客户端传过来的 groupid
        String groupId = joinGroupRequestPacket.getGroupId();

        System.out.println("接收到客户端的加群请求，groupid 为：" + groupId);


        // 根据 groupid 获取对应的 channel
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        channelGroup.add(ctx.channel());

        // 构造加群响应
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setSuccess(true);
        joinGroupResponsePacket.setGroupId(groupId);


        ctx.channel().writeAndFlush(joinGroupResponsePacket);

    }
}
