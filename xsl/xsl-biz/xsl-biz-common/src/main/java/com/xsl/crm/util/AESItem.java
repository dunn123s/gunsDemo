package com.xsl.crm.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Security;

public class AESItem {

    private Cipher        cipher;
    private SecretKeySpec key;

    public enum EncodType {
                           BASE64, HEX
    }

    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    public AESItem(byte[] key, String type) throws Exception {
        this(key, type, "BC");
    }

    public AESItem(byte[] key, String type, String provider) throws Exception {
        cipher = Cipher.getInstance(type, provider);
        this.key = getKey(key);
    }

    public AESItem(byte[] key) throws Exception {
        this(key, "AES");
    }

    public byte[] encrypt(byte[] msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(msg);
    }

    public byte[] decrypt(byte[] msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
                                      UnsupportedEncodingException {
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(msg);
    }

    public String encrypt(byte[] msg, EncodType encodType) throws Exception {
        switch (encodType) {
            case BASE64:
                return Base64.encode(encrypt(msg));
            case HEX:
                return CodingUtil.bytesToHexString(encrypt(msg));
        }
        throw new Exception("ENCODE_TYPE not found !");
    }

    public String encrypt(String msg, EncodType encodType) throws Exception {
        return encrypt(msg.getBytes("UTF-8"), encodType);
    }

    public byte[] decrypt(String msg, EncodType encodType) throws Exception {
        switch (encodType) {
            case BASE64:
                return decrypt(Base64.decode(msg));
            case HEX:
                return decrypt(CodingUtil.hexStringToBytes(msg));
        }
        throw new Exception("ENCODE_TYPE not found !");
    }

    public String decryptStr(String msg, EncodType encodType, String charSet) throws Exception {
        return new String(decrypt(msg, encodType), charSet);
    }

    public String decryptStr(String msg, EncodType encodType) throws Exception {
        return new String(decrypt(msg, encodType), "UTF-8");
    }

    private static SecretKeySpec getKey(byte[] strKey) throws Exception {
        if (strKey.length < 16) {
            throw new Exception("??????????????????16???");
        }
        SecretKeySpec skeySpec = new SecretKeySpec(strKey, "AES");
        return skeySpec;
    }
}
