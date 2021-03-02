package org.camunda.example;

import org.jasypt.util.text.AES256TextEncryptor;
import org.junit.Test;

public class Encryptor {

    public static final String JASYPTPWD = "12345";
    public static final String DBPWD = "camundauser";

    @Test
    public void testEncryption() {
        //new StandardPBEStringEncryptor();
        //encryptor.setAlgorithm("PBEWithMD5AndTripleDES");

        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(JASYPTPWD);
        System.out.println("Encrypting text " + DBPWD + " with password " + JASYPTPWD);
        String encryptedText = encryptor.encrypt(DBPWD);
        System.out.println("Result " + encryptedText);
        String plainText = encryptor.decrypt(encryptedText);
        assert(plainText.equals(DBPWD));
    }
}
