package github.io.volong.juejin.chapter17.serializer;

import com.alibaba.fastjson.JSON;
import github.io.volong.juejin.chapter17.serializer.Serializer;
import github.io.volong.juejin.chapter17.serializer.SerializerAlgorithm;

public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }

}
