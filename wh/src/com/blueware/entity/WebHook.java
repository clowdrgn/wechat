package com.blueware.entity;

import java.util.Date;

/**
 * @author qinheng
 *
 * Date 2015Äê6ÔÂ7ÈÕ
 */
public class WebHook {
	private int id;
	private String event;
	private int request;
	private int deliver;
	private int open;
	private int click;
	private int unsubscribe;
	private int bounce;
	private int report_spam;
	private int invalid;
	private String email;
	private String date;
	private int labelid;
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLabelid() {
		return labelid;
	}
	public void setLabelid(int labelid) {
		this.labelid = labelid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getRequest() {
		return request;
	}
	public void setRequest(int request) {
		this.request = request;
	}
	public int getDeliver() {
		return deliver;
	}
	public void setDeliver(int deliver) {
		this.deliver = deliver;
	}
	public int getOpen() {
		return open;
	}
	public void setOpen(int open) {
		this.open = open;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public int getUnsubscribe() {
		return unsubscribe;
	}
	public void setUnsubscribe(int unsubscribe) {
		this.unsubscribe = unsubscribe;
	}
	public int getBounce() {
		return bounce;
	}
	public void setBounce(int bounce) {
		this.bounce = bounce;
	}
	public int getReport_spam() {
		return report_spam;
	}
	public void setReport_spam(int report_spam) {
		this.report_spam = report_spam;
	}
	public int getInvalid() {
		return invalid;
	}
	public void setInvalid(int invalid) {
		this.invalid = invalid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String time) {
		this.date = time;
	}
    public WebHook() {}  
    private static final WebHook single = new WebHook();  
    public static WebHook getInstance() {  
        return single;  
    } 
	
	
	
	
	
	

}
