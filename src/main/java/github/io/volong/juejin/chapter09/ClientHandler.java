package github.io.volong.juejin.chapter09;

import java.util.Date;
import java.util.UUID;

import github.io.volong.juejin.chapter08.LoginRequestPacket;
import github.io.volong.juejin.chapter08.Packet;
import github.io.volong.juejin.chapter08.PacketCodeC;
import github.io.volong.juejin.chapter10.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录");
        
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("volong");
        loginRequestPacket.setPassword("123");
        
        // 编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);
        
        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            
            if (loginResponsePacket.isSuccess()) {
                System.out.println("客户端登录成功");
            } else {
                System.out.println("客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ": 收到服务端消息: " + messageResponsePacket.getMessage());
        }
        
    }
}
