package github.io.volong.juejin.chapter16;

import github.io.volong.juejin.chapter09.LoginUtil;
import github.io.volong.juejin.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (!SessionUtil.hasLogin(ctx.channel())) {
            System.out.println("该用户没有登录");
            ctx.channel().close();
        } else {
            // 如果用户已经登录过了，则直接将该处理器从逻辑处理链中移除
            // 只要连接未断，客户端只要成功登录过，就被不需要再进行验证
            System.out.println("客户端已经成功登录过，无需再进行验证");
            // 调用该方法之后会调用下面的 handlerRemoved 方法
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

}
