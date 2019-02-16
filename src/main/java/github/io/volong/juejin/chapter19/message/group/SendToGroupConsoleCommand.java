package github.io.volong.juejin.chapter19.message.group;

import github.io.volong.juejin.chapter19.command.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("发送消息给某个群：");

        String toGroupId = scanner.next();
        String message = scanner.next();

        GroupMessageRequestPacket requestPacket = new GroupMessageRequestPacket(toGroupId, message);
        System.out.println("requestPacket:" + requestPacket);
        channel.writeAndFlush(requestPacket);
    }
}
