package com.dzc.springboot.util;

import com.sun.tools.jdi.ArrayTypeImpl;
import sun.security.util.math.IntegerModuloP;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: 董政辰
 * @date: 2020/9/22 15:06
 * @description:获取时间的工具类 对时间的修改等 字符←→时间转换
 * @email：532587041@qq.com
 */
public class DateUtil {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年-MM月-dd日");

    public static String strToDate(Date date) {
        String time = sdf.format(date);
        return time;
    }

    //获取当前并根据参数值进行加减
    public static String nowDateToStr(Integer day) {
        Date date = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.DAY_OF_MONTH, day);
        String time = strToDate(cl.getTime());
        return time;
    }


    //获取借书第一天的时间
    public static String startDateToStr(String date,Integer day) throws ParseException {
        Date rtime = sdf.parse(date);
        Calendar cl = Calendar.getInstance();
        cl.setTime(rtime);
        cl.add(Calendar.DAY_OF_MONTH, -day);
        String stime = strToDate(cl.getTime());
        return stime;
    }

    //判断当前时间是否大于归还时间 逾期为true 否则为false
    public static String isLate(String date) {
        Date rtime = null;
        Date ntime = null;
        try {
            rtime = sdf.parse(date);
            Date now = new Date();
            String format = sdf.format(now);
            ntime = sdf.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (ntime.getTime() == rtime.getTime())
            return  "今日归还";
        if (ntime.getTime() < rtime.getTime())
            return  "未逾期";
        if(ntime.getTime()>rtime.getTime())
            return  "已逾期";
        return "位置错误";
    }
}
