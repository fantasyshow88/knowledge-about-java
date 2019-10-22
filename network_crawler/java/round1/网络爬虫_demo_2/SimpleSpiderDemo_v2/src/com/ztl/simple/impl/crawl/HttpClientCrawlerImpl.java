package com.ztl.simple.impl.crawl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.ztl.simple.iface.crawl.ICrawler;
import com.ztl.simple.pojos.CrawlResultPojo;
import com.ztl.simple.pojos.UrlPojo;

public class HttpClientCrawlerImpl implements ICrawler {
	public CloseableHttpClient httpclient = HttpClients.custom().build();

	@Override
	public CrawlResultPojo crawl(UrlPojo urlPojo) {
		if (urlPojo == null) {
			return null;
		}
		CrawlResultPojo crawlResultPojo = new CrawlResultPojo();
		CloseableHttpResponse response1 = null;
		BufferedReader br = null;
		try {
			HttpGet httpget = new HttpGet(urlPojo.getUrl());
			response1 = httpclient.execute(httpget);
			HttpEntity entity = response1.getEntity();
			InputStreamReader isr = new InputStreamReader(entity.getContent(),
					"utf-8");
			br = new BufferedReader(isr);

			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			crawlResultPojo.setSuccess(true);
			crawlResultPojo.setPageContent(stringBuilder.toString());
			return crawlResultPojo;
		} catch (Exception e) {
			e.printStackTrace();
			crawlResultPojo.setSuccess(false);
		} finally {
			if (response1 != null) {
				try {
					response1.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		return crawlResultPojo;
	}

	/**
	 * 传入加入参数post参数的url pojo
	 */
	public CrawlResultPojo crawl4Post(UrlPojo urlPojo) {
		if (urlPojo == null) {
			return null;
		}
		CrawlResultPojo crawlResultPojo = new CrawlResultPojo();
		CloseableHttpResponse response1 = null;
		BufferedReader br = null;
		try {
			RequestBuilder rb = RequestBuilder.post().setUri(
					new URI(urlPojo.getUrl()));
			;
			// .addParameter("IDToken1",
			// "username").addParameter("IDToken2", "password").build();

			Map<String, Object> parasMap = urlPojo.getParasMap();
			if (parasMap != null) {
				for (Entry<String, Object> entry : parasMap.entrySet()) {
					rb
							.addParameter(entry.getKey(), entry.getValue()
									.toString());
				}
			}
			HttpUriRequest httpRequest = rb.build();
			response1 = httpclient.execute(httpRequest);
			HttpEntity entity = response1.getEntity();
			InputStreamReader isr = new InputStreamReader(entity.getContent(),
					"utf-8");
			br = new BufferedReader(isr);

			String line = null;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			crawlResultPojo.setSuccess(true);
			crawlResultPojo.setPageContent(stringBuilder.toString());
			return crawlResultPojo;
		} catch (Exception e) {
			e.printStackTrace();
			crawlResultPojo.setSuccess(false);
		} finally {
			if (response1 != null) {
				try {
					response1.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		return crawlResultPojo;
	}

	public static void main(String[] args) throws Exception {
		HttpClientCrawlerImpl httpClientCrawlerImpl = new HttpClientCrawlerImpl();
		String url = "http://www.wangdaizhijia.com/front_select-plat";
		UrlPojo urlPojo = new UrlPojo(url);
		Map<String, Object> parasMap = new HashMap<String, Object>();

		int max_page_number = 1000;
		
		parasMap.put("currPage", 30);
		parasMap.put("params", "");
		parasMap.put("sort", 0);
		urlPojo.setParasMap(parasMap);

		CrawlResultPojo resultPojo = httpClientCrawlerImpl.crawl4Post(urlPojo);

		if (resultPojo != null) {
			System.out.println(resultPojo);
		}
	}
}
