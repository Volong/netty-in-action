package github.io.volong.juejin.chapter16;

import github.io.volong.juejin.chapter08.Command;
import github.io.volong.juejin.chapter08.Packet;
import lombok.Data;

@Data
public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;


    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
