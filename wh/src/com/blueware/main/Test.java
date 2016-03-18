package com.blueware.main;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.blueware.util.Verify;

public class Test {
	
	
	private static String APPKEY="XdtATiOr6caIOPfW";
	private static String token="XxBAUGvKX4SQAQFv13kky8J0yqh3mzBKTrVVbUXIfaBBWW9lGN";
	private static long timestamp=Long.parseLong("1433838389965");
	private static String signature="2afdbedd4724170297e13f48cb14bc8f541880adb750c7bbdbcae364affdabd8";
//	String url="http://jingyan.baidu.com/article/574c52192a1d8d6c8d9dc1ce.html";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			boolean s=Verify.verify(APPKEY, token, timestamp, signature);
			System.out.println("!!!!!!:"+s);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String surl=

	}

}
