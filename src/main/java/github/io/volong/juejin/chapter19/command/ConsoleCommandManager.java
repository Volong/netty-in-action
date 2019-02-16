package github.io.volong.juejin.chapter19.command;

import github.io.volong.juejin.chapter19.group.create.CreateGroupConsoleCommand;
import github.io.volong.juejin.chapter19.group.join.JoinGroupConsoleCommand;
import github.io.volong.juejin.chapter19.group.list.ListGroupMembersConsoleCommand;
import github.io.volong.juejin.chapter19.group.quit.QuitGroupConsoleCommand;
import github.io.volong.juejin.chapter19.message.group.SendToGroupConsoleCommand;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", null);
        consoleCommandMap.put("logout", null);
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
        consoleCommandMap.put("sendToGroup", new SendToGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {

        String command = scanner.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.err.println("无法识别的命令，请重新输入");
        }


    }
}
