package github.io.volong.juejin.chapter12;

import java.util.Date;
import java.util.UUID;

import github.io.volong.juejin.chapter08.LoginRequestPacket;
import github.io.volong.juejin.chapter09.LoginResponsePacket;
import github.io.volong.juejin.chapter09.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserId("Volong");
        loginRequestPacket.setPassword("Volong");
        
        ctx.channel().writeAndFlush(loginRequestPacket);
    }
    
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.println(new Date() + " : 客户端登录成功");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + " : 客户端登录失败，原因: " + msg.getReason());
        }
        
    }

}
