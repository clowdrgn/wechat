package com.blueware.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blueware.entity.WebHook;
import com.blueware.web.WebQuey;


/**
 * Servlet implementation class SendCloudApi
 */
@WebServlet("/SendCloudApi")
public class SendCloudApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger LOG = LoggerFactory.getLogger(SendCloudApi.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendCloudApi() {
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
		WebHook wh = new WebHook();
		if(date == null || date == ""){
			LOG.error("Missing date parameter");
			return;
		}
		List<WebHook> list = web.SearchByDate(date);
		String stringlist = list.toString();
		request.setAttribute("str",stringlist);
		JSONArray new_ja = new JSONArray(); 
		Map map = new HashMap();
		
		for(int i = 0; i < list.size(); i ++){
			wh = list.get(i);
			map.put("email", wh.getEmail());
			map.put("date", wh.getDate());
			map.put("event", wh.getEvent());
			map.put("url", wh.getUrl());
		     new_ja.put(map);  
/*			response.getWriter().append("date:"+wh.getDate()+"\n").append("email:"+wh.getEmail()+"\n")
			.append("event:"+wh.getEvent()+"\n").append("url"+wh.getUrl()+"\n");*/
		     try {
				response.getWriter().append(new_ja.get(i).toString());
			} catch (JSONException e) {
				LOG.error(getServletName(), e);
				e.printStackTrace();
			}
			
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
