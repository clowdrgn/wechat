package com.blueware.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blueware.entity.WebHook;
import com.blueware.web.WebQuey;

/**
 * Servlet implementation class ApiTest
 */
@WebServlet("/ApiTest")
public class ApiTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApiTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		WebQuey web = new WebQuey();
		String date = null;
		date = request.getParameter("date");

		List<WebHook> list = web.SearchByDate(date);
		String stringlist = list.toString();
		request.setAttribute("str",stringlist);
		for(int i = 0; i < list.size(); i ++){
			response.getWriter().append(list.get(i).getEmail());
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
