package com.blueware.service;


import java.util.List;

import com.blueware.dao.info.InfoDaoImpl;
import com.blueware.dao.info.SNSUserDaoImpl;
import com.blueware.entity.manage.Info;
import com.blueware.init.ConfigInfoDepository;
import com.blueware.util.OneTools;
import com.blueware.util.TimeTools;
import com.blueware.wechat.oauth2.SNSUserInfo;
import com.blueware.wechat.oauth2.WeixinOauth2Token;

import net.sf.json.JSONObject;

public class LeadIdentifyService {
	public static Info IdentifyByMsg(String email,String phone,String openId){
		if(email == null && phone == null){
			return null;
		}else{
			Info info = InfoDaoImpl.getInstance().findByEmailOrPhone(email,phone);
			SNSUserInfo uinfo = new SNSUserInfo();
			uinfo.setEmail(email);
			uinfo.setPhone(phone);
			uinfo.setOpenId(openId);
			uinfo.setUpdateTime(TimeTools.format());
			if(info != null){
				uinfo.setUserId(info.getUserId());
				SNSUserDaoImpl.getInstance().update(uinfo);
			}else{
				SNSUserDaoImpl.getInstance().update(uinfo);
			}
			return info;
		}
	}
	/**
     * 获取网页授权凭证
     * 
     * @param appId 公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
	 * @throws Exception 
     */
    public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) throws Exception {
        WeixinOauth2Token wat = null;
        // 拼接请求地址
//        String requestUrl = ConfigInfoDepository.WorkTime.WEBTOKEN;
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        System.out.println("-----------------------------------------"+requestUrl);
          JSONObject jsonObject = OneTools.httpsRequest(requestUrl, "GET", null);
          System.out.println("-----------------------------------------"+jsonObject);
        if (null != jsonObject) {
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                System.out.println("获取网页授权凭证失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
            }
        }
        return wat;
    }
    public static void main(String[] args) {
    	try {
			getOauth2AccessToken(ConfigInfoDepository.WorkTime.APPID, ConfigInfoDepository.WorkTime.SECRET, "0214a7d0ec99fc7656664296ecadcc29");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) throws Exception {
        SNSUserInfo snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = ConfigInfoDepository.WorkTime.USERINFO;
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = OneTools.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                System.out.println("获取网页授权凭证失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
            }
        }
        return snsUserInfo;
    }
}
