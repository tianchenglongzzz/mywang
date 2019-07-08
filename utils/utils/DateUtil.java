package com.jhjz.emr.lstd_public.utils;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期操作工具类.
 */

public class DateUtil {

    /**
     * 英文简写如：2016
     */
    public static String FORMAT_Y = "yyyy";

    /**
     * 英文简写如：12:01
     */
    public static String FORMAT_HM = "HH:mm";

    /**
     * 英文简写如：1-12 12:01
     */
    public static String FORMAT_MDHM = "MM-dd HH:mm";

    /**
     * 英文简写（默认）如：2016-12-01
     */
    public static String FORMAT_YMD = "yyyy-MM-dd";

    /**
     * 英文全称  如：2016-12-01 23:15
     */
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    /**
     * 英文全称  如：2016-12-01 23:15:06
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL_SN = "yyyyMMddHHmmssS";

    /**
     * 中文简写  如：2016年12月01日
     */
    public static String FORMAT_YMD_CN = "yyyy年MM月dd日";

    /**
     * 中文简写  如：2016年12月01日  12时
     */
    public static String FORMAT_YMDH_CN = "yyyy年MM月dd日 HH时";

    /**
     * 中文简写  如：2016年12月01日  12时12分
     */
    public static String FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分";

    /**
     * 中文全称  如：2016年12月01日  23时15分06秒
     */
    public static String FORMAT_YMDHMS_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    public static Calendar calendar = null;
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static Date str2Date(String str) {
        return str2Date(str, null);
    }


    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);
    }


    public static Calendar str2Calendar(String str, String format) {

        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;
    }


    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }


    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }


    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }


    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }


    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH) + "-" +
                c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) +
                ":" + c.get(Calendar.SECOND);
    }
    public static String getCurrentDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH) + " " +
                c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
    }
    public static String getDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" +
                c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getYear() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR);
    }

    /**
     * 获得当前日期的字符串格式
     *
     * @param format 格式化的类型
     * @return 返回格式化之后的事件
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);

    }


    /**
     * @param time 当前的时间
     * @return 格式到分
     */

    public static String getMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(time);

    }


    /**
     * @param time 当前的时间
     * @return 当前的天
     */
    public static String getDay(long time) {

        return new SimpleDateFormat("yyyy-MM-dd").format(time);

    }


    /**
     * @param time 时间
     * @return 返回一个毫秒
     */
    // 格式到毫秒
    public static String getSMillon(long time) {

        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

    }


    /**
     * 在日期上增加数个整月
     *
     * @param date 日期
     * @param n    要增加的月数
     * @return 增加数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();

    }


    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param n    要增加的天数
     * @return 增加之后的天数
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();

    }


    /**
     * 获取距现在某一小时的时刻
     *
     * @param format 格式化时间的格式
     * @param h      距现在的小时 例如：h=-1为上一个小时，h=1为下一个小时
     * @return 获取距现在某一小时的时刻
     */
    public static String getNextHour(String format, int h) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        date.setTime(date.getTime() + h * 60 * 60 * 1000);
        return sdf.format(date);

    }


    /**
     * 获取时间戳
     *
     * @return 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());

    }


    /**
     * 功能描述：返回月
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }


    /**
     * 功能描述：返回日
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 功能描述：返回小
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * 功能描述：返回分
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static long getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }


    /**
     * 获得默认的 date pattern
     *
     * @return 默认的格式
     */
    public static String getDatePattern() {

        return FORMAT_YMDHMS;
    }


    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        calendar = Calendar.getInstance();

        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }


    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return 提取字符串的日期
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());

    }


    /**
     * 功能描述：返回毫
     *
     * @param date 日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }


    /**
     * 按默认格式的字符串距离今天的天数
     *
     * @param date 日期字符串
     * @return 按默认格式的字符串距离今天的天数
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;

    }


    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return 提取字符串日期
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * 按用户格式字符串距离今天的天数
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @return 按用户格式字符串距离今天的天数
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 按用户格式字符串返回时间戳
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @return 按用户格式字符串时间戳
     */
    public static long getTimestamp(String date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date time = new Date();
        try {
            time = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time.getTime();
    }



    //获得时间范围
    public static String getResidueTime(long startTime, long endTime) {
        long residueTime = endTime - startTime;
        long daynum = residueTime / (1000 * 60 * 60 * 24);
        long hourl = (residueTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        String time = daynum + "小时" + hourl + "分";
        return time;
    }

    /**
     * 将毫秒转化成固定格式的时间
     * 时间格式: yyyy-MM-dd HH:mm
     *
     * @param millisecond
     * @return
     */
    public static String getDateTimeFromMillisecond(Long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    public static String getDateTimeFromMillisecond1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String date = sdf.format(new Date());
        return date;
    }

    public static String getDateTimeHour(Long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(millisecond);
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    /**
     * 倒计时获取天数
     *
     * @return
     */
    public static String getTimeDay(long residueTime) {
//        long endtime = Long.parseLong(startTime);
//        long currentTimeMillis = System.currentTimeMillis();
//        long residueTime = endtime - currentTimeMillis;
        long daynum = residueTime / (1000 * 60 * 60 * 24);
        return daynum + "";
    }

    /**
     * 倒计时获取小时
     *
     * @return
     */
    public static String getTimeHour(long residueTime) {
//        long endtime = Long.parseLong(startTime);
//        long currentTimeMillis = System.currentTimeMillis();
//        long residueTime = endtime - currentTimeMillis;
        long hour = (residueTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        return hour + "";

    }

    /**
     * 倒计时获取分钟
     *
     * @return
     */
    public static String getTimeMinute(long residueTime) {
//        long endtime = Long.parseLong(startTime);
//        long currentTimeMillis = System.currentTimeMillis();
//        long residueTime = endtime - currentTimeMillis;
        long minute = (residueTime % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60) / (1000 * 60);
        return minute + "";

    }

    /**
     * 倒计时获取分钟
     *
     * @return
     */
    public static long getTimeMinute1(long residueTime) {
//        long endtime = Long.parseLong(startTime);
//        long currentTimeMillis = System.currentTimeMillis();
//        long residueTime = endtime - currentTimeMillis;
        long minute = (residueTime % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60) / (1000 * 60);
        return minute;
    }

    /**
     * 倒计时获取秒
     *
     * @return
     */
    public static String getTimeSeconds(long residueTime) {
//        long endtime = Long.parseLong(startTime);
//        long currentTimeMillis = System.currentTimeMillis();
//        long residueTime = endtime - currentTimeMillis;
        long seconds = residueTime / 1000 -
                Long.parseLong(getTimeDay(residueTime)) * 24 * 60 * 60 -
                Long.parseLong(getTimeHour(residueTime)) * 60 * 60 -
                Long.parseLong(getTimeMinute(residueTime)) * 60;
        return seconds + "";

    }

    /**
     * 日期格式转换时间戳
     *
     * @return
     */
    public static String getTime(String timeString) {
        String timeStamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d;
        try {
            d = sdf.parse(timeString);
            long l = d.getTime();
            timeStamp = String.valueOf(l);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStamp;
    }

    /**
     * 获取距离更新时的天数
     *
     * @return
     */
    public static String getLocationTimeDay(String startTime) {
        long time = Long.parseLong(startTime);
        long currentTimeMillis = System.currentTimeMillis();
        long residueTime = currentTimeMillis - time;
        long daynum = residueTime / (1000 * 60 * 60 * 24);
        return daynum + "";
    }

    /**
     * 获取距离更新时的小时
     *
     * @return
     */
    public static String getLocationTimeHour(String startTime) {
        long time = Long.parseLong(startTime);
        long currentTimeMillis = System.currentTimeMillis();
        long residueTime = currentTimeMillis - time;
        long hour = (residueTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        return hour + "";

    }

    /**
     * 获取距离更新时的分钟
     *
     * @return
     */
    public static String getLocationTimeMinute(String startTime) {
        long time = Long.parseLong(startTime);
        long currentTimeMillis = System.currentTimeMillis();
        long residueTime = currentTimeMillis - time;
        long minute = (residueTime % (1000 * 60 * 60 * 24)) % (1000 * 60 * 60) / (1000 * 60);
        return minute + "";

    }

    /**
     * 点击后给textview设置当前时间(一个tv)
     *
     * @param textView
     */
    public static void setCurrentTime(final TextView textView, boolean b) {
        if (b) {
            LogUtil.d("hwz", "" + b);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String string = textView.getText().toString();
                    if (TextUtils.isEmpty(string)||string==null){
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
                        df.setTimeZone(TimeZone.getTimeZone("GMT+08"));
                        Date curDate = new Date(System.currentTimeMillis());
                        String str = df.format(curDate);
                        textView.setText(str);
                    }else {
                        return;
                    }

                }
            });
        } else {
            LogUtil.d("hwz", "" + b);
            textView.setOnClickListener(null);
        }

    }



    /**
     *  点击传入的控件，弹出时间选择
     * @param view 传入的控件
     * @param activity 传入的界面
     */
    public static void selectTime(final View view, final Activity activity) {
        if(view instanceof EditText){
            final EditText editText=(EditText)view;
            editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(activity, editText.getText().toString(), "yyyy-MM-dd HH:mm");
                    dateTimePicKDialog.dateTimePicKDialogListener(editText
                            , new DateTimePickDialogUtil.ConfirmListerer() {

                                @Override
                                public void timeConfirmListener(String selectedTime) {

                                }
                            });
                }
            });
        }else if(view instanceof TextView){
            final TextView textView=(TextView)view;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(activity, textView.getText().toString(), "yyyy-MM-dd HH:mm");
                    dateTimePicKDialog.dateTimePicKDialogListener(textView
                            , new DateTimePickDialogUtil.ConfirmListerer() {

                                @Override
                                public void timeConfirmListener(String selectedTime) {

                                }
                            });
                }
            });
        }

    }
//    /**
//     *  点击传入的控件，弹出时间选择
//     * @param view 传入的控件
//     * @param activity 传入的界面
//     */
//    public static void selectTime(final TextView view, Activity activity) {
//        DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(activity, "", "yyyy-MM-dd HH:mm");
//        dateTimePicKDialog.dateTimePicKDialogListener(view
//                , new DateTimePickDialogUtil.ConfirmListerer() {
//
//
//                    @Override
//                    public void timeConfirmListener(String selectedTime) {
//                        view.setText(selectedTime);
//                    }
//                });
//
//    }
    /**
     * 将时间转化成毫秒
     * 时间格式: yyyy-MM-dd HH:mm
     *
     * @param time
     * @return
     */
    public static long timeStrToSecond(String time) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            long second = format.parse(time).getTime();
            return second;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * 获取时间间隔
     *
     * @param millisecond
     * @return
     */
    public static long getSpaceTime(Long millisecond) {
        Calendar calendar = Calendar.getInstance();
        Long currentMillisecond = calendar.getTimeInMillis();

        //间隔秒
        Long spaceSecond = (currentMillisecond - millisecond) / 1000;

        //一分钟之内
//        if (spaceSecond >= 0 && spaceSecond < 60) {
//            return "片刻之前";
//        }
        //一小时之内
//        else
//            if (spaceSecond / 60 > 0 && spaceSecond / 60 < 60) {
        return spaceSecond / 60;
//        }
        //一天之内
//        else if (spaceSecond / (60 * 60) > 0 && spaceSecond / (60 * 60) < 24) {
//            return spaceSecond / (60 * 60) + "小时之前";
//        }
//        //3天之内
//        else if (spaceSecond/(60*60*24)>0&&spaceSecond/(60*60*24)<3){
//            return spaceSecond/(60*60*24)+"天之前";
//        }else {
//            return getDateTimeFromMillisecond(millisecond);
//        }
    }

    /**
     * 判断输入的时间距离现在是否大于等于两分钟
     *
     * @param beforeTime
     * @return
     */
    public static boolean isOutnumber2min(String beforeTime) {
        long bdfroe = timeStrToSecond(beforeTime);
        long disTime = getSpaceTime(bdfroe);
        LogUtil.d("hwz", "差值" + disTime);
        if (disTime >= 2) {
            return true;
        } else {
            return false;
        }

    }

}
