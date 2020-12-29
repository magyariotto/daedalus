package com.github.magyariotto.encryption;

import com.github.magyariotto.encryption.config.EncryptionBeanConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({EncryptionBeanConfig.class})
public @interface EnableEncryption {
}
