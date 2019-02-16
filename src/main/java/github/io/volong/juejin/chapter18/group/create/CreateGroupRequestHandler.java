package github.io.volong.juejin.chapter18.group.create;

import github.io.volong.juejin.util.IDUtil;
import github.io.volong.juejin.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {

        System.out.println("接收到创建群聊消息");

        List<String> userIdList = createGroupRequestPacket.getUserIdList();

        ArrayList<String> userNameList = new ArrayList<>();

        // 创建一个 channel 分组
        DefaultChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        // 得到跟 userid 得到 username
        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);

            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        // 群聊的响应结果
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(IDUtil.randomId());
        createGroupResponsePacket.setUserNameList(userNameList);


        SessionUtil.bindChannelGroup(createGroupResponsePacket.getGroupId(), channelGroup);

        // 给每个客户端发送响应通知
        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.println("群聊创建成功，群聊 ID 为: " + createGroupResponsePacket.getGroupId());
        System.out.println("群里面有: " + createGroupResponsePacket.getUserNameList());
    }
}
