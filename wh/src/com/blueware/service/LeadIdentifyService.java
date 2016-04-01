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
     * ��ȡ��ҳ��Ȩƾ֤
     * 
     * @param appId �����˺ŵ�Ψһ��ʶ
     * @param appSecret �����˺ŵ���Կ
     * @param code
     * @return WeixinAouth2Token
	 * @throws Exception 
     */
    public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) throws Exception {
        WeixinOauth2Token wat = null;
        // ƴ�������ַ
//        String requestUrl = ConfigInfoDepository.WorkTime.WEBTOKEN;
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // ��ȡ��ҳ��Ȩƾ֤
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
                System.out.println("��ȡ��ҳ��Ȩƾ֤ʧ�� errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
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
        // ƴ�������ַ
        String requestUrl = ConfigInfoDepository.WorkTime.USERINFO;
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // ͨ����ҳ��Ȩ��ȡ�û���Ϣ
        JSONObject jsonObject = OneTools.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                // �û��ı�ʶ
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // �ǳ�
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // �Ա�1�����ԣ�2��Ů�ԣ�0��δ֪��
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                // �û����ڹ���
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // �û�����ʡ��
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // �û����ڳ���
                snsUserInfo.setCity(jsonObject.getString("city"));
                // �û�ͷ��
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                System.out.println("��ȡ��ҳ��Ȩƾ֤ʧ�� errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
            }
        }
        return snsUserInfo;
    }
}
