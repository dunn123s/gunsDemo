package com.xsl.crm.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * AES 加密算法实现 <br/>
 * 项目名称：hid3-core <br/>
 * 类名称：AESUtil <br/>
 * 类描述： <br/>
 * 创建人：jianfei.su <br/>
 * 创建时间：2014-11-12 下午03:02:46<br/>
 * 修改人：jianfei.su <br/>
 * 修改时间：2014-11-12 下午03:02:46 <br/>
 * 修改备注： <br/>
 *
 * @version 0.0.1-SNAPSHOT<br/>
 */
public class AESUtil {

    // private static final byte[] IV = new
    // byte[]{0x3c,0x0f,0x3c,0x11,0x48,0x7f,0x10,0x11,0x48,0x42,0x53,0x75,0x11,0x00,0x53,0x64};
    // private static final IvParameterSpec iv = new IvParameterSpec(IV);
    private static Cipher cipher;

    static {
        try {
            Security.addProvider(new BouncyCastleProvider());
            cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密
     *
     * @Title: encrypt
     * @Description: TODO
     * @param strKey 加密秘钥
     * @param strIn 加密内容
     * @return
     * @throws Exception
     */
    public static synchronized String encrypt(byte[] strKey, byte[] strIn) throws Exception {
        initModel(strKey);
        byte[] encrypted = cipher.doFinal(strIn);
        return Base64.encode(encrypted);
    }

    /**
     * 加密
     *
     * @Title: encrypt
     * @Description: TODO
     * @param strKey 加密秘钥
     * @param strIn 加密内容
     * @return
     * @throws Exception
     */
    public static synchronized String encrypt(byte[] strKey, String strIn) throws Exception {
        initModel(strKey);
        byte[] encrypted = cipher.doFinal(strIn.getBytes());
        return Base64.encode(encrypted);
    }

    private static void initModel(byte[] strKey) throws Exception, InvalidKeyException,
            InvalidAlgorithmParameterException {
        SecretKeySpec skeySpec = getKey(strKey);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);// , iv
    }

    /**
     * 加密
     *
     * @Title: encrypt
     * @Description: TODO
     * @param strKey 加密秘钥
     * @param strIn 加密内容
     * @return
     * @throws Exception
     */
    public static synchronized String encrypt(byte[] strKey, String strIn, String charSet) throws Exception {
        initModel(strKey);
        byte[] encrypted = cipher.doFinal(strIn.getBytes(charSet));
        return Base64.encode(encrypted);
    }

    /**
     * AES解密
     *
     * @Title: decrypt
     * @Description: TODO
     * @param strKey 解密秘钥
     * @param strIn 解密内容
     * @param charSet 字符集
     * @return
     * @throws Exception
     */
    public static synchronized String decrypt(byte[] strKey, String strIn, String charSet) throws Exception {
        SecretKeySpec skeySpec = getKey(strKey);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec); // , iv
        byte[] encrypted1 = Base64.decode(strIn);
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, charSet);
        return originalString;
    }

    public static synchronized byte[] decryptToByte(byte[] strKey, String strIn) throws Exception {
        SecretKeySpec skeySpec = getKey(strKey);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec); // , iv
        byte[] encrypted1 = Base64.decode(strIn);
        byte[] original = cipher.doFinal(encrypted1);
        return original;
    }

    public static byte[] buildeKey() {
        return SHA256Util.getSha256(String.valueOf(System.currentTimeMillis())).getBytes();
    }

    /**
     * AES 解密
     *
     * @Title: decrypt
     * @Description: TODO
     * @param strKey 秘钥
     * @param strIn 字符集
     * @return
     * @throws Exception
     */
    public static synchronized String decrypt(byte[] strKey, String strIn) throws Exception {
        return decrypt(strKey, strIn, "UTF8");
    }

    private static SecretKeySpec getKey(byte[] strKey) throws Exception {
        byte[] arrBTmp = strKey;
        byte[] arrB = new byte[32]; // 创建一个空的16位字节数组（默认值为0）
        for (int i = 0; i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
        return skeySpec;
    }

    public static AESItem getMD5KeyInstance(String key) throws Exception {
        return getMD5KeyInstance(key, "AES");
    }

    public static AESItem getMD5KeyInstance(String key, String type) throws Exception {
        return getMD5KeyInstance(key, type, "BC");
    }

    public static AESItem getMD5KeyInstance(String key, String type, String provider) throws Exception {
        return new AESItem(MD5Util.getMd5Byte(key), type, provider);
    }

    public static AESItem getAppendZEROInstance(byte[] keyBytes, String type, String provider) throws Exception {
        int keySize = keyBytes.length;
        byte[] keyBuffer = new byte[keySize <= 16 ? 16 : 32];
        if (keySize >= keyBuffer.length) {
            System.arraycopy(keyBytes, 0, keyBuffer, 0, keyBuffer.length);
        } else {
            System.arraycopy(keyBytes, 0, keyBuffer, 0, keySize);
            for (int i = keySize; i < keyBuffer.length; i++) {
                keyBuffer[i] = 0;
            }
        }
        return new AESItem(keyBuffer, type, provider);
    }

    public static AESItem getAppendZEROInstance(byte[] keyBytes, String type) throws Exception {
        return getAppendZEROInstance(keyBytes, type, "BC");
    }

    public static AESItem getAppendZEROInstance(byte[] keyBytes) throws Exception {
        return getAppendZEROInstance(keyBytes, "AES");
    }

    public static AESItem getAppendZEROInstance(String keyBytes, String type, String provider) throws Exception {
        return getAppendZEROInstance(keyBytes.getBytes("UTF-8"), type, provider);
    }

    public static AESItem getAppendZEROInstance(String keyBytes, String type) throws Exception {
        return getAppendZEROInstance(keyBytes.getBytes("UTF-8"), type, "BC");
    }

    public static AESItem getAppendZEROInstance(String keyBytes) throws Exception {
        return getAppendZEROInstance(keyBytes.getBytes("UTF-8"), "AES");
    }

    public static void writeByteArray(byte[] arrays) {
        System.out.println("byte Array{length:" + arrays.length + "}");
        for (int i = 0; i < arrays.length; i++) {
            System.out.print(arrays[i] + " ");
        }
        System.out.println();
    }

    public static String getKey()
    {
        return getKey(256);
    }

    private static String getKey(int number) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(number);
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            return Base64.encode(b);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
