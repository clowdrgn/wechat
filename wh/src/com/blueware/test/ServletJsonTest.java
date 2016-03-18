package com.blueware.test;

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

/**
 * @author qinheng
 *
 * Date 2015Äê6ÔÂ7ÈÕ
 */
public class ServletJsonTest extends HttpServlet{
	

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
	        	
	        }
	    }
	    System.out.println("******************************");
		System.out.println("POST-PARAM:"+param.toString());
		System.out.println("event:"+postJson.getEvent());
		System.out.println("recipient:"+postJson.getRecipient());
		System.out.println("token:"+postJson.getToken());
		System.out.println("timestamp:"+postJson.getTimestamp());
		System.out.println("signature:"+postJson.getSignature());
		System.out.println("labelId:"+postJson.getLabelid());
		System.out.println("url:"+postJson.getUrl());
		System.out.println("********************************");
		
		PrintWriter writer = resp.getWriter();
        writer.write("JSON-OK");

	}

}
