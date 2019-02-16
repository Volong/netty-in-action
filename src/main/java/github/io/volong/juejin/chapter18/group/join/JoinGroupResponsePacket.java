package github.io.volong.juejin.chapter18.group.join;

import github.io.volong.juejin.chapter18.packet.Packet;
import github.io.volong.juejin.chapter18.command.Command;

/**
 * 加群的响应类
 */
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
