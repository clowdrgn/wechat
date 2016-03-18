package com.blueware.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceManager {
	private static final String DATASOURCE_CONFIG_PATH = "/config/datasource.properties";
	private static final Properties DATASOURCE = new Properties();
	static {
		InputStream configFileIStream = DataSourceManager.class
				.getResourceAsStream(DATASOURCE_CONFIG_PATH);
		try {
			DATASOURCE.load(configFileIStream);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(configFileIStream != null){
					configFileIStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getValue(String key){
		String value = null;
		try{
			value = ((String)DATASOURCE.getProperty(key)).trim();
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
	
}
