package github.io.volong.juejin.chapter09;

import github.io.volong.juejin.chapter10.Attributes;
import io.netty.channel.Channel;

public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }
    
    public static boolean hasLogin(Channel channel) {
        return channel.attr(Attributes.LOGIN) != null;
    }
}
