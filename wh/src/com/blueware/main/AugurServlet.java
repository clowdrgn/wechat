package com.blueware.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.blueware.entity.Augur;
import com.blueware.entity.PostJson;
import com.blueware.entity.WebHook;
import com.blueware.web.WebAdd;
import com.blueware.web.WebQuey;

/**
 * Servlet implementation class Augur
 */
@WebServlet("/Augur")
public class AugurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AugurServlet() {
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
	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		try {
			Map map=req.getParameterMap();
		    Set keSet=map.entrySet();
		    Augur augur=Augur.getInstance();
		    PrintWriter writer = response.getWriter();
		    if(map.isEmpty()){
		    	writer.write("empty json");
		    	return;
		    }
		        for(Iterator itr=map.entrySet().iterator();itr.hasNext();){
		        	 Map.Entry entry=(Map.Entry)itr.next();
				     String paramName=(String)entry.getKey();
				     String[] paramValue=(String[])entry.getValue();
				     if(paramName.equals("uid")){
				    	 augur.setUid(Long.parseLong(paramValue[0]));
				     }
				     if(paramName.equals("id")){
				    	 augur.setId(paramValue[0]);
				     }
				     if(paramName.equals("pid")){
				    	 augur.setPid(paramValue[0]);
				     }
				     if(paramName.equals("rpid")){
				    	 augur.setRpid(paramValue[0]);
				     }
				     if(paramName.equals("timestamp")){
				    	 augur.setTimestamp(Long.parseLong(paramValue[0]));
				     }
				     if(paramName.equals("ip")){
				    	 augur.setIp(paramValue[0]);
				     }
				     if(paramName.equals("topic")){
				    	 augur.setTopic(paramValue[0]);
				     }
		        }
			if(augur.getTimestamp() == 0L){
				Long timestamp = System.currentTimeMillis(); 
				String timestampStr = timestamp.toString();
				timestamp = Long.parseLong(timestampStr.substring(0, 10));
				augur.setTimestamp(timestamp);
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime = formatter.format(new Date());
			augur.setCreateTime(createTime);
			long uid=augur.getUid();
			String pid=augur.getPid();
			StringBuilder sb = new StringBuilder();
			sb.append(uid).append(pid);
			if(sb.toString().length()<10){
				writer.write("invalid json");
				return;
			}

				boolean s=WebQuey.augurExist(uid,pid,createTime);
				if(s){
					System.out.println("--------------already exists");
				}else{
					WebAdd.insertAugur(augur);
					writer.write("Augur.done(1)");
				}
				
		}catch (Exception e) {
			PrintWriter writer = response.getWriter();
			writer.write("Error");
			e.printStackTrace();
		}
		
		
		
	}
	 /**
     * 将Json对象转换成Map
     * 
     * @param jsonObject
     *            json对象
     * @return Map对象
     * @throws JSONException
     */
    public static Map toMap(String jsonString) throws JSONException {
    	System.out.println("接收到的value-----------------------------------"+jsonString);
        JSONObject jsonObject = new JSONObject(jsonString);
        
        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;
        
        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);

        }
        return result;

    }

}
