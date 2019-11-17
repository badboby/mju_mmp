package com.mju.mmpo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Allen李
 * @date
 */
public class DateUtil {
    public static String date2String(Date date,String pst){
        SimpleDateFormat dateFormat = new SimpleDateFormat(pst);
        String time = dateFormat.format(date);
        return time;
    }
    public static Date string2Date(String str,String pst) throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat(pst);
        Date date = dateFormat.parse(str);
        return date;
    }
}
