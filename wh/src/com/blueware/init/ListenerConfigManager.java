package com.blueware.init;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ListenerConfigManager {
	private static final Log logger = LogFactory.getLog(ListenerConfigManager.class);
	private static final String CONFIG_ACCESS_PATH = "/config/token.properties";
	private static final Properties Listener = new Properties();
	
	static {
		InputStream configFileIStream = ListenerConfigManager.class
				.getResourceAsStream(CONFIG_ACCESS_PATH);
		try {
			Listener.load(configFileIStream);
		} catch (IOException e) {
			logger.info("when load " + CONFIG_ACCESS_PATH, e);
			throw new RuntimeException("please check the file " + CONFIG_ACCESS_PATH 
					+ ", it can't be loaded into Properties Object");
		}finally{
			try {
				if(configFileIStream != null){
					configFileIStream.close();
				}
			} catch (IOException e) {
				logger.info("when close " + CONFIG_ACCESS_PATH + " file stream", e);
			}
		}
		
		logger.info(" loaded " + CONFIG_ACCESS_PATH + " OK");
	}
	
	public static String getValue(String key){
		String value = null;
		try{
			value = ((String)Listener.getProperty(key)).trim();
		}catch(Exception e){
			logger.info("when try to get the properties in " + CONFIG_ACCESS_PATH, e);
		}
		return value;
	}
	
}
