package com.blueware.main;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueware.dao.info.SNSUserDaoImpl;
import com.blueware.util.TimeTools;
import com.blueware.wechat.oauth2.SNSUserInfo;

/**
 * Servlet implementation class ActiveServlet
 */
@WebServlet("/ActiveServlet")
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActiveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String validateCode = request.getParameter("validateCode");
		SNSUserInfo user = SNSUserDaoImpl.getInstance().findByEmail(email);
		String content = "";
		try {
	     if(user!=null) {    
	            //��֤�û�����״̬    
	            if(user.getStatus()==0) {   
	                ///û����  
	                Date currentTime = new Date();//��ȡ��ǰʱ��    
	                //��֤�����Ƿ����   
					String expireTime = TimeTools.next(user.getUpdateTime(), 2);
	                Date date = TimeTools.String2Date(expireTime);
	                if(currentTime.before(date)) {    
	                    //��֤�������Ƿ���ȷ    
	                    if(validateCode.equals(user.getValidateCode())) {    
	                        user.setStatus(1);//��״̬��Ϊ����  
	                        user.setUpdateTime(TimeTools.format());
	                        SNSUserDaoImpl.getInstance().updateStatus(user);
	                        content = "����ɹ���";
	                        request.setAttribute("content", content);
	                        request.getRequestDispatcher("BindSuccess.jsp").forward(request, response);
	                    } else {    
	                    	content = "�����벻��ȷ";
		                    request.setAttribute("content", content);
		                    request.getRequestDispatcher("emailError.jsp").forward(request, response);
	                    }    
	                } else { 
	                	content = "�������ѹ���";
	                    request.setAttribute("content", content);
	                    request.getRequestDispatcher("emailError.jsp").forward(request, response);
	                }    
	            } else {  
	            	content = "���Ѿ��������";
                    request.setAttribute("content", content);
                    request.getRequestDispatcher("BindSuccess.jsp").forward(request, response);
	            }    
	        } else {  
	        	content = "������δע�ᣨ�����ַ�����ڣ���";
                request.setAttribute("content", content);
                request.getRequestDispatcher("emailError.jsp").forward(request, response);
	        }    
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	            
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
