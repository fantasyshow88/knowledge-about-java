package com.statestr.java.day03_request_response;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletDemo1 extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("").forward(request, response);
		
		response.sendRedirect("");
		
		this.getServletContext().getRealPath("");
		
		this.getServletContext().getResourceAsStream("");
		
		HttpSession session = request.getSession();
		session.invalidate();
	
		
		
		
	}
	
	/**
	 * 控制浏览器缓存
	 * @param response
	 * @throws IOException
	 */
	private void test5(HttpServletResponse response) throws IOException{
		response.setDateHeader("expires", System.currentTimeMillis() + 1000*3600);
		String a = "aaa";
		response.getWriter().write(a);
	}

	/**
	 * 下载中文乱码问题
	 * @param response
	 * @throws IOException
	 */
	private void test4(HttpServletResponse response) throws IOException{
		String filename="http请求.bmp";
		//如果下载文件是中文名则需要对文件名进行url编码
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename,"UTF-8"));
		
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
	 * printwriter向response写数据时的编码设置
	 * @param response
	 * @throws IOException
	 */
	private void test3(HttpServletResponse response) throws IOException {
		String data = "中国";
		//更改reponse使用的码表,即用printwriter写到response中时用utf-8编码，以控制response以什么码表向浏览器写数据
		response.setCharacterEncoding("UTF-8");
		//控制浏览器打开时候的码表
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter wr = response.getWriter();
		//写到response中，最后要把“中国”变为数字，默认的码表是iso-8859-1码表，所以需要调用response.setCharacterEncoding（）
		wr.write(data);
	}
	
	/**
	 * 用html技术中meta 标签控制 http 浏览器编码格式，模拟http响应头
	 * @param response
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private void test2(HttpServletResponse response) throws IOException,
	UnsupportedEncodingException {
	String data = "中国";
	ServletOutputStream out = response.getOutputStream();
	//用html技术中meta 标签控制 http 浏览器编码格式，模拟http响应头
	out.write("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>".getBytes());
	out.write(data.getBytes("UTF-8"));
}

	/**
	 * 控制浏览器以何种编码打开
	 * @param response
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private void test1(HttpServletResponse response) throws IOException,
			UnsupportedEncodingException {
		//控制浏览器以何种码表打开
		response.setContentType("text/html;charset=UTF-8");
//		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		String data = "中国";
		response.getOutputStream().write(data.getBytes("UTF-8"));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

}
