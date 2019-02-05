package github.io.volong.juejin.chapter15;

import github.io.volong.juejin.chapter09.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (!LoginUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            // 如果用户已经登录过了，则直接将该处理器从逻辑处理链中移除
            // 只要连接未断，客户端只要成功登录过，就被不需要再进行验证
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再次进行验证，AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接");
        }
    }
}
