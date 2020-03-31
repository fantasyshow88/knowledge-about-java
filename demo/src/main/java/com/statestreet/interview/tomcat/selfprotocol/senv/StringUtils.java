package com.statestreet.interview.tomcat.selfprotocol.senv;
  
public class StringUtils {  
      
    public static boolean isNullOrBlank(String str) {  
        return str == null || str.length() == 0;  
    }  
      
    public static boolean isNotNullOrBlank(String str) {  
        return !isNullOrBlank(str);  
    }  
  
}  