package github.io.volong.juejin.chapter15;

import github.io.volong.juejin.chapter08.LoginRequestPacket;
import github.io.volong.juejin.chapter09.LoginResponsePacket;
import github.io.volong.juejin.chapter09.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        
        System.out.println(new Date() + " : 收到客户端请求");
        
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());
        
        if (valid(msg)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + " : 登录成功");

            // 标记当前 channel 为已登录
            LoginUtil.hasLogin(ctx.channel());
        } else {
            loginResponsePacket.setReason("帐号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + " : 登录失败");
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
