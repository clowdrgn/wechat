package com.blueware.kf5.service;


import com.blueware.init.ConfigInfoDepository;
import com.kf5.support.controller.KF5Support;
import com.kf5.support.model.Requester;
import com.kf5.support.model.Ticket;

public class KF5ApiV2 {
        protected static KF5Support kf5Support;
        public static void init(String host, String email, String password){
        	try{
        		kf5Support = new KF5Support();
        		kf5Support.initWithPassword(host, email, password);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
	public static Ticket createAgentOrder(String openId,String content,String title,String email){
			title = "来自微信工单，标题为："+title;
			content = "来自微信工单，openId为："+openId+"； 内容："+content;
			System.out.println(title+content);
			KF5ApiV2.init(ConfigInfoDepository.Kf5.HOST, ConfigInfoDepository.Kf5.EMAIL, ConfigInfoDepository.Kf5.PASSWORD);
			String jsonString = "{ticket:{title: \""+title+"\",\"comment\": {\"content\": \""+content+"\"},\"priority\":\"high\",\"requester\":{\"email\": \""+email+"\",\"name\": \""+email+"\" },\"custom_fields\": [{\"name\": \"field_3361\", \"value\": \"Application Insight\"},{\"name\": \"field_3363\", \"value\": \"Java\"}]}}";
			System.out.println(jsonString);
			Ticket t = kf5Support.createAgentOrder(jsonString);
			return t!=null?t:null;
	}
	public static Ticket getAgentTicketDetail(String ticketId){
		KF5ApiV2.init(ConfigInfoDepository.Kf5.HOST, ConfigInfoDepository.Kf5.EMAIL, ConfigInfoDepository.Kf5.PASSWORD);
		return kf5Support.getAgentTicketDetail(ticketId);
	}
	public static Ticket createAgentOrder(String jsonString){
		return kf5Support.createAgentOrder(jsonString);
	}
	public static void main(String[] args) {
		createAgentOrder("obiHwsgUhayEFPvhIy8bTan0OIaU", "测试", "ceshi", "lijiangtest@qq.com");
	}
}
