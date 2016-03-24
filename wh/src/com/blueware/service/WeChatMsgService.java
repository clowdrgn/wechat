package com.blueware.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.blueware.dao.info.SNSUserDaoImpl;
import com.blueware.dao.wechat.WeChatKf5DaoImpl;
import com.blueware.entity.wechat.Kf5Dto;
import com.blueware.entity.wechat.response.Article;
import com.blueware.entity.wechat.response.NewsMessage;
import com.blueware.entity.wechat.response.TextMessage;
import com.blueware.init.ConfigInfoDepository;
import com.blueware.kf5.service.KF5ApiV2;
import com.blueware.util.MessageUtil;
import com.blueware.util.XmlParse;
import com.blueware.wechat.oauth2.SNSUserInfo;
import com.kf5.support.model.Requester;
import com.kf5.support.model.Ticket;

public class WeChatMsgService {
	 public static String processRequest(HttpServletRequest request) {  
	        String respMessage = null;  
	        try {  
	            // 默认返回的文本消息内容  
	            String respContent = "请求处理异常，请稍候尝试！";  
	            // xml请求解析  
	            Map<String, String> requestMap = XmlParse.parseXml(request);  
	  
	            // 发送方帐号（open_id）  
	            String fromUserName = requestMap.get("FromUserName");  
	            // 公众帐号  
	            String toUserName = requestMap.get("ToUserName");  
	            // 消息类型  
	            String msgType = requestMap.get("MsgType");
	            // 事件类型
	            String eventKey = requestMap.get("EventKey");
	            //回复图文消息
	            List<Article> arlist = new ArrayList<Article>();
	            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ConfigInfoDepository.WorkTime.APPID+"&redirect_uri=http%3A%2F%2Faugur.oneapm.com%2Foauthservlet%3Fyou%3D123&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
	            Article article = new Article();
	            article.setTitle("请您完善信息");
	            article.setDescription("请填写电话和邮箱");
	            article.setUrl(url);
	            arlist.add(article);
	            
	            //图文消息创建工单内容
	            String urlGongdan = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ConfigInfoDepository.WorkTime.APPID+"&redirect_uri=http%3A%2F%2Faugur.oneapm.com%2FcreateKf5%3FopenId%3D"+fromUserName+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
	            List<Article> gongdanArs = new ArrayList<Article>();
	            Article gongdanAr = new Article();
	            gongdanAr.setTitle("请您填写工单描述");
	            gongdanAr.setDescription("请填写工单标题以及问题简述，我们会及时处理");
	            gongdanAr.setUrl(urlGongdan);
	            gongdanArs.add(gongdanAr);
	            
	            
	            if(judgeUserInfo(fromUserName)){
            		respContent = "感谢您的关注！";
            		respMessage = textReply(toUserName, fromUserName, respContent);
            	}else{
            		respMessage = newsReply(toUserName, fromUserName, arlist);
            	}
	            // 文本消息  
	            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
	                respContent = "您发送的是文本消息！";  
	            }  
	            // 图片消息  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
	                respContent = "您发送的是图片消息！";  
	            }  
	            // 地理位置消息  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
	                respContent = "您发送的是地理位置消息！";  
	            }  
	            // 链接消息  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
	                respContent = "您发送的是链接消息！";  
	            }  
	            // 音频消息  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
	                respContent = "您发送的是音频消息！";  
	            }  
	            // 事件推送  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
	                // 事件类型  
	                String eventType = requestMap.get("Event");  
	                // 订阅  
	                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
	                    respContent = "谢谢您的关注！";  
	                }  
	                // 取消订阅  
	                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
	                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
	                }  
	                // 自定义菜单点击事件  
	                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
	                	if(eventKey.equals("commitKf5")){
	                		if(judgeUserInfo(fromUserName)){
	                			//已经填写邮箱，创建工单
	                			respMessage = newsReply(toUserName, fromUserName, gongdanArs);
	                		}else{
	                			//未填写邮箱，引导填写
	                			respMessage = newsReply(toUserName, fromUserName, arlist);
	                		}
	                	}
	                	if(eventKey.equals("register")){
	                			respMessage = textReply(toUserName, fromUserName, "暂不提供注册服务，请前往http://user.oneapm.com/pages/v2/signup进行注册，感谢您的支持！");
	                	}
	                	if(eventKey.equals("viewKf5")){
	                		if(judgeUserInfo(fromUserName)){
	                			List<Ticket> tickets = findKf5TicketsByOpenId(fromUserName);
	                			List<Article> kf5List = new ArrayList<Article>();
	                			for(Ticket t : tickets){
	                				Article kf5 = new Article();
	                				kf5.setTitle("创建于"+t.getCreatedAt()+"的工单处理进度:"+t.getStatus());
	                				kf5.setDescription("创建于"+t.getCreatedAt()+"单号为"+t.getId()+"的工单处理进度:"+t.getStatus());
	                				String kf5Url = t.getUrl();
	                				kf5Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ConfigInfoDepository.WorkTime.APPID+"&redirect_uri=http%3A%2F%2Faugur.oneapm.com%2FviewKf5%3Fticket%3D"+ kf5Url.substring(37, 43)+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
	                				kf5.setUrl(kf5Url);
	                				kf5List.add(kf5);
	                			}
	                			if(tickets.size() > 0){
	                				respMessage = newsReply(toUserName, fromUserName, kf5List);
	                			}else{
	                				respMessage = textReply(toUserName, fromUserName, "您没有创建过任何工单！");
	                			}
	                			//已经填写邮箱，查看工单
	                		}else{
	                			//未填写邮箱，引导填写
	                			respMessage = newsReply(toUserName, fromUserName, arlist);
	                		}
	                	}
	                	
	                }  
	            }  
	            	
	           
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	  
	        return respMessage;  
	    }  
	 /**
	  * 回复文本消息
	 * @param toUserName
	 * @param fromUserName
	 * @param time
	 * @param msgType
	 * @param eventKey
	 * @return
	 */
	public static String textReply(String toUserName,String fromUserName,String respContent){
         TextMessage textMessage = new TextMessage();  
         textMessage.setToUserName(fromUserName);  
         textMessage.setFromUserName(toUserName);  
         textMessage.setCreateTime(new Date().getTime());  
         textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
         textMessage.setFuncFlag(0);
         textMessage.setContent(respContent); 
         String respMessage = MessageUtil.textMessageToXml(textMessage); 
         return respMessage;
	 }
	/**
	 * 回复图文消息
	 * @param toUserName
	 * @param fromUserName
	 * @param arlist
	 * @return
	 */
	public static String newsReply(String toUserName,String fromUserName,List<Article> arlist){
		  //回复图文消息
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(fromUserName);  
        newsMessage.setFromUserName(toUserName);  
        newsMessage.setCreateTime(new Date().getTime());  
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
        newsMessage.setFuncFlag(0);
        newsMessage.setArticleCount(arlist.size());
        newsMessage.setArticles(arlist);
        String respMessage = MessageUtil.newsMessageToXml(newsMessage);
        return respMessage;
	}
	 public static boolean judgeUserInfo(String openId){
		 SNSUserInfo info = SNSUserDaoImpl.getInstance().findByOpenId(openId);
		 if(info == null ||(info.getEmail() == null && info.getEmail().length() < 2)){
			 return false;
		 } else{
			 return true;
		 }
	 }
	 public static String findKf5ByOpenId(String openId){
		 StringBuilder sb = new StringBuilder();
		 List<Kf5Dto> list = WeChatKf5DaoImpl.getInstance().findByOpenId(openId);
		 for(Kf5Dto dto : list){
			 String url = dto.getUrl();
			 String orderId = url.substring(38, url.length());
			 Ticket re = KF5ApiV2.getAgentTicketDetail(orderId);
			 if(re != null){
				 sb.append("您的工单处理进度：").append(re.getStatus()).append("\r\n").append("查看地址:").append(dto.getUrl()).append(";\r\n");
			 }
		 }
		 return sb.toString();
	 }
	 public static List<Ticket> findKf5TicketsByOpenId(String openId){
		 List<Ticket> tickets = new ArrayList<Ticket>();
		 List<Kf5Dto> list = WeChatKf5DaoImpl.getInstance().findByOpenId(openId);
		 if(list != null && list.size() > 0){
			 for(Kf5Dto dto : list){
				 String url = dto.getUrl();
				 String orderId = url.substring(38, url.length());
				 Ticket re = KF5ApiV2.getAgentTicketDetail(orderId);
				 if(re != null){
					 tickets.add(re);
				 }
			 }
		 }
		 return tickets;
	 }
	 public static void main(String[] args) {
		String content = findKf5ByOpenId("obiHwsgUhayEFPvhIy8bTan0OIaU");
		System.out.println(content);
	}
}
