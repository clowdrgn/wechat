package com.blueware.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.blueware.entity.Augur;
import com.blueware.entity.WebHook;
import com.blueware.util.db.DBUtil;

public class WebAdd {
	
	public static void insert(WebHook webHook){
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtil.getConnection();
			String sql="insert into sendcloud_new(event,email,date,labelid,url)"
					+ "values('"+webHook.getEvent()+"','"+webHook.getEmail()+"',"+webHook.getDate()+","+webHook.getLabelid()+",'"+webHook.getUrl()+"')";
			pst=conn.prepareStatement(sql);
			pst.executeUpdate();
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
	public static void insertAugur(Augur augur){
		Connection conn=null;
		PreparedStatement pst=null;
		try {
			conn=DBUtil.getConnection();
			String sql="insert into augur(timestamp,rpid,id,uid,ip,topic,pid,create_time)"
					+ "values("+augur.getTimestamp()+",'"+augur.getRpid()+"','"+augur.getId()+"',"+augur.getUid()+",'"+augur.getIp()+"','"+augur.getTopic()+"','"+augur.getPid()+"','"+augur.getCreateTime()+"')";
			pst=conn.prepareStatement(sql);
			pst.executeUpdate();
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
