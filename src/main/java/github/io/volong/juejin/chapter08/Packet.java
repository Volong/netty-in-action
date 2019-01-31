package github.io.volong.juejin.chapter08;

import lombok.Data;

@Data
public abstract class Packet {

    private Byte version = 1;
    
    public abstract Byte getCommand();
    
}
