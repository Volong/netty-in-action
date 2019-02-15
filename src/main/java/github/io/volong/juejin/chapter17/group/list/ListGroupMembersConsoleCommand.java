package github.io.volong.juejin.chapter17.group.list;

import github.io.volong.juejin.chapter17.command.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.Scanner;

public class ListGroupMembersConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {

        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();

        System.out.println("输入想获取成员列表的 groupId");

        String next = scanner.next();

        listGroupMembersRequestPacket.setGroupId(next);

        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
