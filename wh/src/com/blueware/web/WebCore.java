package com.blueware.web;


import com.blueware.entity.PostJson;
import com.blueware.entity.WebHook;

/**
 * @author qinheng
 *
 * Date 2015年6月7日
 */
public class WebCore {
	public static void getcCore(PostJson postJson,WebHook webHook){
		
//		 WebHook webHook=WebHook.getInstance();
		String event=postJson.getEvent();
		String recipient=postJson.getRecipient();
		int labelid=postJson.getLabelid();
		String url=postJson.getUrl();
		
		
		if(event!=null){
			if(event.equals("open")&& event!=null){
				boolean s=WebQuey.SeartEmail(recipient,labelid);
				if(s){
					webHook.setEvent("open");
					webHook.setOpen(1);
					webHook.setEmail(recipient);
					WebUpdate.updateEventToOpen(webHook);
					System.out.println("已存在，open+1");
					/*WebUpdate.updateOpen(webHook);*/
				}else{
					webHook.setEvent("open");
					webHook.setOpen(1);
					webHook.setClick(0);
					webHook.setUnsubscribe(0);
					webHook.setEmail(recipient);
					webHook.setLabelid(labelid);
					webHook.setUrl(null);
					webHook.setDate("NOW()");
					WebAdd.insert(webHook);
				}
				
			}else if(event.equals("click")){
				boolean s=WebQuey.SeartEmail(recipient,labelid);
				if(s){
					webHook.setEvent("click");
					webHook.setClick(1);
					webHook.setEmail(recipient);
					webHook.setUrl(url);
					WebUpdate.updateEventToClick(webHook);
					System.out.println("已存在，click+1");
					/*WebUpdate.updateClick(webHook);*/
				}else{
					webHook.setEvent("click");
					webHook.setOpen(0);
					webHook.setClick(1);
					webHook.setUnsubscribe(0);
					webHook.setEmail(recipient);
					webHook.setLabelid(labelid);
					webHook.setDate("NOW()");
					webHook.setUrl(url);
					WebAdd.insert(webHook);
				}
				
			}else if(event.equals("unsubscribe")){
				boolean s=WebQuey.SeartEmail(recipient,labelid);
				if(s){
					webHook.setEvent("unsubscribe");
					webHook.setUnsubscribe(1);
					webHook.setEmail(recipient);
					WebUpdate.updateEventToUnsubscribe(webHook);
					System.out.println("已存在，recipient+1");
				}else{
					webHook.setEvent("unsubscribe");
					webHook.setOpen(0);
					webHook.setClick(0);
					webHook.setUnsubscribe(1);
					webHook.setEmail(recipient);
					webHook.setDate("NOW()");
					webHook.setLabelid(labelid);
					WebAdd.insert(webHook);
				}
			}else if(event.equals("invalid")){
				boolean s=WebQuey.SeartEmail(recipient,labelid);
				if(s){
					webHook.setInvalid(1);
					webHook.setEvent("invalid");
					webHook.setEmail(recipient);
					WebUpdate.updateEventToUnsubscribe(webHook);
					System.out.println("已存在，invalid+1");
					/*WebUpdate.updateUnsubscribe(webHook);*/
				}else{
					webHook.setEvent("invalid");
					webHook.setOpen(0);
					webHook.setClick(0);
					webHook.setUnsubscribe(0);
					webHook.setEmail(recipient);
					webHook.setDate("NOW()");
					webHook.setInvalid(1);
					webHook.setLabelid(labelid);
					WebAdd.insert(webHook);
				}
			}
		}
		
	}

}
