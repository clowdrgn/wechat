package com.blueware.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.blueware.init.ConfigInfoDepository.WorkTime;

public class DBUtil {
    public static Connection getConnection() {  
        Connection connection = null;    
        try {  
            Class.forName(WorkTime.DRIVER);  
            connection = DriverManager.getConnection(WorkTime.URL, 
            		WorkTime.USERNAME, 
            		WorkTime.PASSWORD);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return connection;  
    }  
    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {  
        try {  
            if (rs != null) {  
                rs.close();  
                rs = null;  
            }  
            if (ps != null) {  
                ps.close();  
                ps = null;  
            }  
            if (conn != null) {  
                conn.close();  
                conn = null;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
