package github.io.volong.juejin.chapter17.packet;

import github.io.volong.juejin.chapter17.group.list.ListGroupMembersRequestPacket;
import github.io.volong.juejin.chapter17.group.list.ListGroupMembersResponsePacket;
import github.io.volong.juejin.chapter17.group.quit.QuitGroupRequestPacket;
import github.io.volong.juejin.chapter17.group.quit.QuitGroupResponsePacket;
import github.io.volong.juejin.chapter17.serializer.JSONSerializer;
import github.io.volong.juejin.chapter17.serializer.Serializer;
import github.io.volong.juejin.chapter17.command.Command;
import github.io.volong.juejin.chapter17.group.join.JoinGroupRequestPacket;
import github.io.volong.juejin.chapter17.group.join.JoinGroupResponsePacket;
import github.io.volong.juejin.chapter17.group.create.CreateGroupRequestPacket;
import github.io.volong.juejin.chapter17.group.create.CreateGroupResponsePacket;
import github.io.volong.juejin.chapter17.login.LoginRequestPacket;
import github.io.volong.juejin.chapter17.login.LoginResponsePacket;
import github.io.volong.juejin.chapter17.message.MessageRequestPacket;
import github.io.volong.juejin.chapter17.message.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import sun.nio.cs.ext.PCK;

import java.util.HashMap;
import java.util.Map;

public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(Command.CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(Command.JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(Command.QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        packetTypeMap.put(Command.LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }
    
    /**
     * 编码
     * 
     * @param byteBufAllocator 当前连接相关的 ByteBuf 分配器
     * @param packet 
     * @return
     */
    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        
        // 2. 序列化
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        
        // 3. 编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        
        return byteBuf;
    }
    
    public Packet decode(ByteBuf byteBuf) {
        
        // 跳过魔数
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);
        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        
        return null;
    }
    
    
    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }

    public void encode(ByteBuf byteBuf, Packet packet) {
        
        // 2. 序列化
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        
        // 3. 编码
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        
    }
}
