package com.ztl.simple.iface.crawl;

import com.ztl.simple.pojos.CrawlResultPojo;
import com.ztl.simple.pojos.UrlPojo;

public interface ICrawler {
	public CrawlResultPojo crawl(UrlPojo urlPojo);
}
