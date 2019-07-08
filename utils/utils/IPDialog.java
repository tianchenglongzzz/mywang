package com.jhjz.emr.lstd_public.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jhjz.emr.lstd_public.CzjjMap_Application;
import com.jhjz.emr.lstd_public.R;
import com.jhjz.emr.lstd_public.config.UrlConfig;

import java.lang.reflect.Field;


public class IPDialog {
	private static IPDialog single = null;
	private static AlertDialog.Builder builder;// 先得到构造器
	private static Dialog dialog;

	//
	public static IPDialog getInstance(AppCompatActivity activity) {
		if (single == null) {
			single = new IPDialog(activity);
		} else {
			showInfo(activity);
		}
		return single;
	}

	private IPDialog(AppCompatActivity activity) {
		showInfo(activity);
	}

	private static void showInfo(final AppCompatActivity activity) {
		try {
			if (dialog != null) {
				dialog.dismiss();
			}
			final AlertDialog.Builder builder = new AlertDialog.Builder(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);// THEME_DEVICE_DEFAULT_LIGHT
			View view = View.inflate(activity, R.layout.dialog_ip, null);
			builder.setView(view);
			builder.setCancelable(true);
			final AlertDialog alertDialog = builder.create();
			alertDialog.show();
			final EditText edt_ip = (EditText) view.findViewById(R.id.edt_ip);
			String IP = CzjjMap_Application.IP;
			edt_ip.setText(IP);
			//mqttIP地址
			final EditText edt_mqttip = (EditText) view.findViewById(R.id.edt_mqtt_ip);
			String MQTTIP = CzjjMap_Application.MQTTIP;
			edt_mqttip.setText(MQTTIP);

			final String oldValue=edt_ip.getText().toString();
			TextView txt_ip_confirm = (TextView) view.findViewById(R.id.txt_ip_confirm);
			txt_ip_confirm.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					SpUtil.saveIP(edt_ip.getText().toString());
					CzjjMap_Application.IP=edt_ip.getText().toString();
					//配置MQTTIp地址
					if(edt_mqttip.getText().toString().trim().indexOf(":")==-1) {
					ToastUtil.show(CzjjMap_Application.getInstance(),"请输入正确地址",Toast.LENGTH_SHORT);
						return;
					}
					SpUtil.saveMQTTIP(edt_mqttip.getText().toString());
					CzjjMap_Application.MQTTIP=edt_mqttip.getText().toString();

					UrlConfig urlConfig=new UrlConfig();
					Field field = null;
					Field field1 = null;
					Field field2= null;
					try {
						field = urlConfig.getClass().getDeclaredField("HOSTDOMAIN");
						field1 = urlConfig.getClass().getDeclaredField("BASE_API");
//						field2 = urlConfig.getClass().getDeclaredField("LOGIN");
						field.setAccessible(true);
						field1.setAccessible(true);
//						field2.setAccessible(true);
						field.set(urlConfig, edt_ip.getText().toString());
						field1.set(urlConfig, edt_ip.getText().toString()+ "/AppApiBdNew3/");
						changeStringValue(urlConfig,oldValue, edt_ip.getText().toString());

//						field2.set(urlConfig, "http://" +  edt_ip.getText().toString()+ "/AppApiBdNew3/"+
// "checkLogin_1");
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}

//					UrlConfig.HOSTDOMAIN=edt_ip.getText().toString();
					alertDialog.dismiss();
				}
			});

			TextView txt_ip_cancel = (TextView) view.findViewById(R.id.txt_ip_cancel);
			txt_ip_cancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					alertDialog.dismiss();

				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	//使用反射技术动态修改String类型的成员变量的值.
	private static void changeStringValue(Object obj,String oldValues,String newValues) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getType() == String.class) {
				field.setAccessible(true);
				String oldValue = (String) field.get(obj);
				String newValue = oldValue.replace(oldValues, newValues);
				field.set(obj, newValue);
			}
		}
	}

}
