package com.jiaoyiping.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author 焦一平
 * 
 */
public class DateUtIl {

	private static DateFormat dataFormat;
	private static DateFormat dateFormatWhithTimeStamp;
	// 默认方法 格式是 yyyy-MM-dd

	static {
		dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatWhithTimeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	}

	
	public static String dateToString(Date date) {
		String s = dataFormat.format(date);
		return s;
	}

	public static Date StringToDate(String s, boolean isTimeStamp) {
		Date result = null;
		// 如果不是TimeStamp类型的就调用基本的转换方法
		if (!isTimeStamp) {
			try {
				result = dataFormat.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		} else {
			try {
				result = dateFormatWhithTimeStamp.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}
		return result;
	}
}
