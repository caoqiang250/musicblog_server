package com.chenkaiyun.ssm.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 说明：日期处理
 * 创建人：CQ
 * 修改时间：2019年4月19日
 * @version
 */
public class DateUtil {
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
	private final static SimpleDateFormat sdfTimesSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private final static SimpleDateFormat sdfTimeYY = new SimpleDateFormat("yyMMddHHmmss");
	private final static SimpleDateFormat sdfBirthday = new SimpleDateFormat("MM-dd");
	
	/**
	 * 获取MMdd格式
	 * @return
	 */
	public static String sdfBirthday() {
		return sdfBirthday.format(new Date());
	}
	
	
	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}
	
	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	
	public static String getTimeSSS() {
		return sdfTimesSSS.format(new Date());
	}
	
	public static String getTimeYYMMDDHHMMSS() {
		return sdfTimeYY.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author RB
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	 
	/**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return YYYY-MM-dd
     */
    public static String getAfterDay(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    
    /**
     * 时间相减得到秒数
     * @param beginDateTimeStr
     * @param endDateTimeStr
     * @return
     */
    public static long getTimesSub(String beginDateTimeStr,String endDateTimeStr){
        long sec=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateTimeStr);
				endDate= format.parse(endDateTimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            sec=(endDate.getTime()-beginDate.getTime())/1000;
            
      
        return sec;
    }
    
    /**
     * 时间相减得到毫秒秒数
     * @param beginDateTimeStr
     * @param endDateTimeStr
     * @return
     */
    public static long getTimeSSSSub(String beginDateTimeStrSSS,String endDateTimeStrSSS){
    	long SSSsec=0;
    	java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS");
    	java.util.Date beginDate = null;
    	java.util.Date endDate = null;
    	
    	try {
    		beginDate = format.parse(beginDateTimeStrSSS);
    		endDate= format.parse(endDateTimeStrSSS);
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	SSSsec=endDate.getTime()-beginDate.getTime();
    	
    	
    	return SSSsec;
    }
    
    /**
     * 时间戳转字符串
     * @param timestamp
     * @return
     */
    public static String getTimestampToString(long timestamp){
    	Timestamp ts = new Timestamp(timestamp*1000L);
    	
    	String tsStr = ""; 

    	tsStr = sdfTime.format(ts); 
		return tsStr;
    }
    
    public static String getTimestampToString_orginal(long timestamp){
    	Timestamp ts = new Timestamp(timestamp);
    	
    	String tsStr = ""; 

    	tsStr = sdfTime.format(ts); 
		return tsStr;
    }
    
    //获取某一日期 YYYY-MM-dd 后第N个月第M天的日期
    public static String getDateMN(int n,int m){
    	Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,m);
		calendar.add(Calendar.MONTH, n);
    	return sdfDay.format(calendar.getTime());
    }
    

    
    public static void main(String[] args) throws InterruptedException {
		/*
		 * System.out.println(getDays()); System.out.println(getTimeYYMMDDHHMMSS());
		 * System.out.println(getAfterDayDate("30")); System.out.println(sdfBirthday());
		 * System.out.println(getAfterDay("30")); String beginTimeSSS=getTimeSSS();
		 * Thread.sleep(1000); String endTimeSSS=getTimeSSS(); long
		 * sub=getTimeSSSSub(beginTimeSSS,endTimeSSS); System.out.println(beginTimeSSS);
		 * System.out.println(endTimeSSS); System.out.println(sub);
		 */
    	System.out.println(getDateMN(1,15));
    	System.out.println(getAfterDay("7"));
    	
    }

}
