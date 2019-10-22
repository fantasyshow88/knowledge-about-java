package com.ztl.simple.control;

import com.ztl.simple.manager.WangYiDaiCrawlManager;

/**
 * 爬虫控制器
 * 
 * @author zel
 * 
 */
public class SystemControler {
	public static void main(String[] args) {
		String url = "http://www.wangdaizhijia.com/front_select-plat";
		int max_page_number = Integer.parseInt(args[0]);
		String filePath = args[1];
		// int max_page_number = 100;
		// String fileName = "网易贷_数据集.txt";
		WangYiDaiCrawlManager.processWangYiDai(url, max_page_number, filePath);

		System.out.println("done!");
	}
}
