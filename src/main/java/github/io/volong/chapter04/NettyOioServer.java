package github.io.volong.chapter04;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.oio.OioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class NettyOioServer {

    public void server(int port) throws InterruptedException {
        ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hi\r\n", CharsetUtil.UTF_8));
        OioEventLoopGroup oioEventLoopGroup = new OioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        
        serverBootstrap.group(oioEventLoopGroup)
                       .channel(OioServerSocketChannel.class) // 同步
                       .localAddress(new InetSocketAddress(port))
                       .childHandler(new ChannelInitializer<Channel>() {

                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(buf.duplicate())
                                       .addListener(ChannelFutureListener.CLOSE);
                                } 
                            });
                        }
                    });
        
        ChannelFuture future = serverBootstrap.bind().sync();
        future.channel().closeFuture().sync();
    }
}
