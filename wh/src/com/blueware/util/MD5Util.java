package com.blueware.util;

import java.security.MessageDigest;

public class MD5Util {
	   public final static String MD5(String s) {
	        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

	        try {
	            byte[] btInput = s.getBytes();
	            MessageDigest mdInst = MessageDigest.getInstance("MD5");
	            mdInst.update(btInput);
	            byte[] md = mdInst.digest();
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            return new String(str);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	   public static String KL(String inStr) {    
		   char[] a = inStr.toCharArray();    
		   for (int i = 0; i < a.length; i++) {    
		    a[i] = (char) (a[i] ^ 't');    
		   }    
		   String s = new String(a);    
		   return s;    
		  }    
		     
	   public static String JM(String inStr) {    
		   char[] a = inStr.toCharArray();    
		   for (int i = 0; i < a.length; i++) {    
		    a[i] = (char) (a[i] ^ 't');    
		   }    
		   String k = new String(a);    
		   return k;    
		  }    
	   public static void main(String[] args) {
		String s = "384592648@qq.com";
		System.out.println("原始：" + s);    
		  System.out.println("MD5后：" + MD5(s));    
		  System.out.println("MD5后再加密：" + KL(MD5(s)));    
		  System.out.println("解密为MD5后的：" + JM(KL(MD5(s))));    
	}
}
