package github.io.volong.juejin.chapter19.message.group;

import github.io.volong.juejin.chapter19.command.Command;
import github.io.volong.juejin.chapter19.packet.Packet;

public class GroupMessageRequestPacket extends Packet {


    private String toGroupId;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }


    public GroupMessageRequestPacket() {

    }

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    public String getToGroupId() {
        return toGroupId;
    }

    public void setToGroupId(String toGroupId) {
        this.toGroupId = toGroupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
