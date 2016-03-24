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
	            // Ĭ�Ϸ��ص��ı���Ϣ����  
	            String respContent = "�������쳣�����Ժ��ԣ�";  
	            // xml�������  
	            Map<String, String> requestMap = XmlParse.parseXml(request);  
	  
	            // ���ͷ��ʺţ�open_id��  
	            String fromUserName = requestMap.get("FromUserName");  
	            // �����ʺ�  
	            String toUserName = requestMap.get("ToUserName");  
	            // ��Ϣ����  
	            String msgType = requestMap.get("MsgType");
	            // �¼�����
	            String eventKey = requestMap.get("EventKey");
	            //�ظ�ͼ����Ϣ
	            List<Article> arlist = new ArrayList<Article>();
	            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ConfigInfoDepository.WorkTime.APPID+"&redirect_uri=http%3A%2F%2Faugur.oneapm.com%2Foauthservlet%3Fyou%3D123&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
	            Article article = new Article();
	            article.setTitle("����������Ϣ");
	            article.setDescription("����д�绰������");
	            article.setUrl(url);
	            arlist.add(article);
	            
	            //ͼ����Ϣ������������
	            String urlGongdan = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ConfigInfoDepository.WorkTime.APPID+"&redirect_uri=http%3A%2F%2Faugur.oneapm.com%2FcreateKf5%3FopenId%3D"+fromUserName+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
	            List<Article> gongdanArs = new ArrayList<Article>();
	            Article gongdanAr = new Article();
	            gongdanAr.setTitle("������д��������");
	            gongdanAr.setDescription("����д���������Լ�������������ǻἰʱ����");
	            gongdanAr.setUrl(urlGongdan);
	            gongdanArs.add(gongdanAr);
	            
	            
	            if(judgeUserInfo(fromUserName)){
            		respContent = "��л���Ĺ�ע��";
            		respMessage = textReply(toUserName, fromUserName, respContent);
            	}else{
            		respMessage = newsReply(toUserName, fromUserName, arlist);
            	}
	            // �ı���Ϣ  
	            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
	                respContent = "�����͵����ı���Ϣ��";  
	            }  
	            // ͼƬ��Ϣ  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
	                respContent = "�����͵���ͼƬ��Ϣ��";  
	            }  
	            // ����λ����Ϣ  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
	                respContent = "�����͵��ǵ���λ����Ϣ��";  
	            }  
	            // ������Ϣ  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
	                respContent = "�����͵���������Ϣ��";  
	            }  
	            // ��Ƶ��Ϣ  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
	                respContent = "�����͵�����Ƶ��Ϣ��";  
	            }  
	            // �¼�����  
	            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
	                // �¼�����  
	                String eventType = requestMap.get("Event");  
	                // ����  
	                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
	                    respContent = "лл���Ĺ�ע��";  
	                }  
	                // ȡ������  
	                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
	                    // TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ  
	                }  
	                // �Զ���˵�����¼�  
	                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
	                	if(eventKey.equals("commitKf5")){
	                		if(judgeUserInfo(fromUserName)){
	                			//�Ѿ���д���䣬��������
	                			respMessage = newsReply(toUserName, fromUserName, gongdanArs);
	                		}else{
	                			//δ��д���䣬������д
	                			respMessage = newsReply(toUserName, fromUserName, arlist);
	                		}
	                	}
	                	if(eventKey.equals("register")){
	                			respMessage = textReply(toUserName, fromUserName, "�ݲ��ṩע�������ǰ��http://user.oneapm.com/pages/v2/signup����ע�ᣬ��л����֧�֣�");
	                	}
	                	if(eventKey.equals("viewKf5")){
	                		if(judgeUserInfo(fromUserName)){
	                			List<Ticket> tickets = findKf5TicketsByOpenId(fromUserName);
	                			List<Article> kf5List = new ArrayList<Article>();
	                			for(Ticket t : tickets){
	                				Article kf5 = new Article();
	                				kf5.setTitle("������"+t.getCreatedAt()+"�Ĺ����������:"+t.getStatus());
	                				kf5.setDescription("������"+t.getCreatedAt()+"����Ϊ"+t.getId()+"�Ĺ����������:"+t.getStatus());
	                				String kf5Url = t.getUrl();
	                				kf5Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ConfigInfoDepository.WorkTime.APPID+"&redirect_uri=http%3A%2F%2Faugur.oneapm.com%2FviewKf5%3Fticket%3D"+ kf5Url.substring(37, 43)+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
	                				kf5.setUrl(kf5Url);
	                				kf5List.add(kf5);
	                			}
	                			if(tickets.size() > 0){
	                				respMessage = newsReply(toUserName, fromUserName, kf5List);
	                			}else{
	                				respMessage = textReply(toUserName, fromUserName, "��û�д������κι�����");
	                			}
	                			//�Ѿ���д���䣬�鿴����
	                		}else{
	                			//δ��д���䣬������д
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
	  * �ظ��ı���Ϣ
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
	 * �ظ�ͼ����Ϣ
	 * @param toUserName
	 * @param fromUserName
	 * @param arlist
	 * @return
	 */
	public static String newsReply(String toUserName,String fromUserName,List<Article> arlist){
		  //�ظ�ͼ����Ϣ
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
				 sb.append("���Ĺ���������ȣ�").append(re.getStatus()).append("\r\n").append("�鿴��ַ:").append(dto.getUrl()).append(";\r\n");
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
