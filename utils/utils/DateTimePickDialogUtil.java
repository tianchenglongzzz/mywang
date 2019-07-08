package com.jhjz.emr.lstd_public.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

import com.jhjz.emr.lstd_public.CzjjMap_Application;
import com.jhjz.emr.lstd_public.R;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 日期时间选择控件 使用方法： private EditText inputDate;//需要设置的日期时间文本编辑框 private String
 * initDateTime="2012年9月3日 14:44",//初始日期时间值 在点击事件中使用：
 * inputDate.setOnClickListener(new OnClickListener() {
 *
 * @Override public void onClick(View v) { DateTimePickDialogUtil
 *           dateTimePicKDialog=new
 *           DateTimePickDialogUtil(SinvestigateActivity.this,initDateTime);
 *           dateTimePicKDialog.dateTimePicKDialog(inputDate);
 *
 *           } });
 *
 * @author
 */
public class DateTimePickDialogUtil   implements OnDateChangedListener, OnTimeChangedListener {
	private DatePicker datePicker;
	private TimePicker timePicker;
	private Dialog ad;
	private String dateTime;
	private String initDateTime;
	private String time_format;
	private final Activity activity;

	/**
	 * 日期时间弹出选择框构造函数
	 *
	 * @param activity
	 *            ：调用的父activity
	 * @param initDateTime
	 *            初始日期时间值，作为弹出窗口的标题和日期时间初始值
	 */
	public DateTimePickDialogUtil(Activity activity, String initDateTime, String time_format) {
		this.activity = activity;
		this.initDateTime = initDateTime;
		this.time_format = time_format;
		if(this.initDateTime.equals("")||this.initDateTime==null){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			df.setTimeZone(TimeZone.getTimeZone("GMT+08"));
			this.initDateTime = df.format(new Date());// new Date()为获取当前系统时间
		}
	}

	public void init(DatePicker datePicker, TimePicker timePicker) {
		Log.i("mine", time_format);
		if (null == time_format || "".equals(time_format)) {
			time_format = "yyyy-MM-dd HH:mm";
		}

		Calendar calendar = Calendar.getInstance();
		if (!(null == initDateTime || "".equals(initDateTime))) {
			calendar = this.getCalendarByInintData(initDateTime);
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			df.setTimeZone(TimeZone.getTimeZone("GMT+08"));
			initDateTime = df.format(new Date());// new Date()为获取当前系统时间
//			initDateTime = new SimpleDateFormat(time_format).format(new java.util.Date());
		}

		datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), this);
		timePicker.setIs24HourView(true);
		timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
	}

	/**
	 * 弹出日期时间选择框方法
	 *
	 * @param inputDate
	 *            :为需要设置的日期时间文本编辑框
	 * @return
	 */
	public Dialog dateTimePicKDialog(final EditText inputDate) {
		LinearLayout dateTimeLayout = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.common_datetime, null);
		String et_content = inputDate.getText().toString();
		datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
		timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
		resizePikcer(datePicker);// 调整datepicker大小
		resizePikcer(timePicker);// 调整timepicker大小
		if (time_format.equals("yyyy-MM-dd")) {
			timePicker.setVisibility(View.GONE);
		}
		init(datePicker, timePicker);
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(this);

		ad = new AlertDialog.Builder(activity).setTitle(initDateTime).setView(dateTimeLayout).setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				if (TimeFormatUtils.compareTime(dateTime)) {
					inputDate.setText(dateTime);
				} else {
//					Toast.makeText(activity, "您选择的时间为 将来时间", Toast.LENGTH_LONG).show();
				}
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				// inputDate.setText("");
			}
		}).setNeutralButton("删除", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				ToastUtil.show(activity,"已删除", Toast.LENGTH_SHORT);
				inputDate.setText(null);

			}
		}).show();

		onDateChanged(null, 0, 0, 0);
		return ad;
	}

	/**
	 * 弹出日期时间选择框方法
	 *
	 * @return
	 */
	public interface ConfirmListerer {
		void timeConfirmListener(String selectedTime);
	}

	public interface MyListener{
		void timeConfirmListener(String selectedTime);
		void deleteLister();
	}

	public interface SaveListerer {
		void saveAllListener(String time);
		void saveOneListener(String time);
	}

	public interface ConfirmTimeListerer {
		boolean timeConfirmListener(String time);
	}

//	public AlertDialog dateTimePicKDialogListener(final TextView inputDate, @Nullable final ConfirmListerer listener) {
//		LinearLayout dateTimeLayout = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.common_datetime, null);
//
//		String et_content = inputDate.getText().toString();
//		datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
//		timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
//		setDatePickerDividerColor(datePicker);
//		setTimePickerDividerColor(timePicker);
//		resizePikcer(datePicker);// 调整datepicker大小
//		resizePikcer(timePicker);// 调整timepicker大小
//		if (time_format.equals("yyyy-MM-dd")) {
//			timePicker.setVisibility(View.GONE);
//		}
//		init(datePicker, timePicker);
//		timePicker.setIs24HourView(true);
//		timePicker.setOnTimeChangedListener(this);
//
//		ad = new AlertDialog.Builder(activity).setTitle(initDateTime)
//				.setView(dateTimeLayout)
//				.setPositiveButton("保存", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int whichButton) {
//				if (TimeFormatUtils.compareTime(dateTime)) {
//					inputDate.setText(dateTime);
//					if(listener!=null){
//						listener.timeConfirmListener(dateTime);
//					}
//				} else {
////					Toast.makeText(activity, "您选择的时间为 将来时间", Toast.LENGTH_LONG).show();
//				}
//
//			}
//		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int whichButton) {
//				// inputDate.setText("");
//			}
//		}).setNeutralButton("删除", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialogInterface, int i) {
//						ToastUtil.show(activity,"已删除", Toast.LENGTH_SHORT);
//						inputDate.setText(null);
//					}
//				}).show();
//		onDateChanged(null, 0, 0, 0);
//		return ad;
//	}

	public Dialog dateTimePicKDialogListener(final TextView inputDate, @Nullable final ConfirmListerer listener) {
		FrameLayout dateTimeLayout = (FrameLayout) activity.getLayoutInflater().inflate(R.layout.custom_alertdialog_datetime, null);
		String et_content = inputDate.getText().toString();
		datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
		timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
		TextView tv_title = dateTimeLayout.findViewById(R.id.tv_title);
		TextView tv_cancle = dateTimeLayout.findViewById(R.id.tv_cancle);
		TextView tv_delete = dateTimeLayout.findViewById(R.id.tv_delete);
		TextView tv_save = dateTimeLayout.findViewById(R.id.tv_save);

		setDatePickerDividerColor(datePicker);
		setTimePickerDividerColor(timePicker);
		resizePikcer(datePicker);// 调整datepicker大小
		resizePikcer(timePicker);// 调整timepicker大小
		if (time_format.equals("yyyy-MM-dd")) {
			timePicker.setVisibility(View.GONE);
		}
		init(datePicker, timePicker);
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(this);

		tv_title.setText(initDateTime);
		tv_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
                ad.dismiss();
				if (TimeFormatUtils.compareTime(dateTime)){
					inputDate.setText(dateTime);
					if(listener!=null){
						listener.timeConfirmListener(dateTime);
					}
				}
			}
		});
		tv_cancle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
                ad.dismiss();
			}
		});
		tv_delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
                ad.dismiss();
				ToastUtil.show(activity,"已删除", Toast.LENGTH_SHORT);
				inputDate.setText(null);
			}
		});

        ad = new Dialog(activity);
        ad.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ad.getWindow().setContentView(dateTimeLayout);
        //3.设置指定的宽高,如果不设置的话，弹出的对话框可能不会显示全整个布局，当然在布局中写死宽高也可以
        WindowManager.LayoutParams lp     = new WindowManager.LayoutParams();
        Window                     window = ad.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //注意要在Dialog show之后，再将宽高属性设置进去，才有效果
        ad.show();
        window.setAttributes(lp);
        onDateChanged(null, 0, 0, 0);
        return ad;
	}

	public Dialog dateTimeLinePicKDialogListener(final TextView inputDate, @Nullable final MyListener listener) {
		FrameLayout dateTimeLayout = (FrameLayout) activity.getLayoutInflater().inflate(R.layout.custom_alertdialog_datetime, null);
		String et_content = inputDate.getText().toString();
		datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
		timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
		TextView tv_title = dateTimeLayout.findViewById(R.id.tv_title);
		TextView tv_cancle = dateTimeLayout.findViewById(R.id.tv_cancle);
		TextView tv_delete = dateTimeLayout.findViewById(R.id.tv_delete);
		TextView tv_save = dateTimeLayout.findViewById(R.id.tv_save);

		setDatePickerDividerColor(datePicker);
		setTimePickerDividerColor(timePicker);
		resizePikcer(datePicker);// 调整datepicker大小
		resizePikcer(timePicker);// 调整timepicker大小
		if (time_format.equals("yyyy-MM-dd")) {
			timePicker.setVisibility(View.GONE);
		}
		init(datePicker, timePicker);
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(this);

		tv_title.setText(initDateTime);
		tv_save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ad.dismiss();
				if (TimeFormatUtils.compareTime(dateTime)) {
					inputDate.setText(dateTime);
					if(listener!=null){
						listener.timeConfirmListener(dateTime);
					}
				}
			}
		});
		tv_cancle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ad.dismiss();
			}
		});
		tv_delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if(!inputDate.getText().toString().trim().equals("")){
//					ToastUtil.show(activity,"已删除", Toast.LENGTH_SHORT);
					inputDate.setText(null);
					listener.deleteLister();
				}
				ad.dismiss();
			}
		});

		ad = new Dialog(activity);
		ad.requestWindowFeature(Window.FEATURE_NO_TITLE);
		ad.getWindow().setContentView(dateTimeLayout);
		//3.设置指定的宽高,如果不设置的话，弹出的对话框可能不会显示全整个布局，当然在布局中写死宽高也可以
		WindowManager.LayoutParams lp     = new WindowManager.LayoutParams();
		Window                     window = ad.getWindow();
		lp.copyFrom(window.getAttributes());
		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		//注意要在Dialog show之后，再将宽高属性设置进去，才有效果
		ad.show();
		window.setAttributes(lp);
		onDateChanged(null, 0, 0, 0);
		return ad;
	}

//	public AlertDialog dateTimeLinePicKDialogListener(final TextView inputDate, @Nullable final MyListener listener) {
//		LinearLayout dateTimeLayout = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.common_datetime, null);
//		String et_content = inputDate.getText().toString();
//		datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
//		timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
//		setDatePickerDividerColor(datePicker);
//		setTimePickerDividerColor(timePicker);
//		resizePikcer(datePicker);// 调整datepicker大小
//		resizePikcer(timePicker);// 调整timepicker大小
//		if (time_format.equals("yyyy-MM-dd")) {
//			timePicker.setVisibility(View.GONE);
//		}
//		init(datePicker, timePicker);
//		timePicker.setIs24HourView(true);
//		timePicker.setOnTimeChangedListener(this);
//
//		ad = new AlertDialog.Builder(activity).setTitle(initDateTime)
//				.setView(dateTimeLayout)
//				.setPositiveButton("保存", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int whichButton) {
//						if (TimeFormatUtils.compareTime(dateTime)) {
//							inputDate.setText(dateTime);
//							if(listener!=null){
//								listener.timeConfirmListener(dateTime);
//							}
//						} else {
////					Toast.makeText(activity, "您选择的时间为 将来时间", Toast.LENGTH_LONG).show();
//						}
//
//					}
//				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int whichButton) {
//						// inputDate.setText("");
//					}
//				}).setNeutralButton("删除", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialogInterface, int i) {
//						ToastUtil.show(activity,"已删除", Toast.LENGTH_SHORT);
//						inputDate.setText(null);
//						listener.deleteLister();
//					}
//				}).show();
//
//		onDateChanged(null, 0, 0, 0);
//		return ad;
//	}
	/**
	 * 溶栓后记录TCD
	 */
	public Dialog dateTimeJiangeDialogListener(final TextView inputDate, final SaveListerer listener) {
		LinearLayout dateTimeLayout = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.common_datetime, null);
		String et_content = inputDate.getText().toString();
		datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
		timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
		resizePikcer(datePicker);// 调整datepicker大小
		resizePikcer(timePicker);// 调整timepicker大小
		if (time_format.equals("yyyy-MM-dd")) {
			timePicker.setVisibility(View.GONE);
		}
		init(datePicker, timePicker);
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(this);

		ad = new AlertDialog.Builder(activity).setTitle(initDateTime).setView(dateTimeLayout).setPositiveButton("修改全部", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				if (TimeFormatUtils.compareTime(dateTime)) {
					inputDate.setText(dateTime);
					if(listener!=null){
						listener.saveAllListener(dateTime);
					}
				} else {
//					Toast.makeText(activity, "您选择的时间为 将来时间", Toast.LENGTH_LONG).show();
				}
			}
		}).setNegativeButton("保存本次", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				if (TimeFormatUtils.compareTime(dateTime)) {
					inputDate.setText(dateTime);
					if(listener!=null){
						listener.saveOneListener(dateTime);
					}
				} else {
//					Toast.makeText(activity, "您选择的时间为 将来时间", Toast.LENGTH_LONG).show();
				}
			}
		}).show();

		onDateChanged(null, 0, 0, 0);
		return ad;
	}

	public Dialog dateTimePicKDialogTimeListener(final TextView inputDate, final ConfirmTimeListerer listener) {
		LinearLayout dateTimeLayout = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.common_datetime, null);
		String et_content = inputDate.getText().toString();
		datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
		timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
		resizePikcer(datePicker);// 调整datepicker大小
		resizePikcer(timePicker);// 调整timepicker大小
		if (time_format.equals("yyyy-MM-dd")) {
			timePicker.setVisibility(View.GONE);
		}
		init(datePicker, timePicker);
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(this);

		ad = new AlertDialog.Builder(activity).setTitle(initDateTime).setView(dateTimeLayout).setPositiveButton("设置", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				if (TimeFormatUtils.compareTime(dateTime)) {
					if(listener.timeConfirmListener(dateTime)){
						inputDate.setText(dateTime);
					}
				} else {
//					Toast.makeText(activity, "您选择的时间为 将来时间", Toast.LENGTH_LONG).show();
				}
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int whichButton) {
				// inputDate.setText("");
			}
		}).setNeutralButton("删除", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				inputDate.setText(null);
			}
		}).show();

		onDateChanged(null, 0, 0, 0);
		return ad;
	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		onDateChanged(null, 0, 0, 0);
	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		// 获得日历实例
		Calendar calendar = Calendar.getInstance();

		calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
		SimpleDateFormat sdf = new SimpleDateFormat(time_format);
		dateTime = sdf.format(calendar.getTime());
//		ad.setTitle(dateTime);    //标题
	}

	/**
	 * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
	 * 
	 * @param initDateTime
	 *            初始日期时间值 字符串型
	 * @return Calendar
	 */
	private Calendar getCalendarByInintData(String initDateTime) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(time_format);
		Date datetime = null;
		try {
			datetime = sdf.parse(initDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(datetime);

		return calendar;
	}

	/**
	 * 截取子串
	 * 
	 * @param srcStr
	 *            源串
	 * @param pattern
	 *            匹配模式
	 * @param indexOrLast
	 * @param frontOrBack
	 * @return
	 */
	public static String spliteString(String srcStr, String pattern, String indexOrLast, String frontOrBack) {
		String result = "";
		int loc = -1;
		if (indexOrLast.equalsIgnoreCase("index")) {
			loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
		} else {
			loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
		}
		if (frontOrBack.equalsIgnoreCase("front")) {
			if (loc != -1)
				result = srcStr.substring(0, loc); // 截取子串
		} else {
			if (loc != -1)
				result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
		}
		return result;
	}

	/**
	 * 调整FrameLayout大小
	 * 
	 * @param tp
	 */
	private void resizePikcer(FrameLayout tp) {
		List<NumberPicker> npList = findNumberPicker(tp);
		for (NumberPicker np : npList) {
			resizeNumberPicker(np);
		}
	}

	/*
	 * 调整numberpicker大小
	 */
	private void resizeNumberPicker(NumberPicker np) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dip2px(activity, 45), LayoutParams.WRAP_CONTENT);
		params.setMargins(dip2px(activity, 5), 0, dip2px(activity, 5), 0);
		np.setLayoutParams(params);

	}

	/**
	 * 得到viewGroup里面的numberpicker组件
	 * 
	 * @param viewGroup
	 * @return
	 */
	private List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {
		List<NumberPicker> npList = new ArrayList<NumberPicker>();
		View child = null;
		if (null != viewGroup) {
			for (int i = 0; i < viewGroup.getChildCount(); i++) {
				child = viewGroup.getChildAt(i);
				if (child instanceof NumberPicker) {
					npList.add((NumberPicker) child);
				} else if (child instanceof LinearLayout) {
					List<NumberPicker> result = findNumberPicker((ViewGroup) child);
					if (result.size() > 0) {
						return result;
					}
				}
			}
		}
		return npList;
	}

	/**
	 * 
	 * @Title: dip2px @Description: TODO(dp转px) @param @param context @param @param
	 *         dpValue @param @return 设定文件 @return int 返回类型 @throws
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}


	/**
	 *
	 * 设置时间选择器的分割线颜色（年月日）
	 * @param datePicker
	 */
	private void setDatePickerDividerColor(DatePicker datePicker){
		// Divider changing:

		// 获取 mSpinners
		LinearLayout llFirst       = (LinearLayout) datePicker.getChildAt(0);

		// 获取 NumberPicker
		LinearLayout mSpinners      = (LinearLayout) llFirst.getChildAt(0);
		for (int i = 0; i < mSpinners.getChildCount(); i++) {
			NumberPicker picker = (NumberPicker) mSpinners.getChildAt(i);

			Field[] pickerFields = NumberPicker.class.getDeclaredFields();
			for (Field pf : pickerFields) {
				if (pf.getName().equals("mSelectionDivider")) {
					pf.setAccessible(true);
					try {
						pf.set(picker, new ColorDrawable(CzjjMap_Application.getInstance().getResources().getColor(R.color.main_color)));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (Resources.NotFoundException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}

	/**
	 *
	 * 设置时间选择器的分割线颜色(十、分)
	 * @param
	 */
	private void setTimePickerDividerColor(TimePicker timePicker){
		Resources systemResources = Resources.getSystem();
		int hourNumberPickerId = systemResources.getIdentifier("hour", "id", "android");
		int minuteNumberPickerId = systemResources.getIdentifier("minute", "id", "android");
		NumberPicker hourNumberPicker = (NumberPicker) timePicker.findViewById(hourNumberPickerId);
		NumberPicker minuteNumberPicker = (NumberPicker) timePicker.findViewById(minuteNumberPickerId);
		setNumberPickerDivider(hourNumberPicker);
		setNumberPickerDivider(minuteNumberPicker);
	}
	private void setNumberPickerDivider(NumberPicker numberPicker) {
		final int count = numberPicker.getChildCount();
		for(int i = 0; i < count; i++){
			try{
				Field dividerField = numberPicker.getClass().getDeclaredField("mSelectionDivider");
				dividerField.setAccessible(true);
				ColorDrawable colorDrawable = new ColorDrawable(
						ContextCompat.getColor(CzjjMap_Application.getInstance(), R.color.main_color));
				dividerField.set(numberPicker,colorDrawable);
				numberPicker.invalidate();
			}
			catch(NoSuchFieldException | IllegalAccessException | IllegalArgumentException e){
				Log.w("setNumberPickerTxtClr", e);
			}
		}
	}
}
