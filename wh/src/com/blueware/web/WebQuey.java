package com.blueware.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.blueware.entity.WebHook;
import com.blueware.util.db.DBUtil;

public class WebQuey {
	//open事件校验
	public static boolean SeartEmail(String email,int labelid){
		
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from sendcloud_new where email='"+email+"' and labelid="+labelid+"";
			pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery(sql);
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
public static boolean augurExist(long uid,String pid,String createTime){
		
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from augur where  create_time='"+createTime+"' and uid="+uid+" and pid='"+pid+"'";
			pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery(sql);
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//负面事件邮件校验：带event；
	public static boolean SearchEmailWithEvent(String email,int labelid,String event){
		
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from sendcloud_new where email='"+email+"' and labelid="+labelid+" and event='"+event+"'";
			pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery(sql);
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	 * click 事件url校验
	 */
public static boolean IsTrue(String email,int labelid,String url){
		
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from sendcloud_new where email='"+email+"' and labelid="+labelid+" and url='"+url+"'";
			pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery(sql);
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
public  List<WebHook> SearchByDate(String date){
	
	Connection conn=null;
	PreparedStatement pst=null;
	
	try {
		
		conn=DBUtil.getConnection();
		String sql="select * from sendcloud_new where date='"+date+"'";
		pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery(sql);
		
		List<WebHook> list = new ArrayList<WebHook>();
		 while(rs.next()){                      
			 WebHook webhook = new WebHook();
			 webhook.setId(Integer.valueOf(rs.getString("id")).intValue());
			 webhook.setEvent(rs.getString("event"));
			 webhook.setEmail(rs.getString("email"));
			 webhook.setDate(rs.getString("date"));
			 webhook.setLabelid(Integer.valueOf(rs.getString("labelid")));
			 webhook.setUrl(rs.getString("url"));
             list.add(webhook);       
	}
		 return list;
		 } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}finally{
		try {
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/*public  WebHook SearchCreateDateByEmail(String email){
	
	Connection conn=null;
	PreparedStatement pst=null;
	
	try {
		
		conn=DBManager.getConncection();
		String sql="select date from sendcloud_new where email='"+email+"'limit 1";
		System.out.println("QUERY_SQL:"+sql);
		pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery(sql);
		WebHook webhook = new WebHook();
		                    
			
			 webhook.setId(Integer.valueOf(rs.getString("id")).intValue());
			 webhook.setEvent(rs.getString("event"));
			 webhook.setEmail(rs.getString("email"));
			 webhook.setDate(rs.getString("date"));
			 webhook.setLabelid(Integer.valueOf(rs.getString("labelid")));
			 webhook.setUrl(rs.getString("url"));
                    
	
		 return list;
		 } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}finally{
		try {
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}*/
}
