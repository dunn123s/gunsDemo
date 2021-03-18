package com.xsl.crm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    protected static final Logger logger   = LoggerFactory.getLogger(StringUtil.class);
    private static final byte     UPPER_A  = 'A';
    private static final byte     UPPER_Z  = 'Z';
    private static final byte     LOW_A    = 'a';
    private static final byte     LOW_Z    = 'z';
    private static final byte     TO_UPPER = LOW_A - UPPER_A;

    /**
     * 判断字符串是否为空
     * 
     * @param data 验证的数据
     * @return 如果字符串为null 或 字符串完全由空格' '组成 <br/>
     * 返回true 否则 返回 false
     */
    public static boolean isEmpty(String data) {
        return data == null || data.trim().length() == 0;
    }

    /**
     * 由纯大写字母组成
     * 
     * @param data
     * @return
     */
    public static boolean isUpper(String data) {
        for (char item : data.toCharArray()) {
            if (!(item >= UPPER_A && item <= UPPER_Z)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 匈牙利命名法转驼峰命名法
     * 
     * @param stringValue
     * @return
     */
    public static String changeHungaryToCamel(String stringValue) {
        if (stringValue.indexOf("_") >= 0 || StringUtil.isUpper(stringValue)) {
            stringValue = stringValue.toLowerCase();
            int _index = 0;
            int valLength = stringValue.length();
            String oldChar;
            String newChar;
            while ((_index = stringValue.indexOf('_')) >= 0) {
                if (_index + 1 < valLength
                    && (stringValue.charAt(_index + 1) >= LOW_A && stringValue.charAt(_index + 1) <= LOW_Z)) {
                    oldChar = new String(new char[] { '_', stringValue.charAt(_index + 1) });
                    newChar = new String(new char[] { (char) (stringValue.charAt(_index + 1) - TO_UPPER) });
                } else {
                    oldChar = new String(new char[] { '_' });
                    newChar = "";
                }
                stringValue = stringValue.replace(oldChar, newChar);
            }
        }
        return stringValue;
    }

    /**
     * 驼峰转匈牙利命名法
     * 
     * @param stringValue
     * @return
     */
    public static String changeCamelToHungary(String stringValue) {
        if (!(stringValue.indexOf("_") >= 0)) {
            int _index = 0;
            String oldChar;
            String newChar;
            while ((_index = indexOfUpper(stringValue)) >= 0) {
                oldChar = new String(new char[] { stringValue.charAt(_index) });
                newChar = new String(new char[] { '_', (char) (stringValue.charAt(_index) + TO_UPPER) });
                stringValue = stringValue.replace(oldChar, newChar);
            }
        }
        return stringValue.toUpperCase();
    }

    /**
     * 返回大写字母的位置
     * 
     * @param stringValue
     * @return
     */
    private static int indexOfUpper(String stringValue) {
        char[] cs = stringValue.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] >= UPPER_A && cs[i] <= UPPER_Z) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断字符串非空
     * 
     * @param data
     * @return
     */
    public static boolean notEmpty(String data) {
        return !isEmpty(data);
    }

    /**
     * 将MAP 的KEY 由匈牙利命名法改成驼峰命名法
     * 
     * @param groupDate
     * @return
     */
    public static Map<String, String> changeHungaryToCamel(Map<String, String> groupDate) {
        Map<String, String> newDate = new LinkedHashMap<String, String>();
        for (Map.Entry<String, String> item : groupDate.entrySet()) {
            newDate.put(StringUtil.changeHungaryToCamel(item.getKey()), item.getValue());
        }
        groupDate.clear();
        return newDate;
    }

    /**
     * 在字符串的最后方加小数点<br>
     * 如： addLastDot("abc",2) 返回 a.bc<br>
     * 如果位数不足，在前方补0 如： addLastDot("a",2) 返回 0.0a
     * 
     * @Title: addLastDot
     * @Description: TODO
     * @param string
     * @param lastIndex
     * @return
     */
    public static String addLastDot(String string, int lastIndex) {
        StringBuilder builder = new StringBuilder(string);
        boolean isNeg = false;
        if (builder.charAt(0) == '-') {
            builder = builder.delete(0, 1);
            isNeg = true;
        }
        int length = builder.length();
        for (int i = 0; i <= lastIndex - length; i++) {
            builder.insert(0, '0');
        }
        builder.insert(builder.length() - lastIndex, '.');
        if (isNeg) builder.insert(0, '-');
        return builder.toString();
        // return String.format(string, "%2d");
    }

    /**
     * 在字符串后添加
     * 
     * @Title: append
     * @Description: TODO
     * @param string
     * @param appendChar
     * @param length
     * @return
     */
    public static String append(String string, char appendChar, int length) {
        char[] c = new char[length];
        Arrays.fill(c, appendChar);
        string = string == null ? "" : string;
        StringBuilder builder = new StringBuilder(string.length() + length + 1);
        return builder.append(string).append(c).toString();
    }

    /**
     * 将字符串最后的 length位转换成 coverChar
     * 
     * @Title: coverLastChar
     * @Description: TODO
     * @param string
     * @param coverChar
     * @param length
     * @return
     */
    public static String coverLastChar(String string, char coverChar, int length) {
        if (string != null && string.length() >= length) {
            char[] c = new char[length];
            Arrays.fill(c, coverChar);
            StringBuilder builder = new StringBuilder(string);
            return builder.replace(builder.length() - length, builder.length(), new String(c)).toString();
        }
        return string;
    }

    /**
     * 字符串从头替换
     * 
     * @param string
     * @param cover
     * @return
     */
    public static String coverFirst(String string, String cover) {
        if (string != null && string.length() >= cover.length()) {
            StringBuilder builder = new StringBuilder(string);
            return builder.replace(0, cover.length(), cover).toString();
        }
        return string;
    }

    /**
     * 字符串从头替换
     * 
     * @param string
     * @param cover
     * @return
     */
    public static String coverFirst(String string, char cover) {
        if (string != null && string.length() >= 1) {
            StringBuilder builder = new StringBuilder(string);
            builder.setCharAt(0, cover);
            return builder.toString();
        }
        return string;
    }

    /**
     * 字符串从头删除
     * 
     * @param string 字符串
     * @param length 删除长度
     * @return
     */
    public static String deleteFirst(String string, int length) {
        if (string != null && string.length() >= length) {
            StringBuilder builder = new StringBuilder(string);
            return builder.delete(0, length).toString();
        }
        return string;
    }

    /**
     * 高性能将字母(a-z)转换成大写<br>
     * 非字母情况 可能会报错
     * 
     * @param c
     * @return
     */
    public static char toUpper(char c) {
        if (c >= LOW_A && c <= LOW_Z) return (char) (c - TO_UPPER);
        return c;
    }

    /**
     * 高性能将字母(A-Z)转换成小写
     * 
     * @param c
     * @return
     */
    public static char toLower(char c) {
        if (c >= UPPER_A && c <= UPPER_Z) return (char) (c + TO_UPPER);
        return c;
    }

    public static String insert(String string, char insertVal, int length) {
        char[] c = new char[length];
        Arrays.fill(c, insertVal);
        if (string != null) {
            return new StringBuilder(string.length() + length).append(c).append(string).toString();
        }
        return null;
    }

    static byte ZERO  = '0';
    static byte NIGHT = '9';

    /**
     * 判断参数是一个 Integer 的数字
     * 
     * @Title: isInteger
     * @Description: TODO
     * @param innerItem
     * @return
     */
    public static boolean isInteger(String innerItem) {
        if (StringUtil.isEmpty(innerItem)) {
            return false;
        }
        char[] cs = innerItem.toCharArray();
        int i = 0;
        if (cs[0] == '-' && cs.length >= 2) {
            i = 1;
        }
        char c;
        for (; i < cs.length; i++) {
            c = cs[i];
            if (!(c >= ZERO && c <= NIGHT)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断参数是一个 浮点类型数字
     * 
     * @Title: isInteger
     * @Description: TODO
     * @param innerItem
     * @return
     */
    public static boolean isDouble(String innerItem) {
        if (innerItem == null) {
            return false;
        }
        boolean hasDot = false;
        char[] cs = innerItem.toCharArray();

        int i = 0;
        if (cs[0] == '-' && cs.length >= 2) {
            i = 1;
        }
        char c;
        for (; i < cs.length; i++) {
            c = cs[i];
            if (!(c >= ZERO && c <= NIGHT)) {
                if (!hasDot && c == '.') {
                    hasDot = true;
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    /**
     * 判断参数是一个 浮点类型数字 保留N位小数
     * 
     * @Title: isInteger
     * @Description: TODO
     * @param innerItem
     * @param n N位小数
     * @return
     */
    public static boolean isDouble(String innerItem, int n) {
        if (innerItem == null) {
            return false;
        }
        boolean hasDot = false;
        char[] cs = innerItem.toCharArray();

        int i = 0;
        if (cs[0] == '-' && cs.length >= 2) {
            i = 1;
        }
        char c;

        for (; i < cs.length; i++) {
            c = cs[i];
            if (!(c >= ZERO && c <= NIGHT)) {
                if (!hasDot && c == '.') {
                    hasDot = true;
                    continue;
                }

                return false;
            }
        }
        return true;
    }

    /**
     * 占位符 格式化<br>
     * 可以使用 {xxx} 形式的占位符，当调用该方法时<br>
     * {xxx} 以 xxx 为KEY value 为止进行替换 如：<br>
     * [今天天气{<b>weather</b>}适合{<b>able</b>}]<br>
     * <b>weather</b>:好 , <b>able</b>:玩 则返回：<br>
     * 今天天气好适合玩
     * 
     * @param msg
     * @param key
     * @param value
     * @return
     */
    public static String format(String msg, String key, Object value) {
        return msg.replaceAll("\\{" + key + "\\}", String.valueOf(value == null ? "" : value));
    }

    /**
     * 删除 {xxx} 形式的占位符
     * 
     * @param msg
     * @return
     */
    public static String removePlaceholder(String msg) {
        return msg.replaceAll("\\{(.*?)\\}", "");
    }

    /**
     * 从左边增加指定字符
     * 
     * @Title: appendLeft
     * @Description: TODO
     * @param c 被指定的字符
     * @param length 内容长度
     * @param content 内容
     * @return
     */
    public static String appendLeft(char c, int length, Object content) {
        String result = String.valueOf(content);
        length = length - result.length();
        char[] cs = null;
        if (length > 0) {
            cs = new char[length];
            Arrays.fill(cs, c);
            return new StringBuilder(length + result.length()).append(cs).append(result).toString();
        }
        return result;
    }

    /**
     * 将URL 形式的字符串 <br>
     * (a=b&c=d) 形式的字符串 转换成键值对形式的MAP
     * 
     * @param paramStr
     * @return
     */
    public static Map<String, String> toQueryMap(String paramStr) {
        String[] vals = split(paramStr, "&");
        Map<String, String> resultMap = new HashMap<String, String>(vals.length);
        int index;
        for (int i = 0; i < vals.length; i++) {
            index = vals[i].indexOf("=");
            resultMap.put(vals[i].substring(0, index), vals[i].substring(index + 1, vals[i].length()));
        }
        return resultMap;
    }

    /**
     * 将字符串转换成 String[] 数组<br>
     * 支持复杂分隔符
     * 
     * @param msg 需要被截取的信息
     * @param cut 分隔符
     * @return
     */
    public static String[] split(String msg, String cut) {
        int index = 0;
        int befault = 0;
        List<String> l = new ArrayList<String>(128);
        do {
            index = msg.indexOf(cut, index);
            l.add(msg.substring(befault, index == -1 ? msg.length() : index));
            index += 1;
            befault = index + cut.length() - 1;
        } while (index > 0 && index < msg.length());
        return l.toArray(new String[l.size()]);
    }

    /**
     * 将字符串截断后比较
     * 
     * @param msg 需要被截取的信息
     * @param cut 分隔符
     * @param compare 需要比较的字符串
     * @return
     */
    public static boolean splitAndCompare(String msg, String cut, String compare) {
        String splitStr[] = msg.split(cut);
        for (String str : splitStr) {
            if (str.equals(compare)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 手机号校验
     * 
     * @param phoneNo
     * @return
     */
    public static boolean isPhoneNo(String phoneNo) {
        Pattern p = Pattern.compile("^1[3|4|5|7|8]\\d{9}$");
        Matcher m = p.matcher(phoneNo);
        return m.matches();
    }

    /**
     * 信用卡有效期校验(YYMM)
     * 
     * @param expDate
     * @return
     */
    public static boolean isExpDate(String expDate) {
        Pattern p = Pattern.compile("^\\d{2}(0[1-9]|1[0-2])$");
        Matcher m = p.matcher(expDate);
        return m.matches();
    }

    /**
     * 生成日志ID
     * 
     * @return
     */
    public static String getLogId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成随机数
     * 
     * @param length
     * @return
     */
    public static String getRandomStr(Integer length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((int) (10 * (Math.random())));
        }
        return sb.toString();
    }

    /*
     * 长度不足8位，则补0
     */
    public static String supplementZero(StringBuilder s){
        int len=s.length();
        if(len<8){
            for(int i=0 ;i<(8-len);i++){
                s.insert(0, "0");
            }
        }
        return s.toString();
    }
    
    public static String supplementByLength(StringBuilder s,int length){
        int len=s.length();
        if(len<8){
            for(int i=0 ;i<(length-len);i++){
                s.insert(0, "0");
            }
        }
        return s.toString();
    }
    
    /**
     * 将首字母大写
     * */
	public static String captureName(String name) {
		if(isEmpty(name)){
			return null;
		}
		
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}
	
	public static String getRandomStr(){
		String uuid = UUID.randomUUID().toString();
		return StringUtils.replace(uuid, "-", "");
	} 
}
