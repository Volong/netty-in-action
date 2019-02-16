package github.io.volong.juejin.chapter19.group.join;

import github.io.volong.juejin.chapter19.command.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 进群命令
 */

public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {

        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.println("输入 groupid，加入群聊：");

        joinGroupRequestPacket.setGroupId(scanner.next());

        channel.writeAndFlush(joinGroupRequestPacket);

    }
}
