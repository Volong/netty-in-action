package github.io.volong.juejin.chapter20.group.create;

import github.io.volong.juejin.chapter20.packet.Packet;
import github.io.volong.juejin.chapter20.command.Command;

import java.util.List;

public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }
}
