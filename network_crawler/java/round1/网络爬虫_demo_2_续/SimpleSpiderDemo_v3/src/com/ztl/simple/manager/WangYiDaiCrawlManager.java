package com.ztl.simple.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ztl.simple.impl.crawl.HttpClientCrawlerImpl;
import com.ztl.simple.pojos.CrawlResultPojo;
import com.ztl.simple.pojos.UrlPojo;
import com.ztl.simple.utils.IOUtil;
import com.ztl.simple.utils.JsonOperatorUtil;

/**
 * 网易贷抓取管理器
 * 
 * @author zel
 * 
 */
public class WangYiDaiCrawlManager {
	public static HttpClientCrawlerImpl httpClientCrawlerImpl = new HttpClientCrawlerImpl();
	public static String[] column_key = { "platName", "locationAreaName",
			"locationCityName", "platUrl" };
	public static int item_count = 0;

	private static CrawlResultPojo crawlOnePage(UrlPojo urlPojo) {
		CrawlResultPojo resultPojo = httpClientCrawlerImpl.crawl4Post(urlPojo);
		return resultPojo;
	}

	public static String parserOnePage(String jsonStr) {
		// 解析该json
		JSONObject jsonObj = JsonOperatorUtil.toJSONObject(jsonStr);
		JSONArray jsonArray = JsonOperatorUtil.toJSONArray(jsonObj.get("list")
				.toString());

		StringBuilder stringBuilder = new StringBuilder();
		for (Object json : jsonArray) {
			JSONObject itemJson = (JSONObject) json;
			for (String column : column_key) {
				stringBuilder.append(itemJson.get(column) + "\t");
			}
			stringBuilder.append("\n");
			item_count++;
		}
		return stringBuilder.toString();
	}

	public static void processWangYiDai(String url, int max_page_number,
			String filePath) {
		// 存储所有的抓取条目
		StringBuilder all_items = new StringBuilder();

		UrlPojo urlPojo = new UrlPojo(url);
		Map<String, Object> parasMap = new HashMap<String, Object>();
		int have_download_page_count = 0;
		Set<String> uniqSet = new HashSet<String>();

		for (int pageNumber = 1; pageNumber <= max_page_number; pageNumber++) {
			parasMap.put("currPage", pageNumber);
			parasMap.put("params", "");
			parasMap.put("sort", 0);
			urlPojo.setParasMap(parasMap);

			CrawlResultPojo resultPojo = crawlOnePage(urlPojo);
			if (uniqSet.contains(resultPojo.getPageContent())) {
				System.out.println("碰到重复，代表已抓取完成!");
				break;
			} else {
				uniqSet.add(resultPojo.getPageContent());
			}
			if (resultPojo != null) {
				String content = resultPojo.getPageContent();
				String page_items = parserOnePage(content);
				all_items.append(page_items);
				have_download_page_count++;
			}
		}
		System.out.println("all items size---" + item_count);
		System.out.println("已经下载了---" + have_download_page_count);

		IOUtil.writeFile(filePath, all_items.toString(), "utf-8");
		System.out.println("save successfully~");
	}

	public static void main(String[] args) {
		String url = "http://www.wangdaizhijia.com/front_select-plat";
		int max_page_number = 100;
		String fileName = "网易贷_数据集.txt";
		processWangYiDai(url, max_page_number, fileName);

		System.out.println("done!");
	}
}
