package com.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DateUtil {
	
	
	public static String convertDateToString(Date date){
		String strDate = "";
		try {
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strDate = sdf.format(date);
		} catch (Exception e) {
			return null;
		}
		return strDate;
	}
	
	public static String convertDateToString(Date date, String format){
		String strDate = "";
		try {
			SimpleDateFormat sdf= new SimpleDateFormat(format);
			strDate = sdf.format(date);
		} catch (Exception e) {
			return null;
		}
		return strDate;
	}

	public static String createTradeno(Date date){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
		String strDate = sdf.format(date);
        String[] beforeShuffle = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(5, 9);
		return strDate + result;
	}
	
}
