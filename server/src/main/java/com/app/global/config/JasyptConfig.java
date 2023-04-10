package com.app.global.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * yml 암호화를 위한 설정 파일
 */
@Configuration
@EnableEncryptableProperties
public class JasyptConfig {

    @Value("${jasypt.password}")
    private String password;

    @Bean
    public PooledPBEStringEncryptor jasyptStringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4); //코어 수와 동일하게 설정
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
        return encryptor;
    }

}
