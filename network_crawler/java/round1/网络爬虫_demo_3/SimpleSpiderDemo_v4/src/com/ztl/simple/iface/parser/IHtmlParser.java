package com.ztl.simple.iface.parser;

public interface IHtmlParser<T> {
	public T parser(String htmlSource);
}
