package com.ztl.simple.utils;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * json解析工具类
 * 
 * @author zel
 * 
 */
public class JsonOperatorUtil {
	public static JSONObject toJSONObject(String str) {
		return (JSONObject) JSONValue.parse(str);
	}

	public static JSONArray toJSONArray(String str) {
		return (JSONArray) JSONValue.parse(str);
	}

	public static void main(String[] args) {
		String str = "[{\"one\":1,\"two\":\"2\"}]";
//		JSONObject jsonObject = JsonOperatorUtil.toJSONObject(str);
		JSONArray jsonObject = JsonOperatorUtil.toJSONArray(str);
		
		Iterator<JSONObject> iterator=jsonObject.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
