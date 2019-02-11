package github.io.volong.juejin.chapter10;

import github.io.volong.juejin.chapter08.Command;
import github.io.volong.juejin.chapter08.Packet;

public class MessageResponsePacket extends Packet {

    private String message;

    public MessageResponsePacket() {
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MessageResponsePacket)) return false;
        final MessageResponsePacket other = (MessageResponsePacket) o;
        if (!other.canEqual((Object) this)) return false;
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
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        return result;
    }

    public String toString() {
        return "MessageResponsePacket(message=" + this.getMessage() + ")";
    }
}
