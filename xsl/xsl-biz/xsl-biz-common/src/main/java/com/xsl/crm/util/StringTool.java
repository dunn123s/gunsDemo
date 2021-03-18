package com.xsl.crm.util;

import com.alibaba.druid.util.StringUtils;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

public class StringTool {

    public static String getMarkMobile(String mobile){
        if(StringUtils.isEmpty(mobile)){
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1*****$2");
    }

    public static String markKey(String key){
        Random r = new Random();
        int number = r.nextInt(10);
        return randomChar() + number + key.substring(0, number+1)+UUID.randomUUID().toString().replace("-","")+key.substring(number+1);
    }

    public static String parseKey(String key){
        Integer number = Integer.parseInt(key.substring(1,2));
        String orgKey = key.substring(2);
        return orgKey.substring(0, number+1) + orgKey.substring(number+33);
    }

    public static String randomChar(){
        StringBuffer sb=new StringBuffer();
        int number= new Random().nextInt(3);
        int result=0;
        switch(number){
            case 0:
                //Math.random()*25+65成成65-90的int型的整型,强转小数只取整数部分
                result=(int)(Math.random()*25+65);  //对应A-Z 参考ASCII编码表
                //将整型强转为char类型
                sb.append((char)result);
                break;
            case 1:
                result=(int)(Math.random()*25+97);   //对应a-z
                sb.append((char)result);
                break;
            case 2:
                sb.append(String.valueOf(new Random().nextInt(10)));
                break;
        }

        return  sb.toString();
    }

    public static boolean checkAmountNull(BigDecimal amount){
        if(null == amount || new BigDecimal(0).compareTo(amount) == 0){
            return true;
        }

        return false;
    }


    public static String getMark4No(String data){
        if(StringUtils.isEmpty(data)){
            return data;
        }

        if(data.length() < 4){
            return data + "******";
        }else{
            return data.substring(0,4) + "******";
        }
    }
}
