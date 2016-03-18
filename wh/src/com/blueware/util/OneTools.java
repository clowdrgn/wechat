package com.blueware.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blueware.wechat.oauth2.MyX509TrustManager;

import net.sf.json.JSONObject;


public class OneTools {

	public final static String sp = ",";
	public final static String sp_email = ";";
	public final static String left = "(";
	public final static String right = ")";
	public final static String quot = "'";
	private static final Logger LOG = LoggerFactory.getLogger(OneTools.class);
	private static final String SERVLET_PUT = "PUT";
    private static final String SERVLET_GET = "GET";
    private static final String SERVLET_POST = "POST";
	
	public static void write(List<String> list, String path) throws IOException{
                File file = new File(path);
                FileWriter fWriter = new FileWriter(file);
                BufferedWriter bWriter = new BufferedWriter(fWriter);
                for(String s : list){
                        bWriter.write(s);
                        bWriter.newLine();
                }
                bWriter.flush();
                bWriter.close();
        }
	
	public static void write(Set<String> list, String path) throws IOException{
                File file = new File(path);
                FileWriter fWriter = new FileWriter(file);
                BufferedWriter bWriter = new BufferedWriter(fWriter);
                for(String s : list){
                        bWriter.write(s);
                        bWriter.newLine();
                }
                bWriter.flush();
                bWriter.close();
        }
	
	public static String listString(List<Long> ids){
		StringBuilder builder = new StringBuilder();
		builder.append(left);
		for(Long id : ids){
			builder.append(id).append(sp);
		}
		builder.delete(builder.length()-1, builder.length());
		builder.append(right);
		return builder.toString();
	}
	
	public static void getDistinctList(List<String> list) {
        List<String> listTemp = new ArrayList<String>();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
                String a = it.next();
                if (listTemp.contains(a)) {
                        it.remove();
                } else {
                        listTemp.add(a);
                }
        }
	}
	
	public static void write(String path, String content){
	        try{
                        File file = new File(path);
                        FileWriter fw = new FileWriter(file);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(content);
                        bw.flush();
                        bw.close();
                        fw.close();
                }catch(Exception e){
                }
	}
	
	public static String read(String path){
                try{
                        File file = new File(path);
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                        String s = br.readLine();
                        br.close();
                        fr.close();
                        return s;
                }catch(Exception e){
                }
                return null;
        }
	public static String readString(String path){
	        StringBuilder builder = new StringBuilder();
                try{
                        File file = new File(path);
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                        String s = null;
                        while((s=br.readLine()) != null){
                                builder.append(s);
                        }
                        br.close();
                        fr.close();
                }catch(Exception e){
                }
                return builder.toString();
        }
	public static List<String> readList(String path){
	        List<String> list = new ArrayList<String>();
                try{
                        File file = new File(path);
                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);
                        String s = null;
                        while((s=br.readLine()) != null){
                                list.add(s);
                        }
                        br.close();
                        fr.close();
                }catch(Exception e){
                }
                return list;
        }

	public static List<String> readFileList(String path,List<String> list) {
		try{
		File file = new File(path);
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				StringBuilder builder = new StringBuilder();
				FileReader fr = new FileReader(tempList[i]);
                BufferedReader br = new BufferedReader(fr);
                String s = null;
                while((s=br.readLine()) != null){
                        builder.append(s);
                }
                list.add(builder.toString());
                br.close();
                fr.close();
			}
			if (tempList[i].isDirectory()) {
				readFileList(tempList[i].getPath(),list);
			}
		}
		}catch (Exception e) {
		}
		return list;
	}
	public static List<String> readFileWithOutDic(String path) {
		List<String> list = new ArrayList<String>();
		try{
		File file = new File(path);
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				StringBuilder builder = new StringBuilder();
				FileReader fr = new FileReader(tempList[i]);
                BufferedReader br = new BufferedReader(fr);
                String s = null;
                while((s=br.readLine()) != null){
                        builder.append(s);
                }
                list.add(builder.toString());
                br.close();
                fr.close();
			}
		}
		}catch (Exception e) {
		}
		return list;
	}
	
	public static String doPut(String urlStr, Map<String, Object> paramMap) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(SERVLET_PUT);
        String paramStr = prepareParam(paramMap);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(paramStr.length()));
        OutputStream os = conn.getOutputStream();
        os.write(paramStr.toString().getBytes("utf-8"));
        os.close();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        String result = "";
        while ((line = br.readLine()) != null) {
                result += line;
        }
        br.close();
        return result;
}

public static String doPost(String urlStr, Map<String, Object> paramMap) throws Exception {
        try{
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod(SERVLET_POST);
                String paramStr = prepareParam(paramMap);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Length", String.valueOf(paramStr.length()));
                OutputStream os = conn.getOutputStream();
                os.write(paramStr.toString().getBytes("utf-8"));
                os.close();
                
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                String result = "";
                while ((line = br.readLine()) != null) {
                        result += line;
                }
                br.close();
                return result;
        }catch(Exception e){
                LOG.error(e.getMessage(), e);
        }
        return null;
}
public static String sendPost(String url, Map<String, Object> paramMap) {
    PrintWriter out = null;
    BufferedReader in = null;
    String result = "";
    try {
            String param = prepareParam(paramMap);
            URL realUrl = new URL(url);
            // 打开和URL之间的连�?
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属�?
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发�?�POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发�?�请求参�?
//            out.write("access_token="+TOKEN+"&email=lijiangdd@126.com");
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响�?
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                    result += line;
            }
    } catch (Exception e) {
            e.printStackTrace();
    }
    // 使用finally块来关闭输出流�?�输入流
    finally {
            try {
                    if (out != null) {
                            out.close();
                    }
                    if (in != null) {
                            in.close();
                    }
            } catch (IOException ex) {
                    ex.printStackTrace();
            }
    }
    return result;
}

public static String doGet(String urlStr, Map<String, Object> paramMap) throws Exception {
    String paramStr = prepareParam(paramMap);
    if (paramStr == null || paramStr.trim().length() < 1) {

    } else {
            urlStr += "?" + paramStr;
    }
    URL url = new URL(urlStr);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod(SERVLET_GET);
    conn.setRequestProperty("Content-Type", "text/html; charset=UTF-8");

    conn.connect();
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String line;
    String result = "";
    while ((line = br.readLine()) != null) {
            result += line;
    }
    br.close();
    return result;
}

private static String prepareParam(Map<String, Object> paramMap) {
    StringBuffer sb = new StringBuffer();
    if (paramMap.isEmpty()) {
            return "";
    } else {
            for (String key : paramMap.keySet()) {
//                    if(!key.equals("phone")&&!key.equals("email")&&!key.equals("access_token")){
//                            continue;
//                    }
                    String value = (String) paramMap.get(key);
                    if (sb.length() < 1) {
                            sb.append(key).append("=").append(value);
                    } else {
                            sb.append("&").append(key).append("=").append(value);
                    }
            }
            return sb.toString();
    }
}

/**
 * 发送https请求
 * 
 * @param requestUrl 请求地址
 * @param requestMethod 请求方式（GET、POST）
 * @param outputStr 提交的数据
 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
 */
public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
    JSONObject jsonObject = null;
    try {
        // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustManager[] tm = { new MyX509TrustManager() };
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();

        URL url = new URL(requestUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setSSLSocketFactory(ssf);
        
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        // 设置请求方式（GET/POST）
        conn.setRequestMethod(requestMethod);

        // 当outputStr不为null时向输出流写数据
        if (null != outputStr) {
            OutputStream outputStream = conn.getOutputStream();
            // 注意编码格式
            outputStream.write(outputStr.getBytes("UTF-8"));
            outputStream.close();
        }

        // 从输入流读取返回内容
        InputStream inputStream = conn.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str = null;
        StringBuffer buffer = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }

        // 释放资源
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
        inputStream = null;
        conn.disconnect();
        jsonObject = JSONObject.fromObject(buffer.toString());
    } catch (ConnectException ce) {
        System.out.println("连接超时：{"+ce+"}");
    } catch (Exception e) {
    	 System.out.println("连接超时：{"+e+"}");
    }
    return jsonObject;
}
	
}
