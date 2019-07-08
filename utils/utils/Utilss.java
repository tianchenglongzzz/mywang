package com.jhjz.emr.lstd_public.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.Toast;

import com.jhjz.emr.lstd_public.CzjjMap_Application;
import com.jhjz.emr.lstd_public.R;
import com.jhjz.emr.lstd_public.bean.LoginBean;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static android.content.Context.TELEPHONY_SERVICE;

public class Utilss {

	// 页面跳转
	public static void startActivity(Context context, @SuppressWarnings("rawtypes") Class desti) {
		Intent intent = new Intent();
		intent.setClass(context, desti);
		context.startActivity(intent);
		// 关闭当前的Activity
		// ((Activity) context).finish();
	}

	public static View getView(Context context, int layoutId) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View layout = inflater.inflate(layoutId, null);
		return layout;
	}

	public static View getView(Activity context, int layoutId) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View layout = inflater.inflate(layoutId, null);
		return layout;
	}

	// 获得汉语拼音首字母--城市
	@SuppressLint("DefaultLocale")
	public static String getAlpha(String str) {
		if (str.equals("-")) {
			return "#";
		}
		if (str == null || str.trim().length() == 0) {
			return "#";
		}
		char c = str.trim().substring(0, 1).charAt(0);
		// 正则表达式，判断首字母是否是英文字母
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase();
		} else {
			return "#";
		}
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 弹出框
	 */

	public static void showToast(Context context, String content) {
		Toast.makeText(context, content, Toast.LENGTH_LONG).show();
	}

	public static void showToastShort(Context context, String content) {
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 打开键盘
	 */

	public static void openKeyBoard(Context context) {
		// 1.得到InputMethodManager对象
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		// 2.调用showSoftInput方法显示软键盘，其中view为聚焦的view组件
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

	}

	/**
	 * 关闭键盘
	 */
	public static void closeKeyboard(Context context) {
		View view = ((Activity) context).getWindow().peekDecorView();
		if (view != null) {
			// 1.得到InputMethodManager对象
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			// 2.调用hideSoftInputFromWindow方法隐藏软键盘
			imm.hideSoftInputFromWindow(view.getWindowToken(), 0); // 强制隐藏键盘
		}
	}

	public static String listToString(List<String> stringList) {
		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(",");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	/**
	 * 验证密码
	 */

	public static boolean isPassWord(String pass) {

		String str = "^[0-9_a-zA-Z]{6,19}$";

		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(pass);

		if (m.matches()) {
			return true;
		}
		return false;

	}

	public static boolean isPassStrong(String pass) {
		String strongStr =
				"^(?=.{6,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$";
		Pattern p = Pattern.compile(strongStr);
		Matcher m = p.matcher(pass);
		return m.matches();
	}

	public static boolean isPassMedium(String pass) {
		String strongStr =
				"^(?=.{6,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*\\W)(?=.*[0-9]))|((?=.*\\W)(?=.*[a-z]))|((?=.*\\W)(?=.*[A-Z]))).*$";
		Pattern p = Pattern.compile(strongStr);
		Matcher m = p.matcher(pass);
		return m.matches();
	}

	public static boolean isPassEnough(String pass) {
		String strongStr = "(?=.{6,}).*";
		Pattern p = Pattern.compile(strongStr);
		Matcher m = p.matcher(pass);
		return m.matches();
	}

	/**
	 * 验证邮编
	 *
	 * @param
	 * @return
	 */
	public static boolean isZipNO(String zipString) {
		Pattern p = Pattern.compile("[0-9]*");
		Matcher m = p.matcher(zipString);
		return m.matches();
	}

	/**
	 * 验证手机号码
	 *
	 * @param mobile
	 * @return
	 */
	public static boolean isMobileNo(String mobile) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * 设置过滤字符函数(过滤掉我们不需要的字符)
	 *
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static String stringFilter(String str) throws PatternSyntaxException {
		String regEx = "[/\\:*?<>|\"\n\t]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("");
	}

	public static String Unicode2Chinese(String strUnicode) {
		try {
			return URLEncoder.encode(strUnicode.trim(), "gb2312");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * 判断是否包含中文
	 */
	static String regEx = "[\u4e00-\u9fa5]";
	static Pattern pat = Pattern.compile(regEx);

	public static boolean isContainsChinese(String str) {
		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find()) {
			flg = true;
		}
		return flg;
	}

	/**
	 * 判断路径连接是否包含域名
	 *
	 * @param url
	 * @return
	 */
	public static boolean isUrlHasHttp(String url) {
		boolean has = false;

		String temp = url.substring(0, 4);
		if (temp.equals("http")) {
			has = true;
		}
		return has;
	}


	/**
	 * 判断字符串是价格格式
	 *
	 * @param str
	 * @return
	 */
	public static boolean isPrice(String str) {// 判断小数，与判断整型的区别在与d后面的小数点（红色）
		return str.matches("(\\d+\\.\\d+$)|(^\\d+$$)");
	}

	/**
	 * listView垂直滚动
	 *
	 */
	/**
	 * scroll Vertical
	 *
	 * @param
	 * @param y
	 *            垂直滑动的距离
	 */
	public static void scrollVertical(final ListView listView, Activity activity, final int y) {
		if (listView == null)
			return;
		activity.runOnUiThread(new Runnable() { // 执行自动化测试的时候模拟滑动需要进入UI线程操作
			@Override
			public void run() {
				invokeMethod(listView, "trackMotionScroll", new Object[]{-y, -y}, // 反射listView父类的方法
						new Class[]{int.class, int.class});
			}
		});
	}

	/**
	 * 遍历当前类以及父类去查找方法，例子，写的比较简单，反射的方法
	 *
	 * @param object
	 * @param methodName
	 * @param params
	 * @param paramTypes
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	private static Object invokeMethod(Object object, String methodName, Object[] params, Class[] paramTypes) {
		Object returnObj = null;
		if (object == null) {
			return null;
		}
		Class cls = object.getClass();
		Method method = null;
		for (; cls != Object.class; cls = cls.getSuperclass()) { // 因为取的是父类的默认修饰符的方法，所以需要循环找到该方法
			try {
				method = cls.getDeclaredMethod(methodName, paramTypes);
				break;
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
		if (method != null) {
			method.setAccessible(true);
			try {
				returnObj = method.invoke(object, params);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnObj;
	}

	public static int stringToInt(String string) {
		String str = string.substring(0, string.indexOf(".")) + string.substring(string.indexOf(".") + 1);
		int intgeo = Integer.parseInt(str);
		return intgeo;
	}

	public static String getCurrentMinute() {

		Calendar c = Calendar.getInstance();
		// int year = c.get(Calendar.YEAR);
		// int month = c.get(Calendar.MONTH);
		// int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		return hour + ":" + minute;

	}

	/**
	 * 日期转换
	 */
	@SuppressLint("SimpleDateFormat")
	public static String dateFix(String date) {
		String re_StrTime = null;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		long lcc_time = Long.valueOf(date);
		re_StrTime = sdf.format(new Date(lcc_time * 1000L));

		return re_StrTime;

	}

	/**
	 * 获取屏幕的宽度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		// 获取屏幕宽度
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		return width;

	}

	/**
	 * 获取屏幕的高度
	 *
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		// 获取屏幕高度
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
		int height = dm.heightPixels;
		return height;
	}

	/**
	 * 取小数点后两位
	 *
	 * @param price
	 * @return
	 */
	public static String priceFix(double price) {
		DecimalFormat df = new DecimalFormat("#0.0");
		return df.format(price);
	}

	/**
	 *患者转归后，禁用界面所有控件
	 * fragment中使用
	 */
	public static void disableAllViews(LoginBean.HzListBean patient, View view) {
		if (patient.getHZStateReason() != null && patient.getHZStateReason().equals("患者已转归")) {
			//该患者已转归 所有控件不能再操作
			List<View> views = getAllChildViews(view);
			for (int i = 0; i < views.size(); i++) {
				if (views.get(i).getId() != R.id.toolbar) {
					views.get(i).setEnabled(false);
					views.get(i).setFocusable(false);
					views.get(i).setClickable(false);
				}
			}
		}
	}

	/**
	 * 获取View中的所有子View
	 * @param view
	 * @return 返回包含所有子view的集合
	 */
	private static List<View> getAllChildViews(View view) {
		List<View> allchildren = new ArrayList<View>();
		if (view instanceof ViewGroup) {
			ViewGroup vp = (ViewGroup) view;
			for (int i = 0; i < vp.getChildCount(); i++) {
				View viewchild = vp.getChildAt(i);
				allchildren.add(viewchild);
				allchildren.addAll(getAllChildViews(viewchild));

			}
		}
		return allchildren;
	}

	/**
	 * 通过反射，获取包含虚拟键的整体屏幕高度
	 *
	 * @return
	 */
	public static int getHasVirtualKey(Activity activity) {
		int dpi = 0;
		Display display = activity.getWindowManager().getDefaultDisplay();
		DisplayMetrics dm = new DisplayMetrics();
		@SuppressWarnings("rawtypes")
		Class c;
		try {
			c = Class.forName("android.view.Display");
			@SuppressWarnings("unchecked")
			Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
			method.invoke(display, dm);
			dpi = dm.heightPixels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dpi;
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 *
	 * @param spValue
	 * @param
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取手机设备id
	 * @return
	 */
	public static String getDeviceID() {
		TelephonyManager TelephonyMgr = (TelephonyManager) CzjjMap_Application.getInstance().getSystemService(TELEPHONY_SERVICE);
		@SuppressLint("MissingPermission")
		String szImei = TelephonyMgr.getDeviceId();
		LogUtil.d("hwz","本设备ID:"+szImei);
		if(szImei==null){
			return "";
		}else{
			return szImei;
		}
	}
}
