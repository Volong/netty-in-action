package github.io.volong.juejin.chapter10;

import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
