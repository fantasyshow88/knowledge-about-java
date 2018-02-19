package com.statestr.java.day01;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocationHeaderTest extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		test5(response);
	}

	/**
	 * 下载
	 * @param response
	 * @throws IOException
	 */
	private void test5(HttpServletResponse response) throws IOException {
		response.setHeader("Content-disposition", "attachment;filename=http请求.bmp");
		InputStream in = this.getServletContext().getResourceAsStream("/http请求.bmp");
		int len = 0;
		byte[] buffer = new byte[1024];
		ServletOutputStream out = response.getOutputStream();
		while((len=in.read(buffer))!= -1){
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
	}

	/**
	 * 定时刷新
	 * @param response
	 * @throws IOException
	 */
	private void test4(HttpServletResponse response) throws IOException {
		String a = "aaaaaa";
		response.setHeader("Refresh", "3");//隔3s刷新数据
//		response.setHeader("Refresh", "3;url=http://www.baidu.com");
		response.getOutputStream().write(a.getBytes());
	}

	/**
	 * 通过content-type 控制浏览器以那种格式处理数据
	 * @param response
	 * @throws IOException
	 */
	private void test3(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Type", "image/bmp");
		
		InputStream in = this.getServletContext().getResourceAsStream("/http请求.bmp");
		int len = 0;
		byte[] buffer = new byte[1024];
		ServletOutputStream out = response.getOutputStream();
		while((len=in.read(buffer))!= -1){
			out.write(buffer,0,len);
		}
		in.close();
		out.close();
	}

	/**
	 * 压缩数据输出
	 * @param response
	 * @throws IOException
	 */
	private void test2(HttpServletResponse response) throws IOException {
		String a = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
		System.out.println("原始数据大小：" + a.getBytes().length);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();//底层流
		GZIPOutputStream gout = new GZIPOutputStream(bout);//包装流一般有缓冲，要缓冲满了才会写
		gout.write(a.getBytes());
		gout.close();//一定要调用这个方法 刷新缓冲
		
		byte[] bzip = bout.toByteArray();
		
		System.out.println("压缩后数据大小：" + bzip.length);
		
		response.setHeader("Content-Encoding", "gzip");//通知浏览器数据采用的压缩格式
		response.setHeader("Content-Length", bzip.length+"");//和content-Encoding对应的头 需要写length
		response.getOutputStream().write(bzip);
	}

	/**
	 * 请求重定向（用状态码 302和 location 头）
	 * @param response
	 */
	private void test1(HttpServletResponse response) {
		response.setStatus(302);
		response.setHeader("location", "/java_web/1.html");
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
