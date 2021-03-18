package com.xsl.crm.util;

import java.util.Random;

/**
 * @author : mk
 * @version V1.0
 * @Project: guns-vip
 * @Package com.zdky.tpl.common.utils
 * @Description: TODO
 * @date Date : 2020-01-16 16:28
 */
public class RandomUtils {
    private static String[] str = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
            "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4",
            "5", "6", "7", "8", "9" };
    public static String genRandomNum(int pwd_len) {
        String result="";
        Random rd = new Random();

        for(int i=0;i<pwd_len;i++){
            result+=str[rd.nextInt(str.length)];
        }
        return result;
    }

    public static String getSmsCode(int len){
        String code = String.valueOf(Math.random());
        return code.substring(code.length()-len,code.length());
    }

    public static String genEntKey() {
        return genRandomNum(32).toUpperCase();
    }

    public static void main(String[] args) {
        int i=0;
     while(i++<10)   System.out.println(getSmsCode(6));

//        System.out.println(genEntKey().toUpperCase());
    }
}
