package com.itmuch.cloud;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class Q extends PropertyPlaceholderConfigurer{
	
	@Override
	protected void convertProperties(Properties props) {
		// TODO Auto-generated method stub
		super.convertProperties(props);
	}
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		// TODO Auto-generated method stub
		return super.convertProperty(propertyName, propertyValue);
	}
	
	@Override
	protected String convertPropertyValue(String originalValue) {
		// TODO Auto-generated method stub
		return super.convertPropertyValue(originalValue);
	}
	
	@Override
	protected void loadProperties(Properties props) throws IOException {
		// TODO Auto-generated method stub
		super.loadProperties(props);
	}
}
