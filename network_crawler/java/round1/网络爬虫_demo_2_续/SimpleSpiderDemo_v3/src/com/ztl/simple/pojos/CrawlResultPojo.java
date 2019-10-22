package com.ztl.simple.pojos;

/**
 * ץȡ���ķ�װ
 * 
 * @author zel
 * 
 */
public class CrawlResultPojo {
	@Override
	public String toString() {
		return "CrawlResultPojo [httpStatuCode=" + httpStatuCode
				+ ", isSuccess=" + isSuccess + ", pageContent=" + pageContent
				+ "]";
	}

	private boolean isSuccess;
	private String pageContent;
	private int httpStatuCode;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public int getHttpStatuCode() {
		return httpStatuCode;
	}

	public void setHttpStatuCode(int httpStatuCode) {
		this.httpStatuCode = httpStatuCode;
	}

}
