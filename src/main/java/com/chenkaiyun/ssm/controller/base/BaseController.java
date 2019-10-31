package com.chenkaiyun.ssm.controller.base;

import java.io.BufferedReader;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.chenkaiyun.ssm.common.Constant;
import com.chenkaiyun.ssm.common.util.CookieUtils;
import com.chenkaiyun.ssm.common.util.PageData;

public class BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger("logger");
	/**
	 * new PageData对象
	 * 
	 * @return
	 */
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}
	
	/**
	 * new PageData对象
	 * 
	 * @return
	 */
	public PageData getPageDataByJson() {
		PageData pd= new PageData();
		String line = "";
		StringBuffer json = new StringBuffer();
		try {
			BufferedReader reader = this.getRequest().getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject jsonObj = JSONObject.parseObject(json.toString());
		pd.putAll(jsonObj);
		return pd;
	}
	
	//得到cookie值
	public String getCookieValue(String cookie_name) {
		Cookie cookie = CookieUtils.getCookieByName(getRequest(), cookie_name);
		return null == cookie?"":cookie.getValue();
	}

	/**
	 * 得到request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}
	
	/**
	 * 得到response对象
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		return response;
	}
	
	/**
	 * 得到session对象
	 * 
	 * @return
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}
	
}
