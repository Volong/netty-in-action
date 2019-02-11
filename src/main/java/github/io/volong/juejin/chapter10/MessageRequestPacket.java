package github.io.volong.juejin.chapter10;

import github.io.volong.juejin.chapter08.Command;
import github.io.volong.juejin.chapter08.Packet;

public class MessageRequestPacket extends Packet {

    private String message;
    
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
