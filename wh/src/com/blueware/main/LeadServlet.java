package com.blueware.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueware.entity.manage.Info;
import com.blueware.service.LeadIdentifyService;

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
	    System.out.println(openId);
	    Info info = LeadIdentifyService.IdentifyByMsg(email,phone,openId);
	    if(info != null){
//	    	request.setAttribute("name", info.getName());
	    	response.getWriter().print(info.getName());
//	    	request.getRequestDispatcher("BindSuccess.jsp").forward(request, response);
	    }else{
	    	response.getWriter().print("");
//	    	request.setAttribute("name", null);
//	    	request.getRequestDispatcher("Register.jsp").forward(request, response);
	    }
	}

}
