package github.io.volong.juejin.chapter20;

import github.io.volong.juejin.chapter20.command.Command;
import github.io.volong.juejin.chapter20.packet.Packet;

public class HeartBeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}
