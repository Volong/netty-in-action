package github.io.volong.juejin.chapter18.login;

import github.io.volong.juejin.session.Session;
import github.io.volong.juejin.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;


public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {


        String userId = msg.getUserId();
        String userName = msg.getUserName();

        if (msg.isSuccess()) {
            System.out.println(userName + " 登录成功，userId 为: " + userId);
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            System.out.println(new Date() + " : 客户端登录失败，原因: " + msg.getReason());
        }
        
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭");
    }
}
