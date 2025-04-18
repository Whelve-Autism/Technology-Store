package utils;

/**
 * 此接口用于实现序列化与反序列化。
 * This interface is used to implement serialization and deserialization.
 *
 * @author Guoqing Lu
 * @version 0.0
 * @since version 0.0
 */
public interface ISerializer {
    void save() throws Exception;
    void load() throws Exception;
    String fileName();
}
/*
 * End of utils.ISerializer Class.
 * Checked by Fan Xinkang on 2025/04/18.
 */