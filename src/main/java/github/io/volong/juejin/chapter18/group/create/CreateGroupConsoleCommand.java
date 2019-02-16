package github.io.volong.juejin.chapter18.group.create;

import github.io.volong.juejin.chapter18.command.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {

        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.println("拉人进群聊，输入 userid，userid 之间使用英文逗号隔开");

        String userIds = scanner.next();

        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));

        channel.writeAndFlush(createGroupRequestPacket);
    }
}
