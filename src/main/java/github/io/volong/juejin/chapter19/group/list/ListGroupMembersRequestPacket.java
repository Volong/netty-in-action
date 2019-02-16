package github.io.volong.juejin.chapter19.group.list;

import github.io.volong.juejin.chapter19.command.Command;
import github.io.volong.juejin.chapter19.packet.Packet;

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
