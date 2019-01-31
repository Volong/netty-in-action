package github.io.volong.juejin.chapter08;

public interface Serializer {

    byte JSON_SERIALIZER = 1;
    
    Serializer DEFAULT = new JSONSerializer();
    
    byte getSerializerAlgorithm();
    
    byte[] serialize(Object object);
    
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
