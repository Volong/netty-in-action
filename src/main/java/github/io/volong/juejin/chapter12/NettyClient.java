package github.io.volong.juejin.chapter12;

import java.util.Scanner;

import github.io.volong.juejin.chapter08.PacketCodeC;
import github.io.volong.juejin.chapter09.LoginUtil;
import github.io.volong.juejin.chapter10.MessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public static void main(String[] args) {
        
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        
        Bootstrap bootstrap = new Bootstrap();
        
        bootstrap.group(nioEventLoopGroup)
                 .channel(NioSocketChannel.class)
                 .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                }).connect("127.0.0.1", 8000).addListener(future -> {
                    if (future.isSuccess()) {
                        Channel channel = ((ChannelFuture) future).channel();
                        startConsoleThread(channel);
                    }
                });
        
    }
    
    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in, "UTF-8");
            
            while (scanner.hasNextLine()) {
                
                if (LoginUtil.hasLogin(channel)) {
                    
                    String line = scanner.nextLine();
                    
                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), packet);
                    channel.writeAndFlush(byteBuf);
                    
                    System.out.println("输入消息发送到服务端:");
                }
            }
            
            scanner.close();
        }).start();
    }
}
