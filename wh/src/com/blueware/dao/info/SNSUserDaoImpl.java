package com.blueware.dao.info;

import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blueware.util.db.DBConnection;
import com.blueware.util.db.DaoImplBase;
import com.blueware.wechat.oauth2.SNSUserInfo;

public class SNSUserDaoImpl extends DaoImplBase<SNSUserInfo> {
        protected static final Logger LOG = LoggerFactory.getLogger(SNSUserDaoImpl.class);
        protected final String TABLE_NAME = "wechat_user";

        static {
                Instance = new SNSUserDaoImpl();
        }

        private final static SNSUserDaoImpl Instance;

        public static SNSUserDaoImpl getInstance() {
                return Instance;
        }


        public boolean isExist(SNSUserInfo info) {
            final String sql = "select * from " + TABLE_NAME + " where open_id = '" + info.getOpenId() + "' limit 1";
            DBConnection conn = new DBConnection();
            try {
                    ResultSet rs = conn.query(sql);
                    return rs != null && rs.next();
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    e.printStackTrace();
            } finally {
                    conn.close();
            }
            return false;
        }

        

        
        public boolean update(SNSUserInfo info) {
            final String sql = "update " + TABLE_NAME + " set user_id= '" + info.getUserId() + "', update_time = '" + info.getUpdateTime() + "', email = '" + info.getEmail() + "',phone = '" + info.getPhone() + "' where open_id = '" + info.getOpenId() + "'";
            DBConnection conn = new DBConnection();
            try {
                    return conn.update(sql);
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    e.printStackTrace();
            } finally {
                    conn.close();
            }
            return false;
    }
        public boolean updateStatus(SNSUserInfo info) {
            final String sql = "update " + TABLE_NAME + " set status= 1, update_time = '" + info.getUpdateTime() + "' where open_id = '" + info.getOpenId() + "'";
            DBConnection conn = new DBConnection();
            try {
                    return conn.update(sql);
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    e.printStackTrace();
            } finally {
                    conn.close();
            }
            return false;
    }
        public SNSUserInfo findByOpenId(String openId) {
        	String sql = "select * from  " + TABLE_NAME + " where   open_id ='"+openId+"' limit 1 ";
        	DBConnection conn = new DBConnection();
        	ResultSet rs = null;
            try{
                    rs = conn.query(sql);
                    while(rs != null && rs.next()){
                    	SNSUserInfo info = getSNSUserInfoFromResult(rs);
                            if(info != null){
                            	return info;
                            }
                    }
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    e.printStackTrace();
            } finally {
                    conn.close();
            }
            return null;
        }
        public SNSUserInfo findByEmail(String email) {
        	String sql = "select * from  " + TABLE_NAME + " where   email ='"+email+"' limit 1 ";
        	DBConnection conn = new DBConnection();
        	ResultSet rs = null;
            try{
                    rs = conn.query(sql);
                    while(rs != null && rs.next()){
                    	SNSUserInfo info = getSNSUserInfoFromResult(rs);
                            if(info != null){
                            	return info;
                            }
                    }
            } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                    e.printStackTrace();
            } finally {
                    conn.close();
            }
            return null;
        }
        public boolean insert(SNSUserInfo info) {
                String sql = "INSERT INTO " + TABLE_NAME + " (user_id,email,phone,create_time,open_id,nick_name,sex,country,province,city,headImgUrl)" + "VALUES('" + info.getUserId()+ "',"
                		+ "'" + info.getEmail() + "','" + info.getPhone() + "','" + info.getCreateTime()+ "','" + info.getOpenId() + "','" + info.getNickname() + "','"+info.getSex()+"','"
                				+""+info.getCountry()+"','"+info.getProvince()+"','"+info.getCity()+"','"+info.getHeadImgUrl()+"')";
                DBConnection conn = new DBConnection();
                try {
                        return conn.insert(sql);
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                        e.printStackTrace();
                } finally {
                        conn.close();
                }
                return false;
        }
        private SNSUserInfo getSNSUserInfoFromResult(ResultSet rs){
        	SNSUserInfo info = null;
            try{
            	Long id = Long.parseLong(rs.getString("id"));
                Long userId = null;
                try {
                        userId = Long.parseLong(rs.getString("user_id").toString());
                } catch (Exception e) {
                }
                String email = null;
                try {
                        email = rs.getString("email").toString();
                } catch (Exception e) {
                }
                String updateTime = null;
                try {
                	updateTime = rs.getString("update_time").toString();
                } catch (Exception e) {
                }
                String createTime = null;
                try {
                        createTime = rs.getString("create_time").toString();
                } catch (Exception e) {
                }
                String phone = null;
                try {
                        phone = rs.getString("phone").toString();
                } catch (Exception e) {
                }
                String open_id = null;
                try {
                		open_id = rs.getString("open_id").toString();
                } catch (Exception e) {
                }
                String nickName = null;
                try {
                	nickName = rs.getString("nick_name").toString();
                } catch (Exception e) {
                }
                int sex = 0;
                try {
                		sex = Integer.parseInt(rs.getString("sex").toString().trim());
				} catch (Exception e) {
				}
                int status = 0;
                try {
                		status = Integer.parseInt(rs.getString("status").toString().trim());
				} catch (Exception e) {
				}
                String validateCode = null;
                try {
                	validateCode = rs.getString("validate_code").toString().trim();
				} catch (Exception e) {
				}
                info = new SNSUserInfo();
                info.setId(id);
                info.setOpenId(open_id);
                info.setEmail(email);
                info.setPhone(phone);
                info.setCreateTime(createTime);
                info.setUpdateTime(updateTime);
                info.setNickname(nickName);
                info.setSex(sex);
                info.setUserId(userId);
                info.setStatus(status);
                info.setValidateCode(validateCode);
            }catch(Exception e){
                    LOG.error(e.getMessage(), e);
            }
            return info;
    }


}
