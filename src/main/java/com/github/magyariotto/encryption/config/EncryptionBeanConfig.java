package com.github.magyariotto.encryption.config;

import com.github.magyariotto.encryption.impl.BooleanEncryptor;
import com.github.magyariotto.encryption.impl.IntegerEncryptor;
import com.github.magyariotto.encryption.impl.PasswordService;
import com.github.magyariotto.encryption.impl.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionBeanConfig {
    public EncryptionBeanConfig() {
    }

    @Bean
    public PasswordService passwordService() {
        return new PasswordService();
    }

    @Bean
    public StringEncryptor stringEncryptor() {
        return new StringEncryptor();
    }

    @Bean
    public IntegerEncryptor integerEncryptor(StringEncryptor stringEncryptor) {
        return new IntegerEncryptor(stringEncryptor);
    }

    @Bean
    public BooleanEncryptor booleanEncryptor(StringEncryptor stringEncryptor) {
        return new BooleanEncryptor(stringEncryptor);
    }
}
