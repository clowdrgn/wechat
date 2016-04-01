package com.blueware.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueware.dao.info.SNSUserDaoImpl;
import com.blueware.dao.wechat.WeChatKf5DaoImpl;
import com.blueware.entity.wechat.Kf5Dto;
import com.blueware.init.ConfigInfoDepository;
import com.blueware.kf5.service.KF5ApiV2;
import com.blueware.service.WeChatMsgService;
import com.blueware.wechat.oauth2.SNSUserInfo;
import com.kf5.support.model.Ticket;

/**
 * Servlet implementation class Kf5Servlet
 */
@WebServlet("/Kf5Servlet")
public class Kf5Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Kf5Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        String openId = request.getParameter("openId");
        System.out.println("last！！--！！！！！！！！！！！！！！！！！！"+openId);
    	String title = request.getParameter("title");
    	String content = request.getParameter("content");
    	String leixing = request.getParameter("leixing");
    	System.out.println(title+content+openId);
    	SNSUserInfo suinfo = SNSUserDaoImpl.getInstance().findByOpenId(openId);
    	if(suinfo != null){
    		if(suinfo.getEmail() != null && suinfo.getEmail().length() > 0){
        		Ticket ticket = KF5ApiV2.createAgentOrder(openId, content, title,suinfo.getEmail(),leixing);
        		if(ticket != null){
        			String url = ticket.getUrl();
        			url = "https://oneapm.kf5.com/agent/#/ticket/" + url.substring(37, 43);
        			ticket.setUrl(url);
        			Kf5Dto dto = new Kf5Dto();
        			dto.setOpenId(openId);
        			dto.setTicket(ticket);
        			WeChatKf5DaoImpl.getInstance().insert(dto);
        			response.getWriter().print(ticket.getUrl());
        		}else{
        			response.getWriter().print("");
        		}
        	}else{
        		response.getWriter().print("");
        	}
    	}
    	else{
    		response.getWriter().print("");
    	}
	}

}
