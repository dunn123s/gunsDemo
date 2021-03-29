import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoundTest {
    @Test
    public void test1() {
        //向下取整 保留两位小数
        double num1 = 123.8976;

        double result = Math.floor(num1);

        double result1 = Math.floor(num1 * 100);

        double result2 = Math.floor(num1 * 100) / 100;
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);

    }
    @Test
    public void test2(){
        boolean number = isNumber("2");
        System.out.println("number = " + number);
    }
    //只保留小数点后两位
    public boolean isNumber(String str){
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match = pattern.matcher(str);
        return match.matches();
    }
    @Test
    public void test3() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(getDaysOfMonth(sdf.parse("2020-9-1")));
    }


    //获取每月的总天数
    public int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Test
    public void test4() throws ParseException {
        String firstDay = getFirstDay(false);
        System.out.println("firstDay = " + firstDay);
    }

      /**
       * 当月第一天
       * @param timeFlag 是否带时分秒， true是
       * @return  获取当月第一天
       * */
    public static String getFirstDay(boolean timeFlag) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();

        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
        if(timeFlag){
            str.append(" 00:00:00");
        }
        return str.toString();
    }

}
