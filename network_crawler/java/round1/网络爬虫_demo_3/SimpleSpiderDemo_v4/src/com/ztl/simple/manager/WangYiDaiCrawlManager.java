package com.ztl.simple.manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ztl.simple.iface.parser.IHtmlParser;
import com.ztl.simple.iface.save.ISave;
import com.ztl.simple.impl.crawl.HttpClientCrawlerImpl;
import com.ztl.simple.impl.parser.HtmlParserImpl4WangYiDai;
import com.ztl.simple.impl.save.SaveImplToFile4WangYiDai;
import com.ztl.simple.pojos.CrawlResultPojo;
import com.ztl.simple.pojos.UrlPojo;
import com.ztl.simple.pojos.WangYiDaiItemPojo;

/**
 * 网易贷抓取管理器
 * 
 * @author zel
 * 
 */
public class WangYiDaiCrawlManager {
	// public static HttpClientCrawlerImpl httpClientCrawlerImpl = new
	// HttpClientCrawlerImpl();
	public static IHtmlParser<String> htmlParser = new HtmlParserImpl4WangYiDai();
	public static String[] column_key = { "platName", "locationAreaName",
			"locationCityName", "platUrl" };
	public static int item_count = 0;

	public static void processWangYiDai(String url, int max_page_number,
			String filePath) {
		// 负责抓取到的文件的存储
		ISave<String> iSave = new SaveImplToFile4WangYiDai(filePath);
		// 存储所有的抓取条目
		StringBuilder all_items = new StringBuilder();
		// List<WangYiDaiItemPojo> all_items = new
		// LinkedList<WangYiDaiItemPojo>();

		UrlPojo urlPojo = new UrlPojo(url);
		Map<String, Object> parasMap = new HashMap<String, Object>();
		int have_download_page_count = 0;
		Set<String> uniqSet = new HashSet<String>();

		for (int pageNumber = 1; pageNumber <= max_page_number; pageNumber++) {
			parasMap.put("currPage", pageNumber);
			parasMap.put("params", "");
			parasMap.put("sort", 0);
			urlPojo.setParasMap(parasMap);

			HttpClientCrawlerImpl httpClientCrawlerImpl = new HttpClientCrawlerImpl(
					urlPojo);
			CrawlResultPojo resultPojo = httpClientCrawlerImpl.crawl();

			if (uniqSet.contains(resultPojo.getPageContent())) {
				System.out.println("碰到重复，代表已抓取完成!");
				break;
			} else {
				uniqSet.add(resultPojo.getPageContent());
			}
			if (resultPojo != null) {
				String content = resultPojo.getPageContent();
				String page_items = htmlParser.parser(content);
				// List<WangYiDaiItemPojo> list = htmlParser.parser(content);

				all_items.append(page_items);
				// all_items.addAll(list);
				have_download_page_count++;
			}
		}
		System.out.println("all items size---" + item_count);
		System.out.println("已经下载了---" + have_download_page_count);

		iSave.save(all_items.toString());

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
