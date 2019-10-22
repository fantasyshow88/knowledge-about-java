package com.ztl.simple.impl.crawl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import com.ztl.simple.iface.crawl.ICrawler;
import com.ztl.simple.pojos.CrawlResultPojo;
import com.ztl.simple.pojos.UrlPojo;

public class HttpUrlConnectionCrawlerImpl implements ICrawler {

	@Override
	public CrawlResultPojo crawl(UrlPojo urlPojo) {
		CrawlResultPojo crawlResultPojo = new CrawlResultPojo();
		if (urlPojo == null || urlPojo.getUrl() == null) {
			crawlResultPojo.setSuccess(false);
			crawlResultPojo.setPageContent(null);

			return crawlResultPojo;
		}

		StringBuilder stringBuilder = new StringBuilder();
		
		HttpURLConnection httpURLConnection = urlPojo.getConnection();
		if (httpURLConnection != null) {
			BufferedReader br = null;
			String line = null;
			try {
				br = new BufferedReader(new InputStreamReader(httpURLConnection
						.getInputStream(),"gb2312"));
				while ((line = br.readLine()) != null) {
//					System.out.println(line);;
					stringBuilder.append(line+"\n");
				}
				crawlResultPojo.setSuccess(true);
				crawlResultPojo.setPageContent(stringBuilder.toString());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null) {
						br.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("������δ�ر�!");
				}
			}
		}
		return crawlResultPojo;
	}
	
	public static void main(String[] args) {
		HttpUrlConnectionCrawlerImpl httpUrlConnectionCrawlerImpl = new HttpUrlConnectionCrawlerImpl();
//		UrlPojo urlPojo = new UrlPojo("http://www.baidu.com");
		 UrlPojo urlPojo = new UrlPojo("http://www.qq.com");
		// UrlPojo urlPojo = new UrlPojo(
		// "http://www.hao123.com/?tn=97961594_hao_pg");
		CrawlResultPojo crawlResultPojo=httpUrlConnectionCrawlerImpl.crawl(urlPojo);

		System.out.println(crawlResultPojo.getPageContent());
		
		System.out.println("done!");
	}
}
