package github.io.volong.juejin.chapter09;

import github.io.volong.juejin.chapter08.Command;
import github.io.volong.juejin.chapter08.Packet;
import lombok.Data;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;
    
    private String reason;
    
    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

    
}
