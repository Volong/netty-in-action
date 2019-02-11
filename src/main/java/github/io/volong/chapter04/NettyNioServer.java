package github.io.volong.chapter04;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class NettyNioServer {

    public void server(int port) throws InterruptedException {
        final ByteBuf buf = Unpooled.copiedBuffer("Hi\r\n", CharsetUtil.UTF_8);
        EventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        
        serverBootstrap.group(nioEventLoopGroup)
                       .channel(NioServerSocketChannel.class) // 异步
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
