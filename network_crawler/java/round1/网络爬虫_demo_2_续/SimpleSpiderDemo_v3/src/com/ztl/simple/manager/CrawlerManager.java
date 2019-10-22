package com.ztl.simple.manager;

import com.ztl.simple.iface.crawl.ICrawler;
import com.ztl.simple.impl.crawl.HttpUrlConnectionCrawlerImpl;
import com.ztl.simple.impl.crawl.SocketCrawlerImpl;
import com.ztl.simple.pojos.CrawlResultPojo;
import com.ztl.simple.pojos.UrlPojo;

/**
 * ��ҵ���߼���ץȡ������
 * 
 * @author zel
 * 
 */
public class CrawlerManager {
	private ICrawler crawler;

	public CrawlerManager(boolean isSocket) {
		if (isSocket) {
			this.crawler = new SocketCrawlerImpl();
		} else {
			this.crawler = new HttpUrlConnectionCrawlerImpl();
		}
	}

	public CrawlResultPojo crawl(UrlPojo urlPojo) {
		return this.crawler.crawl(urlPojo);
	}

	public static void main(String[] args) {
		CrawlerManager crawlerManager = new CrawlerManager(false);
		UrlPojo urlPojo = new UrlPojo("http://www.qq.com");
		CrawlResultPojo crawlResultPojo=crawlerManager.crawl(urlPojo);
		
		System.out.println("CrawlResultPojo---"+crawlResultPojo.getPageContent());
		
	}

}
