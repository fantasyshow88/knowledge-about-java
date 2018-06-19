package com.roocon.sharding05;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BetweenDay {
	
	
	private static String beginDate = "2017-01-01";
	
	private static String endDate = "2017-01-31";
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args) throws Exception {
		// 每天的毫秒值    24 * 60 * 60 * 1000
		
		Date begin = format.parse(beginDate);
		
		Date end = format.parse(endDate);
		
		long bm = begin.getTime();
		
		long em = end.getTime();
		
		long r = em - bm;
		
		long day = r / (24 * 60 * 60 * 1000) /10;
		
		System.out.println(day);
		
		
		
	}

}
