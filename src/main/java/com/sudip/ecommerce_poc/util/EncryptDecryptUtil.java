package com.sudip.ecommerce_poc.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Slf4j
@Component
public class EncryptDecryptUtil {

    @Value("${secret.key}")
    private String sk;

    private SecretKey stringToKey(String keyStr) {
        byte[] decodedKey = Base64.getDecoder().decode(keyStr);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public String encryptPII(String str) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, stringToKey(sk));
            byte[] cipherBA = cipher.doFinal(str.getBytes());
            return Base64.getEncoder().encodeToString(cipherBA);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decryptPII(String str) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, stringToKey(sk));
            byte[] cipherBA = Base64.getDecoder().decode(str);
            byte[] clearBA = cipher.doFinal(cipherBA);
            return new String(clearBA);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String encryptUrlPII(String str) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, stringToKey(sk));
            byte[] cipherBA = cipher.doFinal(str.getBytes());
            return Base64.getUrlEncoder().encodeToString(cipherBA);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decryptUrlPII(String str) throws Exception{

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, stringToKey(sk));
        byte[] cipherBA = Base64.getUrlDecoder().decode(str);
        byte[] clearBA = cipher.doFinal(cipherBA);
        return new String(clearBA);

    }
}
