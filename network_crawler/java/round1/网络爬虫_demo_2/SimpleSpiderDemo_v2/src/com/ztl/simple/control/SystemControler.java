package com.ztl.simple.control;

import java.util.ArrayList;
import java.util.List;

import com.ztl.simple.manager.CrawlerManager;
import com.ztl.simple.pojos.CrawlResultPojo;
import com.ztl.simple.pojos.UrlPojo;

/**
 * 爬虫控制器
 * 
 * @author zel
 * 
 */
public class SystemControler {
	public static void main(String[] args) {
		List<UrlPojo> urlPojoList = new ArrayList<UrlPojo>();

		UrlPojo urlPojo1 = new UrlPojo("http://www.qq.com");
		UrlPojo urlPojo2 = new UrlPojo("http://www.baidu.com");

		urlPojoList.add(urlPojo1);
		urlPojoList.add(urlPojo2);

		CrawlerManager crawlerManager = new CrawlerManager(false);

		for (UrlPojo pojo : urlPojoList) {
			CrawlResultPojo crawlResultPojo = crawlerManager.crawl(pojo);
			// System.out.println("CrawlResultPojo---"
			// + crawlResultPojo.getPageContent());

			System.out.println("pojo toString---" + pojo.toString());
			System.out.println("crawl status---" + crawlResultPojo.isSuccess());

		}
	}
}
