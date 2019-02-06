package github.io.volong.juejin.chapter15;

import github.io.volong.juejin.chapter08.PacketCodeC;
import github.io.volong.juejin.chapter10.MessageRequestPacket;
import github.io.volong.juejin.chapter12.MessageResponseHandler;
import github.io.volong.juejin.chapter12.PacketDecoder;
import github.io.volong.juejin.chapter12.PacketEncoder;
import github.io.volong.juejin.chapter13.Spliter;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class NettyClient {

    public static void main(String[] args) {
        
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        
        Bootstrap bootstrap = new Bootstrap();
        
        bootstrap.group(nioEventLoopGroup)
                 .channel(NioSocketChannel.class)
                 .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Spliter());
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

            System.out.println("输入消息发送到服务端:");

            while (scanner.hasNextLine()) {
                
                String line = scanner.nextLine();

                MessageRequestPacket packet = new MessageRequestPacket();
                packet.setMessage(line);
                ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), packet);
                channel.writeAndFlush(byteBuf);

            }
            
            scanner.close();
        }).start();
    }
}
