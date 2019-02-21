package github.io.volong.juejin.chapter20.group.list;

import github.io.volong.juejin.chapter20.command.Command;
import github.io.volong.juejin.chapter20.packet.Packet;

public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
