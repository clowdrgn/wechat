package com.blueware.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueware.dao.info.SNSUserDaoImpl;
import com.blueware.init.ConfigInfoDepository;
import com.blueware.service.LeadIdentifyService;
import com.blueware.wechat.oauth2.SNSUserInfo;
import com.blueware.wechat.oauth2.WeixinOauth2Token;

/**
 * Servlet implementation class OAuthServlet
 */
@WebServlet("/OAuthServlet")
public class OAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OAuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
        	// �û�ͬ����Ȩ���ܻ�ȡ��code
        	String code = request.getParameter("code");
        	String state = request.getParameter("state");
        	String you = request.getParameter("you");
        	
        	// �û�ͬ����Ȩ
        	if (!"authdeny".equals(code)) {
        		try {
        			System.out.println("----------------------------------------------------111111111111111"+you);
        			// ��ȡ��ҳ��Ȩaccess_token
        			WeixinOauth2Token weixinOauth2Token = LeadIdentifyService.getOauth2AccessToken(ConfigInfoDepository.WorkTime.APPID, ConfigInfoDepository.WorkTime.SECRET, code);
        			// ��ҳ��Ȩ�ӿڷ���ƾ֤
        			String accessToken = weixinOauth2Token.getAccessToken();
//        			// �û���ʶ
        			String openId = weixinOauth2Token.getOpenId();
//        			// ��ȡ�û���Ϣ
        			SNSUserInfo snsUserInfo = LeadIdentifyService.getSNSUserInfo(accessToken, openId);
        			System.out.println("----------------------------------------------------222222222222222222"+state);
        			System.out.println("----------------------------------------------------333333333333333333"+code);
        			
        			SNSUserDaoImpl.getInstance().insert(snsUserInfo);
        			request.setAttribute("snsUserInfo", snsUserInfo);
        			request.setAttribute("state", state);
        			request.setAttribute("openId", openId);
        			request.getRequestDispatcher("lead.jsp").forward(request, response);
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
        		
        	}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("lead.jsp").forward(request, response);
		}
    }
}
