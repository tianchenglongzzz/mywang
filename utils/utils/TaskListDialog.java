package com.jhjz.emr.lstd_public.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jhjz.emr.lstd_public.CzjjMap_Application;
import com.jhjz.emr.lstd_public.R;
import com.jhjz.emr.lstd_public.base.BaseActivity;


public class TaskListDialog {
	private static TaskListDialog single = null;
	private static AlertDialog.Builder builder;// 先得到构造器
	private static Dialog dialog;

	//
	public static TaskListDialog getInstance(BaseActivity activity) {
		if (single == null) {
			single = new TaskListDialog(activity);
		} else {
			showInfo(activity);
		}
		return single;
	}

	private TaskListDialog(BaseActivity activity) {
		showInfo(activity);
	}

	private static void showInfo(final BaseActivity activity) {
		try {
			if (dialog != null) {
				dialog.dismiss();
			}
			final AlertDialog.Builder builder = new AlertDialog.Builder(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);// THEME_DEVICE_DEFAULT_LIGHT
			View view = View.inflate(activity, R.layout.dialog_tasklist, null);
			builder.setView(view);
			builder.setCancelable(true);
			final AlertDialog alertDialog = builder.create();

			alertDialog.show();

			//确定按钮
			TextView txt_ip_confirm = (TextView) view.findViewById(R.id.btn_sure);
			ImageView down_arrow = (ImageView) view.findViewById(R.id.down_arrow);
			down_arrow.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					Toast.makeText(CzjjMap_Application.getInstance(), "1", Toast.LENGTH_SHORT).show();
				}
			});
			txt_ip_confirm.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					 Toast.makeText(CzjjMap_Application.getInstance(), "已完成", Toast.LENGTH_SHORT).show();
				}
			});
			//取消
			TextView txt_ip_cancel = (TextView) view.findViewById(R.id.btn_quxiao);
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
}
