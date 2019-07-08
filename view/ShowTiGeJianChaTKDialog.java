package com.jhjz.emr.lstd_public.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jhjz.emr.lstd_public.R;
import com.jhjz.emr.lstd_public.bean.TigeBeen;

import java.util.List;
import java.util.Map;

public class ShowTiGeJianChaTKDialog {
	// private AlertDialog alertDialog;
	private String fanshe_left, fanshe_right;

	public interface ShowLvListener {
		void add1(TigeBeen tigeBeen);

	}

	public ShowTiGeJianChaTKDialog(
			List<Map<String, String>> list,
			Activity adtivity,
			ShowTiGeJianChaTKDialog.ShowLvListener showLvListener,
			boolean cancelAble,TigeBeen tigeBeen) {
		showInfo(list, adtivity, showLvListener, cancelAble,tigeBeen);
	}

	private void showInfo(List<Map<String, String>> list, Activity adtivity,
						  final ShowLvListener showLvListener, boolean cancelAble, final TigeBeen tigeBeen) {
		AlertDialog.Builder builder = new AlertDialog.Builder(adtivity);
		View view = View.inflate(adtivity, R.layout.dialog_tige_jiancha_tk,
				null);
		builder.setView(view);
		builder.setCancelable(cancelAble);
		final AlertDialog dialog = builder.create();
		dialog.show();
		View vieww = view.findViewById(R.id.dialog_tige_view);
		TextView tianjia = (TextView) view
				.findViewById(R.id.dialog_tige_tianjia);
		final EditText tongkong_left = (EditText) view
				.findViewById(R.id.tongkong_left);
		final EditText tongkong_right = (EditText) view
				.findViewById(R.id.tongkong_right);

		final RadioGroup rg_tkleft = (RadioGroup) view
				.findViewById(R.id.rg_tkleft);
		final RadioGroup rg_tkright = (RadioGroup) view
				.findViewById(R.id.rg_tkright);
		final List<Map<String, String>> listt = list;
		if (tigeBeen!=null){
			if (tigeBeen.getTZZhi()!=null&&!TextUtils.isEmpty(tigeBeen.getTZZhi())){
				String[] split = tigeBeen.getTZZhi().split(",");
				if (split[0]!=null&&!TextUtils.isEmpty(split[0])){
					tongkong_left.setText(split[0]);
				}
				if (split[1]!=null&&!TextUtils.isEmpty(split[1])){
					for (int i = 0; i < rg_tkleft.getChildCount(); i++) {
						RadioButton radioButton = (RadioButton) rg_tkleft.getChildAt(i);
						if (radioButton.getText().toString().equals(split[1])){
							radioButton.setChecked(true);
						}
					}
				}
			}

			if (tigeBeen.getTZZStr2()!=null&&!TextUtils.isEmpty(tigeBeen.getTZZStr2())){
				String[] split = tigeBeen.getTZZStr2().split(",");
				if (split[0]!=null&&!TextUtils.isEmpty(split[0])){
					tongkong_right.setText(split[0]);
				}
				if (split[1]!=null&&!TextUtils.isEmpty(split[1])){
					for (int i = 0; i < rg_tkright.getChildCount(); i++) {
						RadioButton radioButton = (RadioButton) rg_tkright.getChildAt(i);
						if (radioButton.getText().toString().equals(split[1])){
							radioButton.setChecked(true);
						}
					}
				}
			}
		}
		tianjia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String zj_left = tongkong_left.getText().toString();
				String zj_right = tongkong_right.getText().toString();
				for (int i = 0; i < rg_tkleft.getChildCount(); i++) {
					RadioButton radioButton = (RadioButton) rg_tkleft
							.getChildAt(i);
					if (radioButton.isChecked()) {
						fanshe_left = radioButton.getText().toString();
					}
				}
				for (int i = 0; i < rg_tkright.getChildCount(); i++) {
					RadioButton radioButton = (RadioButton) rg_tkright
							.getChildAt(i);
					if (radioButton.isChecked()) {
						fanshe_right = radioButton.getText().toString();
					}
				}

				if (fanshe_left == null || TextUtils.isEmpty(fanshe_left)) {
					Toast.makeText(v.getContext(), "请选择左侧光反射", Toast.LENGTH_SHORT).show();
					return;
				}else{
				}
				if (fanshe_right == null || TextUtils.isEmpty(fanshe_right)) {
					Toast.makeText(v.getContext(), "请选择右侧光反射", Toast.LENGTH_SHORT).show();
					return;
				}
				if (zj_left == null || TextUtils.isEmpty(zj_left)) {
					Toast.makeText(v.getContext(), "请填写左侧瞳孔直径", Toast.LENGTH_SHORT).show();
					return;
				}
				if (zj_right == null || TextUtils.isEmpty(zj_right)) {
					Toast.makeText(v.getContext(), "请填写右侧瞳孔直径", Toast.LENGTH_SHORT).show();
					return;
				}

				tigeBeen.setTZZhi(zj_left+","+fanshe_left);
				tigeBeen.setTZZStr2(zj_right+","+fanshe_right);
				if (listt.size() == 1) {
					showLvListener.add1(tigeBeen);
					
				}
				dialog.dismiss();
			}
		});

	};
}
