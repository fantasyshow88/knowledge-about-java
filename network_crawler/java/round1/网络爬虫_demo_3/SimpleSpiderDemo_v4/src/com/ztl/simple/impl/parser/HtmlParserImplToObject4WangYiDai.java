package com.ztl.simple.impl.parser;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ztl.simple.iface.parser.IHtmlParser;
import com.ztl.simple.manager.WangYiDaiCrawlManager;
import com.ztl.simple.pojos.WangYiDaiItemPojo;
import com.ztl.simple.utils.JsonOperatorUtil;

/**
 * html source解析类
 * 
 * @author zel
 * 
 */
public class HtmlParserImplToObject4WangYiDai implements IHtmlParser<List<WangYiDaiItemPojo>> {
	public static String[] column_key = { "platName", "locationAreaName",
			"locationCityName", "platUrl" };
	
	@Override
	public List<WangYiDaiItemPojo> parser(String htmlSource) {
		// 解析该json
		JSONObject jsonObj = JsonOperatorUtil.toJSONObject(htmlSource);
		JSONArray jsonArray = JsonOperatorUtil.toJSONArray(jsonObj.get("list")
				.toString());
		List<WangYiDaiItemPojo> list=new LinkedList<WangYiDaiItemPojo>();
		for (Object json : jsonArray) {
			
			WangYiDaiItemPojo wangYiDaiItemPojo=new WangYiDaiItemPojo();
			JSONObject itemJson = (JSONObject) json;
			wangYiDaiItemPojo.setName(itemJson.get(column_key[0]).toString());
			wangYiDaiItemPojo.setProvince(itemJson.get(column_key[1]).toString());
			wangYiDaiItemPojo.setCity(itemJson.get(column_key[2]).toString());
			wangYiDaiItemPojo.setDomain(itemJson.get(column_key[3]).toString());
			list.add(wangYiDaiItemPojo);
			WangYiDaiCrawlManager.item_count++;
		}
		return list;
	}

}
