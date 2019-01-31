package github.io.volong.juejin.chapter09;

import java.util.Date;

import github.io.volong.juejin.chapter08.LoginRequestPacket;
import github.io.volong.juejin.chapter08.Packet;
import github.io.volong.juejin.chapter08.PacketCodeC;
import github.io.volong.juejin.chapter10.MessageRequestPacket;
import github.io.volong.juejin.chapter10.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf requestByteBuf = (ByteBuf) msg;
        
        // 解码
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);
        
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
        
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println("校验成功");
            } else {
                loginResponsePacket.setReason("帐号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println("校验失败");
            }
            
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
            
        } else if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
            
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("收到!!!");
            ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.writeAndFlush(byteBuf);
        }
        
    }
    
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
