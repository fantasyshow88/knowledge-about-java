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
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.ztl.simple.iface.crawl.ICrawler;
import com.ztl.simple.manager.httpclient.HttpClientManager;
import com.ztl.simple.pojos.CrawlResultPojo;
import com.ztl.simple.pojos.UrlPojo;
import com.ztl.simple.pojos.enumeration.HttpMethodEnum;

public class HttpClientCrawlerImpl implements ICrawler {
	private UrlPojo urlPojo;

	public UrlPojo getUrlPojo() {
		return urlPojo;
	}

	public void setUrlPojo(UrlPojo urlPojo) {
		this.urlPojo = urlPojo;
	}

	public void init() {
		System.out.println("init successfully!");
	}

	public HttpClientCrawlerImpl(UrlPojo urlPojo) {
		this.urlPojo = urlPojo;
	}

	/**
	 * 传入加入参数post参数的url pojo
	 */
	@Override
	public CrawlResultPojo crawl() {
		if (urlPojo == null) {
			return null;
		}
		CrawlResultPojo crawlResultPojo = new CrawlResultPojo();
		CloseableHttpResponse response1 = null;
		BufferedReader br = null;
		try {
			RequestBuilder rb = null;
			if (urlPojo.getHttpMethodEnum() == HttpMethodEnum.GET) {
				rb = RequestBuilder.get().setUri(new URI(urlPojo.getUrl()));
			} else {
				rb = RequestBuilder.post().setUri(new URI(urlPojo.getUrl()));
			}
			Map<String, Object> parasMap = urlPojo.getParasMap();
			if (parasMap != null) {
				for (Entry<String, Object> entry : parasMap.entrySet()) {
					rb
							.addParameter(entry.getKey(), entry.getValue()
									.toString());
				}
			}
			HttpUriRequest httpRequest = rb.build();
			response1 = HttpClientManager.getClient().execute(httpRequest);
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
		String url = "http://www.wangdaizhijia.com/front_select-plat";
		UrlPojo urlPojo = new UrlPojo(url);
		urlPojo.setHttpMethodEnum(HttpMethodEnum.POST);
		HttpClientCrawlerImpl httpClientCrawlerImpl = new HttpClientCrawlerImpl(
				urlPojo);
		Map<String, Object> parasMap = new HashMap<String, Object>();

		int max_page_number = 1000;

		parasMap.put("currPage", 1);
		parasMap.put("params", "");
		parasMap.put("sort", 0);
		urlPojo.setParasMap(parasMap);

		CrawlResultPojo resultPojo = httpClientCrawlerImpl.crawl();

		if (resultPojo != null) {
			System.out.println(resultPojo);
		}
	}
}
