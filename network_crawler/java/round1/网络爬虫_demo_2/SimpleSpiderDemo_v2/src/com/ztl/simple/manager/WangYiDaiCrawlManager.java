package com.ztl.simple.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ztl.simple.impl.crawl.HttpClientCrawlerImpl;
import com.ztl.simple.pojos.CrawlResultPojo;
import com.ztl.simple.pojos.UrlPojo;

/**
 * 网易贷抓取管理器
 * 
 * @author zel
 * 
 */
public class WangYiDaiCrawlManager {
	public static HttpClientCrawlerImpl httpClientCrawlerImpl = new HttpClientCrawlerImpl();

	private static CrawlResultPojo crawlOnePage(UrlPojo urlPojo) {
		CrawlResultPojo resultPojo = httpClientCrawlerImpl.crawl4Post(urlPojo);
		return resultPojo;
	}

	public static void main(String[] args) {
		String url = "http://www.wangdaizhijia.com/front_select-plat";
		UrlPojo urlPojo = new UrlPojo(url);
		Map<String, Object> parasMap = new HashMap<String, Object>();
		int max_page_number = 50;
		int have_download_page_count = 0;
		Set<String> uniqSet=new HashSet<String>();
		
		for (int pageNumber = 1; pageNumber <= max_page_number; pageNumber++) {
			parasMap.put("currPage", pageNumber);
			parasMap.put("params", "");
			parasMap.put("sort", 0);
			urlPojo.setParasMap(parasMap);

			CrawlResultPojo resultPojo = crawlOnePage(urlPojo);
            if(uniqSet.contains(resultPojo.getPageContent())){
            	System.out.println("碰到重复，代表已抓取完成!");
            	break;
            }else {
            	uniqSet.add(resultPojo.getPageContent());
            }
			if (resultPojo != null) {
				System.out.println(resultPojo);
				have_download_page_count++;
			}
		}
		
		System.out.println("已经下载了---" + have_download_page_count);

		System.out.println("done!");
	}
}
