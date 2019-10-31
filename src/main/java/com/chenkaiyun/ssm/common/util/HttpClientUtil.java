package com.chenkaiyun.ssm.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil {
	
	//生成请求URL
	private static String genReqUrl(String url,PageData params){
		String reqUrl = "";
    	String paramStr = "";
		Object[] key_arr = params.keySet().toArray();
		for (Object key : key_arr) {
            String val = params.getString(key);
            if(null == val) {
            	continue;
            }
            try {
				key = URLEncoder.encode(key.toString(), "UTF-8");
				val = URLEncoder.encode(val, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            paramStr += "&" + key + "=" + val;
        }
		if(url.indexOf("?")<0) {//URL中不带有参数
			reqUrl = url + "?" + paramStr.substring(1);
		}else {
			reqUrl = url + paramStr;
		}
		return reqUrl;
	}
	
	//发送GET请求
	public static JSONObject doGet(String url,PageData params) throws UnsupportedEncodingException {
		//构建请求的url
    	String req_url =genReqUrl(url,params);
		//创建client对象
		CloseableHttpClient client = HttpClients.createDefault();
		//创建请求对象
    	HttpGet httpGet = new HttpGet(req_url);
    	//设置请求头
    	httpGet.setHeader("Accept", "application/json");
    	httpGet.setHeader("Content-Type","application/json"); 
    	String content = null;
    	CloseableHttpResponse response = null;
    	try {
    		response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity, "utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {    
				response.close();    
            } catch (IOException e) {    
                e.printStackTrace();    
            }    
		}
    	//关闭连接，释放资源
		try {
            client.close();    
        } catch (IOException e) {    
            e.printStackTrace();    
        } 
    	return JSONObject.parseObject(content);	
	}
	
	//发送POST请求
	public static JSONObject doPostJson(String url,PageData params) {
		//创建client对象
    	CloseableHttpClient client = HttpClients.createDefault();
    	//创建请求对象
    	HttpPost httpPost = new HttpPost(url);
    	//设置请求头
    	httpPost.setHeader("Accept", "application/json");
    	httpPost.setHeader("Content-Type","application/json");
    	//设置请求body  等同form中的表单元素
    	StringEntity se = null;
		se = new StringEntity(JSONObject.toJSONString(params),Charset.forName("UTF-8"));
    	se.setContentType("application/json");
    	httpPost.setEntity(se);
    	//发送请求，获取结果
    	String content = null;
    	CloseableHttpResponse response = null;
		try {
			response = client.execute(httpPost);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity, "utf-8"); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {    
				response.close();    
            } catch (IOException e) {    
                e.printStackTrace();    
            }    
		}
		//关闭连接，释放资源
		try {
            client.close();    
        } catch (IOException e) {    
            e.printStackTrace();    
        }      
    	return JSONObject.parseObject(content);  		
	}
	
	public static void main(String[] args) {
		HttpClientUtil.doPostJson("http://localhost/common/getBulletinList", null);
	}
}
