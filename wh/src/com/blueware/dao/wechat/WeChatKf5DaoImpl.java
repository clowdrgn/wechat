package com.blueware.dao.wechat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blueware.entity.wechat.Kf5Dto;
import com.blueware.util.OneTools;
import com.blueware.util.db.DBConnection;
import com.blueware.util.db.DaoImplBase;

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
                 .append(OneTools.quot).append(dto.getTicket().getUrl()).append(OneTools.quot).append(OneTools.sp)
                 .append(dto.getTicket().getAssigneedAt())
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
	
}
