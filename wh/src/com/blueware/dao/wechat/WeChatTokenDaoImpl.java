package com.blueware.dao.wechat;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blueware.util.TimeTools;
import com.blueware.util.db.DaoImplBase;
import com.blueware.wechat.oauth2.Token;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class WeChatTokenDaoImpl extends DaoImplBase<Token>{
	protected static final Logger LOG = LoggerFactory.getLogger(WeChatTokenDaoImpl.class);
	protected final String TABLE_NAME = "wechat_token";
	
	static {
		Instance = new WeChatTokenDaoImpl();
	}
	
	private final static WeChatTokenDaoImpl Instance;
	
	public static WeChatTokenDaoImpl getInstance(){
		return Instance;
	}
	
	
	public String findLast(){
	        try{
	                DBCursor cursor= getDBCollection(TABLE_NAME).find();
	                if(cursor.hasNext()){
	                        return findToken(cursor.next());
	                }
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return null;
	}
	public boolean exists(){
        try{
        	DBObject object = new BasicDBObject();
            DBCursor cursor= getDBCollection(TABLE_NAME).find(object);
            if(cursor.hasNext()){
                    return true;
            }
        }catch(Exception e){
                LOG.error(e.getMessage(), e);
        }
        return false;
	}
	
	public boolean insert(String access_token){
	        try{
	                DBObject object = new BasicDBObject();
	                object.put("access_token", access_token);
	                object.put("create_time", TimeTools.format());  
	                return getDBCollection(TABLE_NAME).insert(object).getN() > -1;
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return false;
	}
	
	public boolean update(String access_token){
	        try{
	                DBObject object = new BasicDBObject();
	                DBObject value = new BasicDBObject();
	                value.put("access_token", access_token);
	                value.put("create_time", TimeTools.format());  
	                return getDBCollection(TABLE_NAME).update(object, new BasicDBObject("$set", value)).getN() > -1;
	        }catch(Exception e){
	                LOG.error(e.getMessage(), e);
	        }
	        return false;
	}
	
	
	
	
	private String findToken(DBObject object){
	      try{
	              String access_token = null;
	              try{
	            	  access_token  = object.get("access_token").toString();
	              }catch(Exception e){}
	              return access_token;
	      }catch(Exception e){
	              LOG.error(e.getMessage(), e);
	      }
	      return null;
	}
	
}
