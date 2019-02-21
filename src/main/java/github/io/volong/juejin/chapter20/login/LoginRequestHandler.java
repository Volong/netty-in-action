package github.io.volong.juejin.chapter20.login;

import github.io.volong.juejin.chapter20.message.MessageResponseHandler;
import github.io.volong.juejin.session.Session;
import github.io.volong.juejin.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    public LoginRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {

        MessageResponseHandler.LoginResponsePacket loginResponsePacket = new MessageResponseHandler.LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUsername());


        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String randomUserId = randomUserId();
            loginResponsePacket.setUserId(randomUserId);
            System.out.println(loginRequestPacket.getUsername() + " 登录成功");

            // 记录当前登录的用户
            SessionUtil.bindSession(new Session(randomUserId, loginRequestPacket.getUsername()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码错误");
            loginResponsePacket.setSuccess(false);
            System.out.println("登录失败");
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }


    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }

}
