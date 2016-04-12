package com.blueware.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueware.dao.info.SNSUserDaoImpl;
import com.blueware.entity.manage.Info;
import com.blueware.service.LeadIdentifyService;
import com.blueware.service.mail.SendCloudService;
import com.blueware.util.MD5Util;
import com.blueware.util.TimeTools;
import com.blueware.wechat.oauth2.SNSUserInfo;

/**
 * Servlet implementation class LeadServlet
 */
@WebServlet("/LeadServlet")
public class LeadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeadServlet() {
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
    	String phone = request.getParameter("phone");
    	String email = request.getParameter("email");
    	SNSUserInfo f = SNSUserDaoImpl.getInstance().findByEmail(email);
    	if(f!=null){
    		String content = "该邮箱已经被激活过了！";
    		request.setAttribute("content", content);
    		request.getRequestDispatcher("emailError.jsp").forward(request, response);
    	}
    	String nowTime = TimeTools.format();
    	SNSUserInfo uinfo = new SNSUserInfo(nowTime, phone, email, 0, MD5Util.MD5(nowTime+email),openId);
	    System.out.println(openId);
	    Info info = LeadIdentifyService.IdentifyByMsg(uinfo);
	    if(info != null){
//	    	request.setAttribute("name", info.getName());
	        StringBuffer sb=new StringBuffer("点击下面链接激活账号，48小时生效，否则重新激活邮箱，链接只能使用一次，请尽快激活！");  
	        sb.append("\">http://augur.oneapm.com/active?email=");   
	        sb.append(email);  
	        sb.append("&validateCode=");  
	        sb.append(uinfo.getValidateCode());  
	        sb.append("");  
	        System.out.println(sb.toString());
	    	LeadIdentifyService.sendEnableEmail(sb.toString(), email);
	    	String content = "激活邮件已经发送！请查收";
    		request.setAttribute("content", content);
	    	request.getRequestDispatcher("BindSuccess.jsp").forward(request, response);
	    }else{
	    	String content = "您输入的邮箱未注册，请使用oneapm登录邮箱验证";
    		request.setAttribute("content", content);
	    	request.getRequestDispatcher("emailError.jsp").forward(request, response);
//	    	request.setAttribute("name", null);
//	    	request.getRequestDispatcher("Register.jsp").forward(request, response);
	    }
	}

}
