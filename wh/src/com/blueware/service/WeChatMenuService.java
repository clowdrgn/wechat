package com.blueware.service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blueware.dao.wechat.WeChatTokenDaoImpl;
import com.blueware.init.ConfigInfoDepository;
import com.blueware.util.OneTools;
import com.blueware.util.db.MongoConnection;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

import net.sf.json.JSONObject;

public class WeChatMenuService extends OneTools{
	private static String appid = ConfigInfoDepository.WorkTime.APPID;
	private static String secret = ConfigInfoDepository.WorkTime.SECRET;
	private static String urlStr = "https://api.weixin.qq.com/cgi-bin/token";
	private static final Logger LOG = LoggerFactory.getLogger(WeChatMenuService.class);
		public static void updateAccessToken(){
			Map<String, Object> paramMap = new HashMap<String,Object>();
			paramMap.put("grant_type", "client_credential");
			paramMap.put("appid", appid);
			paramMap.put("secret", secret);
			try {
				String result = doGet(urlStr, paramMap);
				System.out.println(result);
				JSONObject jsonObject = JSONObject.fromObject(result);
				String access_token = jsonObject.getString("access_token");
				if(! WeChatTokenDaoImpl.getInstance().exists()){
		    		 WeChatTokenDaoImpl.getInstance().insert(access_token);
		    	 }else{
		    		 WeChatTokenDaoImpl.getInstance().update(access_token);
		    	 }
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error(e.getMessage(), e);
			}
		}
		public static void createOwnMenue(){
			String menu = "{\"button\":[{\"name\":\"帮助\",\"sub_button\":[{\"type\":\"click\",\"name\":\"创建工单\",\"key\":\"commitKf5\"},{\"type\":\"view\",\"name\":\"查看工单\",\"url\":\"https://oneapm.kf5.com/user/access\"}]},{\"type\":\"view\",\"name\":\"文档\",\"url\":\"http://support.oneapm.com/\"},{\"type\":\"click\",\"name\":\"免费注册\",\"key\":\"register\"}]}";
	        String access_token= "oDDTPCSbX7iJoAgMB-65gVnr8jzGqIPcEqh_ixjIcI9t132zUwfJnr_NFvuyzJH8WibnLMYIAEiWdMywfOYHEBt703C2eOA2IOBDqCLTLfLqIF4T8YhyySfvXBnx9BnNXNOhAGANDE";
	        String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
	        JSONObject json = OneTools.httpsRequest(action, "POST", menu);
	        System.out.println(json.toString());
		}
		public static void main(String[] args) {
			createOwnMenue();
		}
		
}
