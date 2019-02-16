package github.io.volong.juejin.chapter18.group.quit;

import github.io.volong.juejin.chapter18.command.Command;
import github.io.volong.juejin.chapter18.packet.Packet;

public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
