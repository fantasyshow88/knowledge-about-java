package com.statestreet.interview.tomcat.selfprotocol.senv;
import java.net.URLStreamHandler;  
import java.net.URLStreamHandlerFactory;  
  
/** 
 * <p> 
 *       自定义协议的处理器工厂，负责针对每种自定义的协议而返回它们各自对应的协议处理器 
 *       如果要用上述的查找规则1来安装协议处理器时，则需要用到这个类 
 *</p>  
 */  
public class CustomProtocolFactory implements URLStreamHandlerFactory {

	
	@Override
    public URLStreamHandler createURLStreamHandler(String protocol) {  
        if ("senv".equalsIgnoreCase(protocol))  
            return new Handler();  
        return null;  
    } 
  
 
  
}  