//package com.app;
//
//import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
//import org.junit.jupiter.api.Test;
//
///**
// * yml 암호화 진행
// */
//public class JasyptTest {
//
//    @Test
//    public void jasyptTest() {
//        String password = "sakncksjallkasdkl#$@^#*asdsiajodias2737"; //vmOptions에 넣어둔 값으로 설정
//        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//        encryptor.setPoolSize(4);
//        encryptor.setPassword(password);
//        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");
//        String content = "kevin1234123412341234123412341234123132132132";    // 암호화 할 내용
//        String encryptedContent = encryptor.encrypt(content); // 암호화
//        String decryptedContent = encryptor.decrypt(encryptedContent); // 복호화
//        System.out.println("Enc : " + encryptedContent + ", Dec: " + decryptedContent);
//    }
//
//}
