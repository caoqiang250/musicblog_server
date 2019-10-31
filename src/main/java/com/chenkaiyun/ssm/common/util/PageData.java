package com.chenkaiyun.ssm.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageHelper;
/** 
 * 说明：参数封装Map
 * 创建人：CQ
 * 修改时间：2014年9月20日
 * @version
 */
public class PageData extends HashMap implements Map{
	
	private static final long serialVersionUID = 1L;
	
	Map map = null;
	HttpServletRequest request;
	
	//封装了errorCode 为 0 的信息
	public static PageData getSpd(){
		PageData pd = new PageData();
		pd.put("errorCode", 0);
		return pd;
	}
	
	//封装了errorCode 为 -1 的信息
	public static PageData getFpd(){
		PageData pd = new PageData();
		pd.put("errorCode", -1);
		return pd;
	}
	
	public PageData(HttpServletRequest request){
		this.request = request;
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap(); 
		Iterator entries = properties.entrySet().iterator(); 
		Map.Entry entry; 
		String name = "";  
		String value = "";  
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next(); 
			name = (String) entry.getKey(); 
			Object valueObj = entry.getValue(); 
			if(null == valueObj){ 
				value = ""; 
			}else if(valueObj instanceof String[]){ 
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){ 
					 value = values[i] + ",";
				}
				value = value.substring(0, value.length()-1); 
			}else{
				value = valueObj.toString(); 
			}
			returnMap.put(name, value); 
		}
		map = returnMap;
	}
	
	public PageData() {
		map = new HashMap();
	}
	
	//快捷创建返回信息
	public PageData(int errorCode,String errorMsg) {
		Map returnMap = new HashMap(); 
		returnMap.put("errorCode", errorCode);
		returnMap.put("errorMsg", errorMsg);
		map = returnMap;
	}
	
	@Override
	public Object get(Object key) {
		Object obj = null;
		if(map.get(key) instanceof Object[]) {
			Object[] arr = (Object[])map.get(key);
			obj = request == null ? arr:(request.getParameter((String)key) == null ? arr:arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}
	
	public String getString(Object key) {
		if(get(key) == null){
			return null;
		}if(get(key) instanceof String){
			return (String)get(key);			
		}else if(get(key) instanceof BigInteger){
			return String.valueOf((BigInteger)get(key));
		}else if(get(key) instanceof Integer){
			return String.valueOf((Integer)get(key));
		}else if(get(key) instanceof Long){
			return String.valueOf((Long)get(key));
		}else if(get(key) instanceof BigDecimal) {
			return String.valueOf((BigDecimal)get(key));
		}else if(get(key) instanceof Float) {
			return String.valueOf((Float)get(key));
		}else if(get(key) instanceof Double) {
			return String.valueOf((Double)get(key)); 
		}
		return "";
	}
	
	public int getInt(Object key) {
		if(get(key) instanceof Integer){
			return (Integer)get(key);			
		}else if(get(key) instanceof BigInteger){
			return ((BigInteger)get(key)).intValue();
		}else if(get(key) instanceof String){
			return Integer.parseInt(getString(key));
		}else if(get(key) instanceof Long){
			return ((Long)get(key)).intValue();
		}else if(get(key) instanceof BigDecimal) {
			return ((BigDecimal)get(key)).intValue();  
		}else if(get(key) instanceof Float) {
			return ((Float)get(key)).intValue();  
		}else if(get(key) instanceof Double) {
			return ((Double)get(key)).intValue(); 
		}
		return 0;
	}
	
	//设置分页信息 默认第一页，10条
	public void setPageInfo() {
		int pageNum = this.getInt("pageNum");
		int pageSize = this.getInt("pageSize");
		PageHelper.startPage(pageNum>0?pageNum:1, pageSize>0?pageSize:10);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key, Object value) {
		return map.put(key, value);
	}
	
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	public void clear() {
		map.clear();
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	public Set entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	public Set keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	public Collection values() {
		// TODO Auto-generated method stub
		return map.values();
	}
	
}
