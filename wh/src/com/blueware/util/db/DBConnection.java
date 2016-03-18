package com.blueware.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnection {
	private Connection conn = null;
	private PreparedStatement ps = null;

	private final String sp = "\\?";
	
	protected static final Logger LOG = LoggerFactory.getLogger(DBConnection.class);

	/**
	 * 查询
	 * 
	 * @param sql
	 * @return List<Object>
	 * @throws SQLException 
	 */
	public ResultSet query(String sql, Object... args) {
		if (sql.equals("") || sql == null) {
			return null;
		}
		ResultSet rs = null;
		try{
			for(Object object : args){
				sql = sql.replaceFirst(sp, object.toString());
			}
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
		return rs;
	}

	/**
	 * 更新
	 * 
	 * @param sql
	 * @return boolean false
	 */
	public boolean update(String sql, Object... args) {
		boolean b = false;
		if (sql.equals("") || sql == null) {
			return b;
		}
		try {
			for(Object object : args){
				sql = sql.replaceFirst(sp, object.toString());
			}
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			int i = ps.executeUpdate();
			if (i == 1) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean delete(String sql, Object... args){
		boolean b = false;
		if (sql.equals("") || sql == null) {
			return b;
		}
		try {
			for(Object object : args){
				sql = sql.replaceFirst(sp, object.toString());
			}
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			int i = ps.executeUpdate();
			if (i == 1) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean insert(String sql, Object... args){
		boolean b = false;
		if (sql.equals("") || sql == null) {
			return b;
		}
		try {
			for(Object object : args){
				sql = sql.replaceFirst(sp, object.toString());
			}
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			int i = ps.executeUpdate();
			if (i == 1) {
				b = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
