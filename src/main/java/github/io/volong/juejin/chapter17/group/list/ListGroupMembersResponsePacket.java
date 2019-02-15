package github.io.volong.juejin.chapter17.group.list;

import github.io.volong.juejin.chapter17.command.Command;
import github.io.volong.juejin.chapter17.packet.Packet;
import github.io.volong.juejin.session.Session;

import java.util.List;

public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }
}
