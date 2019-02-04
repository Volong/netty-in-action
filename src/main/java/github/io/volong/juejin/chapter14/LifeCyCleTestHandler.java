package github.io.volong.juejin.chapter14;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class LifeCyCleTestHandler extends ChannelInboundHandlerAdapter {

    /**
     *
     * 检测到新连接之后，调用 ch.pipeline().addLast(new LifeCycleTestHandle()) 之后的回掉
     * 表示在当前 channel 中，已经成功添加了一个 handler 处理器
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("逻辑处理器被添加: handlerAdded()");
        super.handlerAdded(ctx);
    }

    /**
     * 当前 channel 的所有逻辑处理已经和某个 NIO 线程进行了绑定
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 绑定到线程(NioEventLoop)：channelRegistered()");
        super.channelRegistered(ctx);
    }


    /**
     *
     * 当 channel 中所有的逻辑链都准备完毕（即已经添加完所有的 handler）以及还绑定好一个 handler 线程之后
     * 会调用该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 准备就绪：channelActive()");
        super.channelActive(ctx);
    }

    /**
     *
     * 客户端向服务端发来消息，每次都会回掉该方法，表示有数据可以读
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channel 有数据可读：channelRead()");
        super.channelRead(ctx, msg);
    }


    /**
     *
     * 服务端每次读完一次完整的数据之后，回调该方法，表示数据读取完毕
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 某次数据读完：channelReadComplete()");
        super.channelReadComplete(ctx);
    }


    /**
     *
     * 表示这条连接已经被关闭了，在 TCP 上已经不是 ESTABLISH 状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 被关闭：channelInactive()");
        super.channelInactive(ctx);
    }


    /**
     *
     * 这条连接对应的 NIO 线程移除对该连接的绑定
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel 取消线程(NioEventLoop) 的绑定: channelUnregistered()");
        super.channelUnregistered(ctx);
    }


    /**
     *
     * 给这条连接添加的所有处理器都被移除掉时会调用该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("逻辑处理器被移除：handlerRemoved()");
        super.handlerRemoved(ctx);
    }
    
}
