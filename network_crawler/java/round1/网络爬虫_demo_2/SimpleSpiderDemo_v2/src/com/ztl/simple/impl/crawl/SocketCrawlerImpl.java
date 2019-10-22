package com.ztl.simple.impl.crawl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.ztl.simple.iface.crawl.ICrawler;
import com.ztl.simple.pojos.CrawlResultPojo;
import com.ztl.simple.pojos.UrlPojo;

public class SocketCrawlerImpl implements ICrawler {

	@Override
	public CrawlResultPojo crawl(UrlPojo urlPojo) {
		CrawlResultPojo crawlResultPojo = new CrawlResultPojo();
		if (urlPojo == null || urlPojo.getUrl() == null) {
			crawlResultPojo.setSuccess(false);
			crawlResultPojo.setPageContent(null);

			return crawlResultPojo;
		}

		String host = urlPojo.getHost();
		if (host == null) {
			crawlResultPojo.setSuccess(false);
			crawlResultPojo.setPageContent(null);
			return crawlResultPojo;
		}
		BufferedWriter bw = null;
		BufferedReader br = null;

		try {
			Socket socket = new Socket(host, 80);
			socket.setKeepAlive(true);
			// socket.setSoTimeout(1000);
			bw = new BufferedWriter(new OutputStreamWriter(socket
					.getOutputStream()));

			bw.write("GET " + urlPojo.getUrl() + " HTTP/1.0\r\n");
			bw.write("HOST:" + host + "\r\n");
			bw.write("\r\n");// ���еĽ����\r\n֮ǰû���κ���ݣ�˵����ʱ��http head���������������
			bw.flush();// ��ջ�����

			br = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			StringBuilder stringBuilder = new StringBuilder();

			String line = null;
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				stringBuilder.append(line + "\n");
			}
			crawlResultPojo.setSuccess(true);
			crawlResultPojo.setPageContent(stringBuilder.toString());

			return crawlResultPojo;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("������δ�رգ�����!");
			}
		}
		return null;
	}

	public static void main(String[] args) {
		SocketCrawlerImpl socketCrawlerImpl = new SocketCrawlerImpl();
		UrlPojo urlPojo = new UrlPojo("http://www.baidu.com");
		// UrlPojo urlPojo = new UrlPojo("http://www.qq.com");
		// UrlPojo urlPojo = new UrlPojo(
		// "http://www.hao123.com/?tn=97961594_hao_pg");
		CrawlResultPojo crawlResultPojo=socketCrawlerImpl.crawl(urlPojo);

		System.out.println(crawlResultPojo.getPageContent());
		
		System.out.println("done!");
	}

}
