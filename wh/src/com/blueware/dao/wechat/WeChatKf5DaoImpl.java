package com.blueware.dao.wechat;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blueware.entity.wechat.Kf5Dto;
import com.blueware.util.OneTools;
import com.blueware.util.db.DBConnection;
import com.blueware.util.db.DaoImplBase;
import com.blueware.wechat.oauth2.SNSUserInfo;

public class WeChatKf5DaoImpl extends DaoImplBase<Kf5Dto>{
	protected static final Logger LOG = LoggerFactory.getLogger(WeChatTokenDaoImpl.class);
	protected final String TABLE_NAME = "wechat_kf5";
	
	static {
		Instance = new WeChatKf5DaoImpl();
	}
	
	private final static WeChatKf5DaoImpl Instance;
	
	public static WeChatKf5DaoImpl getInstance(){
		return Instance;
	}
	
	
	 public boolean insert(Kf5Dto dto) {
         StringBuilder builder = new StringBuilder();
                 builder.append(OneTools.left).append(OneTools.quot).append(dto.getOpenId()).append(OneTools.quot).append(OneTools.sp)
                 .append(OneTools.quot).append(dto.getTicket().getCreatedAt()).append(OneTools.quot).append(OneTools.sp)
                 .append(OneTools.quot).append(dto.getTicket().getUrl()).append(OneTools.quot).append(OneTools.sp).append(OneTools.quot)
                 .append(dto.getTicket().getAssigneedAt()).append(OneTools.quot)
                 .append(OneTools.right);
         final String sql = "insert into wechat_kf5 (openId,create_time,url,assigneed_at"
                         + ")values?";
         System.out.println(sql+"  "+builder.toString());
         DBConnection conn = new DBConnection();
         try {
                 return conn.insert(sql, builder.toString());
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
         } finally {
                 conn.close();
         }
         return false;
 }	
     public List<Kf5Dto> findByOpenId(String openId) {
    	List<Kf5Dto> list = new ArrayList<Kf5Dto>();
     	String sql = "select * from  " + TABLE_NAME + " where   openId ='"+openId+"'  ";
     	DBConnection conn = new DBConnection();
     	ResultSet rs = null;
         try{
                 rs = conn.query(sql);
                 while(rs != null && rs.next()){
                	 Kf5Dto info = getKf5DtoFromResult(rs);
                	 list.add(info);
                 }
         } catch (Exception e) {
                 LOG.error(e.getMessage(), e);
                 e.printStackTrace();
         } finally {
                 conn.close();
         }
         return list;
     }
     private Kf5Dto getKf5DtoFromResult(ResultSet rs){
    	 Kf5Dto dto = null;
         try{
         	Long id = Long.parseLong(rs.getString("id"));
             String open_id = null;
             try {
             		open_id = rs.getString("open_id").toString();
             } catch (Exception e) {
             }
             String url = null;
             try {
             	url = rs.getString("url").toString();
             } catch (Exception e) {
             }
             String create_time = null;
             try {
            	 create_time = rs.getString("create_time").toString();
				} catch (Exception e) {
				}
             dto = new Kf5Dto();
             dto.setId(id);
             dto.setOpenId(open_id);
             dto.setCreateTime(create_time);
             dto.setUrl(url);
         }catch(Exception e){
                 LOG.error(e.getMessage(), e);
         }
         return dto;
 }
	
}
