package com.jhjz.emr.lstd_public.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

public class ShowTipDialog {

	private static ShowTipDialog single = null;
	private static AlertDialog.Builder builder;// 先得到构造器
	private static Dialog dialog;

	public interface TipListener {
		void onFonfirm();

		void onCancel();
	}

	//
	public static ShowTipDialog getInstance(String title, String message, Activity adtivity, boolean clickSideAble, final TipListener tipListener) {
		if (single == null) {
			single = new ShowTipDialog(title, message, adtivity, clickSideAble, tipListener);
		} else {
			showInfo(title, message, builder, adtivity, clickSideAble, tipListener);
		}
		return single;
	}

	private ShowTipDialog(String title, String message, Activity adtivity, boolean clickSideAble, final TipListener tipListener) {
		showInfo(title, message, builder, adtivity, clickSideAble, tipListener);
	}

	private static void showInfo(String title, String message, AlertDialog.Builder builder, Activity activity, boolean clickSideAble, final TipListener tipListener) {
		try {
			if (dialog != null) {
				dialog.dismiss();
			}
			builder = new AlertDialog.Builder(activity);
			if (title != null && !"".equals(title)) {
				builder.setTitle(title); // 设置标题
			}
			builder.setMessage(message); // 设置内容
			// builder.setIcon(R.drawable.ic_launcher_xin);// 设置图标，图片id即可
			builder.setPositiveButton("是", new DialogInterface.OnClickListener() { // 设置确定按钮
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (tipListener != null) {
								tipListener.onFonfirm();
							}
							dialog.dismiss(); // 关闭dialog
						}
					});
			builder.setNegativeButton("否", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss(); // 关闭dialog
					if (tipListener != null) {
						tipListener.onCancel();
					}
				}
			});
			builder.create();
			dialog = builder.show();
			if (clickSideAble) {
				dialog.setCancelable(clickSideAble);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	};
}
