package com.blueware.service.mail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blueware.util.TimeTools;


@SuppressWarnings({"resource","deprecation"})
public class SendCloudService {

        private static final String URL_SEND = "https://sendcloud.sohu.com/webapi/mail.send.xml";
        private static final String ACCOUNT = "yunying";
        private static final String KEY = "XdtATiOr6caIOPfW";
        private static final String FROM = "OneAPM@push.oneapm.com";
        
        public static BufferedWriter bw;

        protected static final Logger LOG = LoggerFactory.getLogger(SendCloudService.class);

        public static boolean sendCloud(String to, String html, String title, Long lable, int account) {
                try {
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httpost = new HttpPost(URL_SEND);
                        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
                        nvps.add(new BasicNameValuePair("api_user", ACCOUNT));
                        nvps.add(new BasicNameValuePair("api_key", KEY));
                        nvps.add(new BasicNameValuePair("from", FROM));
                        nvps.add(new BasicNameValuePair("to", to));
                        nvps.add(new BasicNameValuePair("subject", title));
                        if(lable != null && lable > 0L){
                                nvps.add(new BasicNameValuePair("label", lable.toString()));
                        }
                        nvps.add(new BasicNameValuePair("html", html));
                        httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
                        // 请求
                        HttpResponse response = httpclient.execute(httpost);
                        //记录
                        bw.write(TimeTools.format()+"|"+account+"|"+lable+"|"+to+"|"+title);
                        bw.newLine();
                        bw.flush();
                        // 处理响应
                        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
                                return true;
                        }
                } catch (Exception e) {
                        LOG.error(e.getMessage(), e);
                }
                return false;
        }
        
        
        public static void main(String[] args) throws IOException{
                File file = new java.io.File("/home/abc/001");
                FileWriter  fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("123");
                bw.newLine();
                bw.flush();
                bw.close();
        }

}
