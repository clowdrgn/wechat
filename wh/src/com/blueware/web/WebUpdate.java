package com.blueware.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.blueware.entity.WebHook;
import com.blueware.util.db.DBUtil;

/**
 * @author qinheng
 *
 * Date 2015Äê6ÔÂ7ÈÕ
 */
public class WebUpdate {
/*	public static void updateClick(WebHook webHook){
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBManager.getConncection();
//			String sql="update sendcloud set open="+webHook.getOpen()+",click="+webHook.getClick()+" where email='"+webHook.getEmail()+"'";
			String sql="update sendcloud set click="+webHook.getClick()+",url='"+webHook.getUrl()+"' where email='"+webHook.getEmail()+"'";
			System.out.println("UPDATE_SQL:"+sql);
			pst=conn.prepareStatement(sql);
			pst.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
/*	public static void updateOpen(WebHook webHook){
		Connection conn=null;
		PreparedStatement pst=null;
		
		try {
			conn=DBManager.getConncection();
			String sql="update sendcloud set open="+webHook.getOpen()+" where email='"+webHook.getEmail()+"'";
			System.out.println("UPDATE_SQL:"+sql);
			pst=conn.prepareStatement(sql);
			pst.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
/*	public static void updateUnsubscribe(WebHook webHook){
		Connection conn=null;
		PreparedStatement pst=null;
		
		try {
			conn=DBManager.getConncection();
				String sql="update sendcloud set unsubscribe="+webHook.getUnsubscribe()+" where email='"+webHook.getEmail()+"'";
				System.out.println("UPDATE_SQL:"+sql);
				pst=conn.prepareStatement(sql);
				pst.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public static void updateEventToClick(WebHook webHook){
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtil.getConnection();
//			String sql="update sendcloud set open="+webHook.getOpen()+",click="+webHook.getClick()+" where email='"+webHook.getEmail()+"'";
			String sql="update sendcloud_new set event='"+webHook.getEvent()+"',url='"+webHook.getUrl()+"' where email='"+webHook.getEmail()+"'";
			pst=conn.prepareStatement(sql);
			pst.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public static void updateEventToOpen(WebHook webHook){
		Connection conn=null;
		PreparedStatement pst=null;
		
		try {
			conn=DBUtil.getConnection();
			String sql="update sendcloud_new set event='"+webHook.getEvent()+"' where email='"+webHook.getEmail()+"'";
			pst=conn.prepareStatement(sql);
			pst.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public static void updateEventToUnsubscribe(WebHook webHook){
		Connection conn=null;
		PreparedStatement pst=null;
		
		try {
			conn=DBUtil.getConnection();
				String sql="update sendcloud_new set event='"+webHook.getEvent()+"' where email='"+webHook.getEmail()+"'";
				pst=conn.prepareStatement(sql);
				pst.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
