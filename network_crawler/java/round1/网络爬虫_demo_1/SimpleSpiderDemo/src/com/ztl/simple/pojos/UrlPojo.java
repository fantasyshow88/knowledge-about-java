package com.ztl.simple.pojos;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.ztl.simple.enumeration.TaskLevel;

/**
 * url任务的pojo类
 * 
 * @author zel
 * 
 */
public class UrlPojo {
	public UrlPojo(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "UrlPojo [taskLevel=" + taskLevel + ", url=" + url + "]";
	}

	public UrlPojo(String url, TaskLevel taskLevel) {
		this.url = url;
		this.taskLevel = taskLevel;
	}

	private String url;
	private TaskLevel taskLevel = TaskLevel.MIDDLE;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TaskLevel getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(TaskLevel taskLevel) {
		this.taskLevel = taskLevel;
	}

	public HttpURLConnection getConnection() {
		try {
			URL url = new URL(this.url);
			URLConnection connection = url.openConnection();
			if (connection instanceof HttpURLConnection) {
				return (HttpURLConnection) connection;
			}else {
			  throw new Exception("connection is errr!");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getHost() {
		try {
			URL url = new URL(this.url);
			return url.getHost();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
