package com.ztl.simple.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件IO
 * 
 * @author zel
 * 
 */
public class IOUtil {
	public static void writeFile(String filePath, String value, String encoding) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(filePath));
			fos.write(value.getBytes(encoding));
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		String filePath = "test.txt";
		String value = "中国人民万岁,hello world,123";
		String encoding = "utf-8";

		IOUtil.writeFile(filePath, value, encoding);

		System.out.println("done!");
	}
}
