package github.io.volong.juejin.chapter16;

import github.io.volong.juejin.chapter08.Command;
import github.io.volong.juejin.chapter08.Packet;
import lombok.Data;

@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
