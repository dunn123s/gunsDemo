package com.xsl.crm.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * SHA256加密器 <br/>
 * 项目名称：hid3-core <br/>
 * 类名称：SHA256Util <br/>
 * 类描述： <br/>
 * 创建人：jianfei.su <br/>
 * 创建时间：2014-11-12 下午03:14:48<br/>
 * 修改人：jianfei.su <br/>
 * 修改时间：2014-11-12 下午03:14:48 <br/>
 * 修改备注： <br/>
 * 
 * @version 0.0.1-SNAPSHOT<br/>
 */
public class SHA256Util {

    private static MessageDigest SHA256;

    static {
        try {
            SHA256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static synchronized String getSha256(String msg) {
        return getSha256(msg.getBytes());
    }

    public static synchronized String getSha256(byte[] msg) {
        SHA256.update(msg);
        return CodingUtil.bytesToHexString(SHA256.digest());
    }
    
    public static synchronized String getSha1(String str){
        String result = null;
        try {
            MessageDigest mesd = MessageDigest.getInstance("SHA-1");
            if(null!=str && !"".equals(str)) {
                result = CodingUtil.bytesToHexString(mesd.digest(str.getBytes("UTF-8")));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return result;
        }  catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    /***
    * 返回长度为【strLength】的随机数，在前面补0
    */
    public static String getFixLenthString(int strLength) {

        Random rm = new Random();

        // 获得随机数
        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

        // 将获得的获得随机数转化为字符串
        String fixLenthString = String.valueOf(pross);

        // 返回固定的长度的随机数
        return fixLenthString.substring(1, strLength + 1);
    }

}
