package com.jhjz.emr.lstd_public.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeFormatUtils {

	/**
	 * 将字符串时间 格式化成 没有秒的字符创
	 */

	public static String StringTimeToFormatTimeString(String stringTime) {
		String dateString = "";
		String time = "";
		try {
			if (stringTime == null || "".equals(stringTime)) {
				return "";
			}
			time = stringTime.replace("T", " ").replace("/", "-");
			Date data = strToDateLong(time, "yyyy-MM-dd HH:mm");
			time = dateToStrLong(data, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return time;

	}

	/**
	 * 获取当前系统时间
	 * @return
	 */
	public static Date getTime(){
		return  new Date(System.currentTimeMillis());
	}
	/**
	 * 获取当前系统时间
	 * @return
	 */
	public static String getTimeStr(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		df.setTimeZone(TimeZone.getTimeZone("GMT+08"));
		LogUtil.d("hwz","当前时间"+df.format(new Date(System.currentTimeMillis()-30000)));
		return  df.format(new Date());
	}



	/**
	 * 获取当前系统时间
	 * @return
	 */
	public static String getTimeStr1(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
		df.setTimeZone(TimeZone.getTimeZone("GMT+08"));
		return  df.format(new Date());
	}
	public static String StringTimeForMat(String stringTime) {
		String dateString = "";
		String time = "";
		try {
			if (stringTime == null || "".equals(stringTime)) {
				return "";
			}
			time = stringTime.replace("T", " ").replace("/", "-");
			Date data = strToDateLong(time, "yyyy-MM-dd HH:mm");
			time = dateToStrLong(data, "MM-dd HH:mm");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return time;

	}
	public static String StringTimeForMat1(String stringTime) {
		String dateString = "";
		String time = "";
		try {
			if (stringTime == null || "".equals(stringTime)) {
				return "";
			}
			time = stringTime.replace("T", "").replace("/", "-");
			Date data = strToDateLong(time, "yyyy-MM-dd HH:mm");
			time = dateToStrLong(data, "HH:mm");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return time;

	}
	public static String StringTimeForMat5(String stringTime) {
		String dateString = "";
		String time = "";
		try {
			if (stringTime == null || "".equals(stringTime)) {
				return "";
			}
			time = stringTime.replace("T", "").replace("/", "-");
			Date data = strToDateLong(time, "yyyy-MM-dd HH:mm");
			time = dateToStrLong(data, "mm");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return time;

	}
	public static String StringTimeForMat3(String stringTime) {
		String dateString = "";
		String time = "";
		try {
			if (stringTime == null || "".equals(stringTime)) {
				return "";
			}
			time = stringTime.replace("T", " ").replace("/", "-");
			Date data = strToDateLong(time, "yyyy-MM-dd HH:mm");
			time = dateToStrLong(data, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return time;

	}
	public static String StringTimeForMat4(String stringTime) {
		String dateString = "";
		String time = "";
		try {
			if (stringTime == null || "".equals(stringTime)) {
				return "";
			}
			time = stringTime.replace("T", " ").replace("/", "-");
			Date data = strToDateLong(time, "yyyy-MM-dd HH:mm");
			time = dateToStrLong(data, "HH:mm");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return time;

	}
	public static String StringTimeForMat6(String stringTime) {
		String dateString = "";
		String time = "";
		try {
			if (stringTime == null || "".equals(stringTime)) {
				return "";
			}
			time = stringTime.replace("T", " ").replace("/", "-");
			Date data = strToDateLong(time, "yyyy-MM-dd");
			time = dateToStrLong(data, "yyyy年MM月dd日");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return time;

	}
	public static String StringTimeForMat7(String stringTime) {
		String dateString = "";
		String time = "";
		try {
			if (stringTime == null || "".equals(stringTime)) {
				return "";
			}
			time = stringTime.replace("T", " ").replace("/", "-");
			Date data = strToDateLong(time, "yyyy-MM-dd hh:mm");
			time = dateToStrLong(data, "HH:mm");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return time;

	}
	public static String StringTimeToFormatTime2String(String stringTime) {
		String dateString = "";
		String time = "";
		try {
			if (stringTime == null || "".equals(stringTime)) {
				return "";
			}
			time = stringTime.replace("T", " ").replace("/", "-");
			Date data = strToDateLong(time, "yyyy-MM-dd HH:mm:ss");
			time = dateToStrLong(data, "yyyy-MM-dd HH:mm");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return time;

	}

	@SuppressLint("SimpleDateFormat")
	public static Date strToDateLong(String strDate, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/** * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss * * @param dateDate * @return */
	@SuppressLint("SimpleDateFormat")
	public static String dateToStrLong(java.util.Date dateDate, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	//
	/**
	 * 与当前时间比较大小
	 * 
	 */
	@SuppressLint("SimpleDateFormat")
	public static boolean compareTime(String time) {
		try {
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 24小时制
			Date date = sdformat.parse(time);
			String dateNows = sdformat.format(new Date());
			Date dateNow=sdformat.parse(dateNows);
			long diff = date.getTime() - (dateNow.getTime());
			if (diff < 0 || diff == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * 比较两个时间大小
	 */
	public static boolean compareTime(String sendTime, String loginTime) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt_send = df.parse(sendTime);
			Date dt_login = df.parse(loginTime);
			if (dt_send.getTime() > dt_login.getTime()) {
				return true;
			} else if (dt_send.getTime() < dt_login.getTime()) {
				return false;
			} else {
				return false;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}
	@SuppressLint("SimpleDateFormat")
	public static boolean compareTime(Date date) {
		try {
			Date dateNow = new Date();
			long diff = date.getTime() - (dateNow.getTime());
			if (diff < 0 || diff == 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	/**
	 * 比较两个时间大小
	 */
	public static int compareTimeTwo(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	// 将时间戳转为字符串
	public static String getStrTime(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time));
		return re_StrTime;
	}

	/**
	 * 将时间戳转为字符串
	 * @param cc_time
	 * @return
	 */
	public static String getStrTime1(String cc_time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long lcc_time = Long.valueOf(cc_time);
		re_StrTime = sdf.format(new Date(lcc_time));
		return re_StrTime;
	}

	/**
	 * 检查上次签到时间距今是否大于8小时
	 * @param lastSignTime 上次签到时间
	 * @return
	 */
	public static boolean checkSignTime(String lastSignTime){
		boolean b = false;
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		try {
			Date last = sdformat.parse(lastSignTime);
			long disTime=System.currentTimeMillis()-last.getTime();
			long dis=disTime/3600000;
			LogUtil.d("hwz","时差A"+disTime);
			LogUtil.d("hwz","时差B"+dis);
			if(dis<8){
				b= false;
			}else{
				b= true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 检查选择图片时间距今是否大于2分钟
	 * @param lastSignTime 选择图片时间
	 * @return
	 */
	public static boolean checkTime(String lastSignTime){
		boolean b = false;
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		try {
			Date last = sdformat.parse(lastSignTime);
			long disTime=System.currentTimeMillis()-last.getTime();
			long dis=disTime/60000;
			LogUtil.d("hwz","时差A"+disTime);
			LogUtil.d("hwz","时差B"+dis);
			if(dis<2){
				b= false;
			}else{
				b= true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return b;
	}
	public static String getTimeExpend(String startTime, String endTime){
		long longStart = getTimeMillis(startTime); //获取开始时间毫秒数
		long longEnd = getTimeMillis(endTime);  //获取结束时间毫秒数
		long longExpend = longEnd - longStart;  //获取时间差
		long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
		long longMinutes2 = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000);   //根据时间差来计算分钟数
		long longMinutes = (longExpend / (60 * 1000)) ;   //根据时间差来计算分钟数longExpend /
//		+"    "+longHours+":"+longMinutes2

		if(longStart>longEnd) {
			return   ""+ longMinutes;
		}else {
			return  "+" +longMinutes;
		}

	}
	public static long getDisTimeHours(String time1,String time2){
		long longStart = getTimeMillis(time1); //获取开始时间毫秒数
		long longEnd = getTimeMillis(time2);  //获取结束时间毫秒数
		long longExpend = longEnd - longStart;  //获取时间差
		long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
		LogUtil.d("hwz","时间差为："+longHours+"小时");
		return longHours;
	}
	public static long getTimeMillis(String strTime) {
		long returnMillis = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d = null;
		try {
			d = sdf.parse(strTime);
			returnMillis = d.getTime();
		} catch (ParseException e) {
		}
		return returnMillis;
	}
	public static long getAddTime(String time,int i){
		long timeMillis = getTimeMillis(time);
		long addTime=0;
		if(i==15) {
			addTime =timeMillis+(15*60*1000);
		}else if(i==30) {
			addTime =timeMillis+(30*60*1000);
		}else if(i==60) {
			addTime =timeMillis+(60*60*1000);
		}else if(i==120) {
			addTime =timeMillis+(120*60*1000);
		}else if(i==240) {
			addTime =timeMillis+(240*60*1000);
		}
		return addTime;
	}
	public static String getAddTime2(long date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date1 = new Date(date);
		String addTime = sdf.format(date1);
		return addTime;
	}

	/**
	 * 参数为秒
	 * @param mss
	 * @return
	 */
	public static String getMin(long mss) {
		long minutes = mss/60;
		LogUtil.d("hwz",minutes+"分钟");
		return minutes+"";
	}

	/**
	 * 参数为米
	 * @param dis
	 * @return
	 */
	public static String getKm(int dis){
		int v=dis;
		LogUtil.d("hwz",v+"km");
		return v+"";
	}
}
