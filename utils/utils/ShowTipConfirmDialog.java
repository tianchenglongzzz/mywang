package com.jhjz.emr.lstd_public.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

public class ShowTipConfirmDialog {

	private static ShowTipConfirmDialog single = null;
	private static AlertDialog.Builder builder;// 先得到构造器
	private static Dialog dialog;

	public interface TipConfirmListener {
		void onFonfirm();
	}

	//
	public static ShowTipConfirmDialog getInstance(String title, String message, Activity adtivity, boolean clickSideAble, final TipConfirmListener tipListener) {
		if (single == null) {
			single = new ShowTipConfirmDialog(title, message, adtivity, clickSideAble, tipListener);
		} else {
			showInfo(title, message, builder, adtivity, clickSideAble, tipListener);
		}
		return single;
	}

	private ShowTipConfirmDialog(String title, String message, Activity adtivity, boolean clickSideAble, final TipConfirmListener tipListener) {
		showInfo(title, message, builder, adtivity, clickSideAble, tipListener);
	}

	private static void showInfo(String title, String message, AlertDialog.Builder builder, Activity activity, boolean clickSideAble, final TipConfirmListener tipListener) {
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
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() { // 设置确定按钮
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (tipListener != null) {
								tipListener.onFonfirm();
							}
							dialog.dismiss(); // 关闭dialog
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
