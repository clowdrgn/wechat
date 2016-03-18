package com.blueware.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueware.entity.PostJson;
import com.blueware.entity.WebHook;
import com.blueware.util.Verify;
import com.blueware.web.WebAdd;
import com.blueware.web.WebCore;
import com.blueware.web.WebQuey;
import com.blueware.web.WebUpdate;

/**
 * @author qinheng
 *
 * Date 2015��6��7��
 */
public class Servlet extends HttpServlet{
	
//	private static final String APPKEY="kpq61m0m-ps7w-bxqn-vfbe-n3cycz1182";
	private static final String APPKEY="XdtATiOr6caIOPfW";
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		StringBuffer param = new StringBuffer();
		Map map=req.getParameterMap();
	    Set keSet=map.entrySet();
	    PostJson postJson=PostJson.getInstance();
	    WebHook webHook=WebHook.getInstance();
	    for(Iterator itr=keSet.iterator();itr.hasNext();){
	        Map.Entry entry=(Map.Entry)itr.next();
	        String paramName=(String)entry.getKey();
	        String[] paramValue=(String[])entry.getValue();
	        if(param.toString().equals("")){
	        	param.append("?"+paramName+"="+paramValue[0]);
	        }else{
	        	param.append("&"+paramName+"="+paramValue[0]);
	        	if(paramName.equals("event")){
	        		postJson.setEvent(paramValue[0]);
	        	}
	        	if(paramName.equals("recipient")){
	        		postJson.setRecipient(paramValue[0]);
	        	}
	        	if(paramName.equals("token")){
	        		postJson.setToken(paramValue[0]);
	        	}
	        	if(paramName.equals("timestamp")){
	        		postJson.setTimestamp(Long.parseLong(paramValue[0]));
	        	}
	        	if(paramName.equals("signature")){
	        		postJson.setSignature(paramValue[0]);
	        	}
	        	if(paramName.equals("labelId")){
	        		postJson.setLabelid(Integer.parseInt(paramValue[0]));
	        	}
	        	if(paramName.equals("url")){
	        		postJson.setUrl(paramValue[0]);
	        	}
	        	
	        }
	    }
	    
//	    System.out.println("*****11111111111111111111111111111111111***");
//	    System.out.println("*************START*****************");
//	    
//		System.out.println("POST-PARAM:"+param.toString());
//		System.out.println("event:"+postJson.getEvent());
//		System.out.println("recipient:"+postJson.getRecipient());
//		System.out.println("token:"+postJson.getToken());
//		System.out.println("timestamp:"+postJson.getTimestamp());
//		System.out.println("signature:"+postJson.getSignature());
//		System.out.println("labelId:"+postJson.getLabelid());
//		System.out.println("url:"+postJson.getUrl());
//		
//		System.out.println("TOKEN:"+postJson.getToken());
//		System.out.println("TIMESTAMP:"+postJson.getTimestamp());
//		System.out.println("SIGNATURE:"+postJson.getSignature());
//		
//		System.out.println("**************END******************");
		
		
		
		
		
		String token=postJson.getToken();
		long timestamp=postJson.getTimestamp();
		String signature=postJson.getSignature();
		String event=postJson.getEvent();
		String recipient=postJson.getRecipient();
		int labelid=postJson.getLabelid();
		String url=postJson.getUrl();
		
		
		
		
		
		

		if(event.equals("open") && event!=null){
			boolean s=WebQuey.SeartEmail(recipient,labelid);
			if(s){
				System.out.println("--------------already exists");
			}else{
				webHook.setEvent("open");
				webHook.setOpen(1);
				webHook.setClick(0);
				webHook.setUnsubscribe(0);
				webHook.setEmail(recipient);
				webHook.setLabelid(labelid);
				webHook.setInvalid(0);
				webHook.setDate("NOW()");
				webHook.setUrl(null);
				WebAdd.insert(webHook);
			}
			
		}else if(event.equals("click")){
//			boolean s=WebQuey.SeartEmail(recipient,labelid);
			boolean s=WebQuey.IsTrue(recipient, labelid, url);
			if(s){
				System.out.println("--------------already exists");
			}else{
				webHook.setEvent("click");
				webHook.setOpen(0);
				webHook.setClick(1);
				webHook.setUnsubscribe(0);
				webHook.setEmail(recipient);
				webHook.setLabelid(labelid);
				webHook.setDate("NOW()");
				webHook.setInvalid(0);
				webHook.setUrl(url);
				WebAdd.insert(webHook);
			}
			
		}else if(event.equals("unsubscribe")){
			boolean s=WebQuey.SearchEmailWithEvent(recipient,labelid,event);
			if(s){
				System.out.println("--------------already exists");
			}else{
				webHook.setEvent("unsubscribe");
				webHook.setOpen(0);
				webHook.setClick(0);
				webHook.setUnsubscribe(1);
				webHook.setEmail(recipient);
				webHook.setDate("NOW()");
				webHook.setLabelid(labelid);
				webHook.setInvalid(0);
				WebAdd.insert(webHook);
			}
		}else if(event.equals("invalid")){
			boolean s=WebQuey.SearchEmailWithEvent(recipient,labelid,event);
			if(s){
				System.out.println("--------------already exists");
			}else{
				webHook.setEvent("invalid");
				webHook.setOpen(0);
				webHook.setClick(0);
				webHook.setUnsubscribe(0);
				webHook.setEmail(recipient);
				webHook.setDate("NOW()");
				webHook.setInvalid(1);
				webHook.setLabelid(labelid);
				webHook.setUrl(null);
				WebAdd.insert(webHook);
			}
		}else if(event.equals("report_spam")){
			boolean s=WebQuey.SearchEmailWithEvent(recipient,labelid,event);
			if(s){
				System.out.println("--------------already exists");
			}else{
				webHook.setEvent("report_spam");					
				webHook.setEmail(recipient);
				webHook.setDate("NOW()");					
				webHook.setLabelid(labelid);
				webHook.setUrl(null);
				WebAdd.insert(webHook);
			}
		}else if(event.equals("bounce")){
			boolean s=WebQuey.SearchEmailWithEvent(recipient,labelid,event);
			if(s){
				System.out.println("--------------already exists");
			}else{
				webHook.setEvent("bounce");					
				webHook.setEmail(recipient);
				webHook.setDate("NOW()");					
				webHook.setLabelid(labelid);
				webHook.setUrl(null);
				WebAdd.insert(webHook);
			}
		}else {
			PrintWriter writer = resp.getWriter();
	        writer.write("misssing");
		}
		PrintWriter writer = resp.getWriter();
		writer.write("OK");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	    
//	    try {
//	    	boolean s=Verify.verify(APPKEY, token, timestamp, signature);
//	    	System.out.println("**********VERIFY-START************");
//	    	System.out.println("APPKEY:"+APPKEY);
//	    	System.out.println("token:"+token);
//	    	System.out.println("timestamp:"+timestamp);
//	    	System.out.println("signature:"+signature);
//	    	System.out.println("VERIFY_IS:"+s);
//	    	System.out.println("**********VERIFY-END************");
//	    	if(s){
//	    		WebCore.getcCore(postJson,webHook);
//	    	}else{
//	    		System.out.println("STATUS:VERIFY FAIL");
//	    		PrintWriter writer = resp.getWriter();
//		        writer.write("verify fail!!!");
//	    	}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
	}
	

}
