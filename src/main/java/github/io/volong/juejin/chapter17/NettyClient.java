package github.io.volong.juejin.chapter17;

import github.io.volong.juejin.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClient {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;

    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        bootstrap.group(eventExecutors)
                 .channel(NioSocketChannel.class)
                 .handler(new ChannelInitializer<Channel>() {
                     @Override
                     protected void initChannel(Channel ch) throws Exception {
                         ch.pipeline().addLast(new Spliter());
                         ch.pipeline().addLast(new PacketDecoder());
                         ch.pipeline().addLast(new LoginResponseHandler());
                         ch.pipeline().addLast(new MessageResponseHandler());
                         ch.pipeline().addLast(new CreateGroupResponseHandler());
                         ch.pipeline().addLast(new PacketEncoder());

                     }
                 });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }


    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();

        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }


    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }

}
