package github.io.volong.juejin.chapter20.group.join;

import github.io.volong.juejin.chapter20.packet.Packet;
import github.io.volong.juejin.chapter20.command.Command;

public class JoinGroupRequestPacket extends Packet {


    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}