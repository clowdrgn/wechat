package com.blueware.init;

public class ConfigInfoDepository {
	public static class WorkTime {
		public static final String TOKEN = ListenerConfigManager.getValue("token");
		public static final String APPID = ListenerConfigManager.getValue("appid");
		public static final String SECRET = ListenerConfigManager.getValue("secret");
		public static final String WEBTOKEN = ListenerConfigManager.getValue("webToken");
		public static final String USERINFO = ListenerConfigManager.getValue("userInfo");
		public static final String DRIVER = DataSourceManager.getValue("source.driverClassName");
		public static final String URL = DataSourceManager.getValue("source.url");
		public static final String USERNAME = DataSourceManager.getValue("source.username");
		public static final String PASSWORD = DataSourceManager.getValue("source.password");
	}
	public static class Kf5{
		public static final String HOST = KF5ConfigManager.getValue("host");
		public static final String EMAIL = KF5ConfigManager.getValue("email");
		public static final String PASSWORD = KF5ConfigManager.getValue("password");
	}
	

}
