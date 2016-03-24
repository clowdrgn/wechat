package com.blueware.entity.wechat;

import com.kf5.support.model.Ticket;

public class Kf5Dto {
	private Long id;
	private String openId;
	private String createTime;
	private String url;
	private Ticket ticket;
	
	public Kf5Dto() {
	}
	public Kf5Dto(Long id, String openId, String createTime, Ticket ticket) {
		this.id = id;
		this.openId = openId;
		this.createTime = createTime;
		this.ticket = ticket;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
