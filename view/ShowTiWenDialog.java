package com.jhjz.emr.lstd_public.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.jhjz.emr.lstd_public.R;
import com.jhjz.emr.lstd_public.utils.DateTimePickDialogUtil;
import com.jhjz.emr.lstd_public.utils.TimeFormatUtils;

import java.text.SimpleDateFormat;

public class ShowTiWenDialog {
	// private AlertDialog alertDialog;

	public interface ShowLvTiWenListener {
		void addOne(String time, String ssy, String szy);

		void addAll(String time, String ssy, String szy);

	}

	public ShowTiWenDialog(Activity adtivity, String time, String ssy, String szy, int AddOrRepair, ShowLvTiWenListener showLvListener, boolean cancelAble) {
		showInfo(adtivity, time, ssy, szy, AddOrRepair, showLvListener, cancelAble);
	}

	private void showInfo(final Activity adtivity, String time, String ssy, String szy, int AddOrRepair, final ShowLvTiWenListener showLvListener, boolean cancelAble) {
		AlertDialog.Builder builder = new AlertDialog.Builder(adtivity);
		View view = View.inflate(adtivity, R.layout.dialog_rongshuanhou_tiwen, null);
		builder.setView(view);
		builder.setCancelable(cancelAble);
		final AlertDialog dialog = builder.create();
		dialog.show();

		final MyEditText edt_time_tiwen = (MyEditText) view.findViewById(R.id.edt_time_tiwen);
		final EditText edt_ssy_tiwen = (EditText) view.findViewById(R.id.edt_ssy_tiwen);
		final EditText edt_szy_tiwen = (EditText) view.findViewById(R.id.edt_szy_tiwen);

		if (AddOrRepair == 1) {
			edt_time_tiwen.setText(time != null ? time.replace("T", " ") : "");
			edt_ssy_tiwen.setText(ssy != null ? ssy : "");
			edt_szy_tiwen.setText(szy != null ? szy : "");
		}
		edt_time_tiwen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String initEndDateTime = "";
				if (!"".equals(edt_time_tiwen.getText().toString()) && !edt_time_tiwen.getText().toString().equals(":00")) {
					initEndDateTime = TimeFormatUtils.StringTimeToFormatTime2String(edt_time_tiwen.getText().toString().replace("T", " "));
				} else {
					initEndDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date());

				}
				edt_time_tiwen.setText(initEndDateTime);
				if (!edt_time_tiwen.getText().toString().equals(":00") && !edt_time_tiwen.getText().toString().equals("") && edt_time_tiwen.getText().toString() != null) {
					DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(adtivity, edt_time_tiwen.getText().toString().replace("/", "-"), "yyyy-MM-dd HH:mm");
					dateTimePicKDialog.dateTimePicKDialogListener(edt_time_tiwen,null);
				}
			}
		});
		TextView txt_one_save = (TextView) view.findViewById(R.id.txt_one_save);
		TextView txt_all_save = (TextView) view.findViewById(R.id.txt_all_save);
		txt_one_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				showLvListener.addOne(edt_time_tiwen.getText().toString(), edt_ssy_tiwen.getText().toString(), edt_szy_tiwen.getText().toString());
			}
		});
		txt_all_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				showLvListener.addAll(edt_time_tiwen.getText().toString(), edt_ssy_tiwen.getText().toString(), edt_szy_tiwen.getText().toString());
			}
		});

	};
}
