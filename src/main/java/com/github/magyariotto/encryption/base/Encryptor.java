package com.github.magyariotto.encryption.base;

public interface Encryptor<T> {
    String encryptEntity(T var1, String var2);

    T decryptEntity(String var1, String var2);
}
