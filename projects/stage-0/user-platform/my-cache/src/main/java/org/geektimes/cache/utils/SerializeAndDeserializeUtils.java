package org.geektimes.cache.utils;

import javax.cache.CacheException;
import java.io.*;

public class SerializeAndDeserializeUtils {

    //    序列化
    public static byte[] serialize(Object value) {
        byte[] bytes = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)
        ) {
            // Key -> byte[]
            objectOutputStream.writeObject(value);
            bytes = outputStream.toByteArray();
        } catch (IOException e) {
            throw new CacheException(e);
        }
        return bytes;
    }

    //    反序列化
    public static <T> T deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        T value = null;
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)
        ) {
            // byte[] -> Value
            value = (T) objectInputStream.readObject();
        } catch (Exception e) {
            throw new CacheException(e);
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(serialize("asdasdasdad"));
        System.out.println((String) deserialize(serialize("asdasdasdad")));
    }
}