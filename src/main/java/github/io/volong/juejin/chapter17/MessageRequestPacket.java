package github.io.volong.juejin.chapter17;

public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;

    public MessageRequestPacket() {

    }

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    public String getToUserId() {
        return this.toUserId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MessageRequestPacket)) return false;
        final MessageRequestPacket other = (MessageRequestPacket) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$toUserId = this.getToUserId();
        final Object other$toUserId = other.getToUserId();
        if (this$toUserId == null ? other$toUserId != null : !this$toUserId.equals(other$toUserId)) return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MessageRequestPacket;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $toUserId = this.getToUserId();
        result = result * PRIME + ($toUserId == null ? 43 : $toUserId.hashCode());
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "MessageRequestPacket(toUserId=" + this.getToUserId() + ", message=" + this.getMessage() + ")";
    }
}
