package com.chenkaiyun.ssm.common.util;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieUtils {
	/**
	 * 设置cookie（接口方法）
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		// if(maxAge>0){
		cookie.setMaxAge(maxAge);
		// }
		response.addCookie(cookie);
	}

	/**
	 * 根据名字获取cookie（接口方法）
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	/**
	 * 将cookie封装到Map里面（非接口方法）
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * 从request对象中获取客户端真实的ip地址
	 * 
	 * @param request
	 *            request对象
	 * @return 客户端的IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获得请求的baseurl(例如:http://www.xx.com:80)
	 * 
	 * @param request
	 * @return
	 */
	public static String getBaseUrl(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName();
	}

	/**
	 * 返回数据
	 * 
	 * @param response
	 * @param data
	 *            数据
	 * @param header
	 *            http头部
	 */
	public static void response(HttpServletResponse response, String data, Map<String, String> header) {
		if (header != null) {
			for (Entry<String, String> en : header.entrySet()) {
				response.setHeader(en.getKey(), en.getValue());
			}
		}
		try (Writer wr = response.getWriter()) {
			wr.write(data);
			wr.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void response(HttpServletResponse response, String data) {
		response(response, data, null);
	}
}
