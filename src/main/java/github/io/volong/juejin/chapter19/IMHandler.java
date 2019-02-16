package github.io.volong.juejin.chapter19;

import github.io.volong.juejin.chapter19.command.Command;
import github.io.volong.juejin.chapter19.group.create.CreateGroupRequestHandler;
import github.io.volong.juejin.chapter19.group.join.JoinGroupRequestHandler;
import github.io.volong.juejin.chapter19.group.list.ListGroupMembersRequestHandler;
import github.io.volong.juejin.chapter19.group.quit.QuitGroupRequestHandler;
import github.io.volong.juejin.chapter19.message.group.GroupMessageRequestHandler;
import github.io.volong.juejin.chapter19.message.MessageRequestHandler;
import github.io.volong.juejin.chapter19.packet.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    public IMHandler() {
        handlerMap = new HashMap<>();

        handlerMap.put(Command.MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestHandler.INSTANCE);
        handlerMap.put(Command.GROUP_MESSAGE_REQUEST, GroupMessageRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {

        System.out.println("msg:" + msg);
        System.out.println("command:" + msg.getCommand());
        SimpleChannelInboundHandler<? extends Packet> simpleChannelInboundHandler = handlerMap.get(msg.getCommand());

        System.out.println("simpleChannelInboundHandler:" + simpleChannelInboundHandler);
        simpleChannelInboundHandler.channelRead(ctx, msg);
    }
}
