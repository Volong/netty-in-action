package github.io.volong.juejin.chapter16;

import github.io.volong.juejin.chapter08.Command;
import github.io.volong.juejin.chapter08.Packet;

public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    public MessageResponsePacket() {
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }

    public String getFromUserId() {
        return this.fromUserId;
    }

    public String getFromUserName() {
        return this.fromUserName;
    }

    public String getMessage() {
        return this.message;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MessageResponsePacket)) return false;
        final MessageResponsePacket other = (MessageResponsePacket) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$fromUserId = this.getFromUserId();
        final Object other$fromUserId = other.getFromUserId();
        if (this$fromUserId == null ? other$fromUserId != null : !this$fromUserId.equals(other$fromUserId))
            return false;
        final Object this$fromUserName = this.getFromUserName();
        final Object other$fromUserName = other.getFromUserName();
        if (this$fromUserName == null ? other$fromUserName != null : !this$fromUserName.equals(other$fromUserName))
            return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MessageResponsePacket;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $fromUserId = this.getFromUserId();
        result = result * PRIME + ($fromUserId == null ? 43 : $fromUserId.hashCode());
        final Object $fromUserName = this.getFromUserName();
        result = result * PRIME + ($fromUserName == null ? 43 : $fromUserName.hashCode());
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "MessageResponsePacket(fromUserId=" + this.getFromUserId() + ", fromUserName=" + this.getFromUserName() + ", message=" + this.getMessage() + ")";
    }
}
