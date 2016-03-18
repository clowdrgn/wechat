package com.blueware.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class BaseAuth
 */
@WebServlet("/BaseAuth")
public class BaseAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseAuth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String sessionAuth = (String) request.getSession().getAttribute("auth");  
		   
	        if (sessionAuth != null) {  
	            System.out.println("this is next step");  
	            nextStep(request, response);  
	  
	        } else {  
	  
	            if(!checkHeaderAuth(request, response)){  
	                response.setStatus(401);  
	                response.setHeader("Cache-Control", "no-store");  
	                response.setDateHeader("Expires", 0);  
	                response.setHeader("WWW-authenticate", "Basic Realm=\"test\"");  
	            }             
	  
	        }  
	  
	    }  
	  
	    private boolean checkHeaderAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {  
	  
	        String auth = request.getHeader("Authorization");  
	        System.out.println("auth encoded in base64 is " + getFromBASE64(auth));  
	          
	        if ((auth != null) && (auth.length() > 6)) {  
	            auth = auth.substring(6, auth.length());  
	  
	            String decodedAuth = getFromBASE64(auth);  
	            System.out.println("auth decoded from base64 is " + decodedAuth);  
	  
	            request.getSession().setAttribute("auth", decodedAuth);  
	            return true;  
	        }else{  
	            return false;  
	        }  
	  
	    }  
	  
	    private String getFromBASE64(String s) {  
	        if (s == null)  
	            return null;  
	        BASE64Decoder decoder = new BASE64Decoder();  
	        try {  
	            byte[] b = decoder.decodeBuffer(s);  
	            return new String(b);  
	        } catch (Exception e) {  
	            return null;  
	        }  
	    }  
	  
	    public void nextStep(HttpServletRequest request, HttpServletResponse response) throws IOException {  
	        PrintWriter pw = response.getWriter();  
	        pw.println("<html> next step, authentication is : " + request.getSession().getAttribute("auth") + "<br>");  
	        pw.println("<br></html>");  
	    }  
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
