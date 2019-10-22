package com.ztl.simple.impl.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ztl.simple.iface.parser.IHtmlParser;
import com.ztl.simple.manager.WangYiDaiCrawlManager;
import com.ztl.simple.utils.JsonOperatorUtil;

/**
 * html source解析类
 * 
 * @author zel
 * 
 */
public class HtmlParserImpl4WangYiDai implements IHtmlParser {
	public static String[] column_key = { "platName", "locationAreaName",
			"locationCityName", "platUrl" };
	
	@Override
	public String parser(String htmlSource) {
		// 解析该json
		JSONObject jsonObj = JsonOperatorUtil.toJSONObject(htmlSource);
		JSONArray jsonArray = JsonOperatorUtil.toJSONArray(jsonObj.get("list")
				.toString());

		StringBuilder stringBuilder = new StringBuilder();
		for (Object json : jsonArray) {
			JSONObject itemJson = (JSONObject) json;
			for (String column : column_key) {
				stringBuilder.append(itemJson.get(column) + "\t");
			}
			stringBuilder.append("\n");
			WangYiDaiCrawlManager.item_count++;
		}
		return stringBuilder.toString();
	}

}
