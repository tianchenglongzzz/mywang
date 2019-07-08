package com.jhjz.emr.lstd_public.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jhjz.emr.lstd_public.R;
import com.jhjz.emr.lstd_public.bean.TigeBeen;
import com.jhjz.emr.lstd_public.utils.ToastUtil;
import com.jhjz.emr.lstd_public.utils.Utilss;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowTiGeJianChaDialog {
	//private AlertDialog alertDialog;

	public interface ShowLvListener {
		void add1(String data1, String data3);
		void add2(String data1, String data2, String data3);
		void add3(EditText edittext);
	}

	public interface ShowLvListener1 {
		void add(TigeBeen tigeBeen);
	}
	/**
	 * 
	 * @param zhi  三个edittext上填的的内容
	 */
	public ShowTiGeJianChaDialog(List<Map<String, String>> list, Activity adtivity, ShowLvListener showLvListener, boolean cancelAble,
                                 String zhi) {
		Map<String,String> map=new HashMap<>();
		if(zhi.equals("")){
			map.put("zhong1", "");
			map.put("zhong2", "");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
			String shijian = df.format(new Date());// new Date()为获取当前系统时间
			map.put("Shijian", shijian);
		}else{
			String[] temp=zhi.split("/");
			if(temp.length==2){
				map.put("zhong1", temp[0]);
				map.put("zhong2", temp[1]);
			}else{
				map.put("zhong1", zhi);
			}

//			map.put("Shijian", temp[0]);
		}
		showInfo(list,adtivity, showLvListener, cancelAble,map);
	}
	/**
	 *
	 * @param
	 */
	public ShowTiGeJianChaDialog(List<Map<String, String>> list, Activity adtivity, ShowLvListener1 showLvListener, boolean cancelAble,
								 TigeBeen tigeBeen) {
		boolean isNew = false;
		Map<String,String> map=new HashMap<>();
		if(tigeBeen==null){
			isNew=true;
			map.put("zhong1", "");
			map.put("zhong2", "");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
			String shijian = df.format(new Date());// new Date()为获取当前系统时间
			map.put("Shijian", shijian);
		}else{
			String ttZhi=tigeBeen.getTZZhi();
			if(ttZhi!=null&&!ttZhi.isEmpty()){	
				if(ttZhi.contains("/")){
				String[] split = ttZhi.split("/");
				map.put("zhong1",split[0]);
				map.put("zhong2",split[1]);
				}else{
					map.put("zhong1",tigeBeen.getTZZhi());
					map.put("zhong2",tigeBeen.getTZZhi());
				}
				isNew=false;
			}else{
                isNew=true;
				map.put("zhong1",tigeBeen.getTZZhi());
				map.put("zhong2",tigeBeen.getTZZhi());
			}

			map.put("Shijian", tigeBeen.getTZZStr1());
		}
		showInfos(list,adtivity, showLvListener, cancelAble,map,tigeBeen,isNew);
	}
	private void showInfos(List<Map<String, String>> list, final Activity adtivity, final ShowLvListener1 showLvListener, boolean cancelAble,
						   Map<String,String> zhi, final TigeBeen tigeBeen,boolean isNew) {
		AlertDialog.Builder builder=new AlertDialog.Builder(adtivity);
		View view = View.inflate(adtivity, R.layout.dialog_tige_jiancha, null);
		builder.setView(view);
		builder.setCancelable(cancelAble);
		final AlertDialog dialog = builder.create();
		dialog.show();
		LinearLayout layout1=(LinearLayout)view.findViewById(R.id.dialog_tige_item1);
		LinearLayout layout2=(LinearLayout)view.findViewById(R.id.dialog_tige_item2);
		TextView item1Tv1=(TextView)view.findViewById(R.id.dialog_tige_item1_tv1);
		TextView item1Tv2=(TextView)view.findViewById(R.id.dialog_tige_item1_tv2);
		TextView item2Tv1=(TextView)view.findViewById(R.id.dialog_tige_item2_tv1);
		TextView item2Tv2=(TextView)view.findViewById(R.id.dialog_tige_item2_tv2);
		final EditText et1=(EditText)view.findViewById(R.id.dialog_tige_item1_et);
		final EditText et2=(EditText)view.findViewById(R.id.dialog_tige_item2_et);
		final EditText et3=(EditText)view.findViewById(R.id.dialog_tige_item3_et);

		View vieww=view.findViewById(R.id.dialog_tige_view);
		final TextView tianjia=(TextView)view.findViewById(R.id.dialog_tige_tianjia);
		TextView dialog_tige_cancel=(TextView)view.findViewById(R.id.dialog_tige_cancel);
		if(!isNew){
			tianjia.setText("修改");
		}
		if(list.size()==1){
			layout2.setVisibility(View.GONE);
//			vieww.setVisibility(View.GONE);
		}else{
			item2Tv1.setText(list.get(1).get("zuo"));
			item2Tv2.setText(list.get(1).get("you"));
		}
		item1Tv1.setText(list.get(0).get("zuo"));
		item1Tv2.setText(list.get(0).get("you"));
		et1.setText(zhi.get("zhong1"));
		et2.setText(zhi.get("zhong2"));
		if (zhi.get("Shijian")!=null&&!TextUtils.isEmpty(zhi.get("Shijian"))){
			et3.setText(zhi.get("Shijian"));
		}else{
			et3.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		}
		final List<Map<String, String>> listt=list;
		tianjia.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(listt.size()==1){
					if (et1.getText().toString()==null||TextUtils.isEmpty(et1.getText().toString())){
						ToastUtil.show(adtivity,"请输入相关值", Toast.LENGTH_SHORT);
						return;
					}
					if (tigeBeen.getTZCode().equals("血压")){
						if (Double.parseDouble(et1.getText().toString()) - 300 > 0 || Double.parseDouble(et1.getText().toString()) - 300 > 0) {
							Utilss.showToastShort(adtivity, "血压超出范围");
							return;
						}
					}else if (tigeBeen.getTZCode().equals("体温")){
						if (Double.parseDouble(et1.getText().toString()) - 300 > 0 || Double.parseDouble(et1.getText().toString()) - 300 > 0) {
							Utilss.showToastShort(adtivity, "血压超出范围");
							return;
						}
					}
					tigeBeen.setTZZStr1(et3.getText().toString());
					tigeBeen.setTZZhi(et1.getText().toString());

//					showLvListener.add1(et1.getText().toString(),et3.getText().toString());
				}else{
					if (et1.getText().toString()==null||TextUtils.isEmpty(et1.getText().toString())){
						ToastUtil.show(adtivity,"请输入相关值", Toast.LENGTH_SHORT);
						return;
					}
					if (et2.getText().toString()==null||TextUtils.isEmpty(et2.getText().toString())){
						ToastUtil.show(adtivity,"请输入相关值", Toast.LENGTH_SHORT);
						return;
					}
					tigeBeen.setTZZStr1(et3.getText().toString());
					tigeBeen.setTZZhi(et1.getText().toString()+"/"+et2.getText().toString());
//					showLvListener.add2(et1.getText().toString(),et2.getText().toString(),et3.getText().toString());
				}
				showLvListener.add(tigeBeen);
				dialog.dismiss();
			}
		});
		dialog_tige_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
//		et3.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(adtivity, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()), "yyyy-MM-dd HH:mm");
//				dateTimePicKDialog.dateTimePicKDialogListener(et3, new DateTimePickDialogUtil.ConfirmListerer() {
//
//					@Override
//					public void timeConfirmListener() {
//
//					}
//				});
//			}
//		});
	}
	private void showInfo(List<Map<String, String>> list, final Activity adtivity, final ShowLvListener showLvListener, boolean cancelAble,
						  Map<String,String> zhi) {
		AlertDialog.Builder builder=new AlertDialog.Builder(adtivity);
		View view = View.inflate(adtivity, R.layout.dialog_tige_jiancha, null);
		builder.setView(view);
		builder.setCancelable(cancelAble);
		final AlertDialog dialog = builder.create();
        dialog.show();
		LinearLayout layout1=(LinearLayout)view.findViewById(R.id.dialog_tige_item1);
		LinearLayout layout2=(LinearLayout)view.findViewById(R.id.dialog_tige_item2);
		TextView item1Tv1=(TextView)view.findViewById(R.id.dialog_tige_item1_tv1);
		TextView item1Tv2=(TextView)view.findViewById(R.id.dialog_tige_item1_tv2);
		TextView item2Tv1=(TextView)view.findViewById(R.id.dialog_tige_item2_tv1);
		TextView item2Tv2=(TextView)view.findViewById(R.id.dialog_tige_item2_tv2);
		final EditText et1=(EditText)view.findViewById(R.id.dialog_tige_item1_et);
		final EditText et2=(EditText)view.findViewById(R.id.dialog_tige_item2_et);
		final EditText et3=(EditText)view.findViewById(R.id.dialog_tige_item3_et);
		openSoftInputFromWindow(adtivity,et1);

		View vieww=view.findViewById(R.id.dialog_tige_view);
		TextView tianjia=(TextView)view.findViewById(R.id.dialog_tige_tianjia);
		TextView dialog_tige_cancel=(TextView)view.findViewById(R.id.dialog_tige_cancel);
		if(list.size()==1){
			layout2.setVisibility(View.GONE);
//			vieww.setVisibility(View.GONE);
		}else{
			item2Tv1.setText(list.get(1).get("zuo"));
			item2Tv2.setText(list.get(1).get("you"));
		}
		item1Tv1.setText(list.get(0).get("zuo"));
		item1Tv2.setText(list.get(0).get("you"));
		et1.setText(zhi.get("zhong1"));
		if(zhi.get("zhong1")!=null&&zhi.get("zhong1").length()>0){
			et1.setSelection(zhi.get("zhong1").length());
			tianjia.setText("修改");
		}else{
			tianjia.setText("添加");
		}

		et2.setText(zhi.get("zhong2"));
		if(zhi.get("zhong2")!=null){
			et2.setSelection(zhi.get("zhong2").length());
		}
		if (zhi.get("Shijian")!=null&&!TextUtils.isEmpty(zhi.get("Shijian"))){
			et3.setText(zhi.get("Shijian"));
			if(zhi.get("Shijian")!=null){
				et3.setSelection(zhi.get("Shijian").length());
			}
		}else{
			et3.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
			et3.setSelection(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()).length());
		}
		final List<Map<String, String>> listt=list;
		tianjia.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(listt.size()==1){
					if (et1.getText().toString()==null||TextUtils.isEmpty(et1.getText().toString())){
						ToastUtil.show(adtivity,"请输入相关值", Toast.LENGTH_SHORT);
						return;
					}
					showLvListener.add1(et1.getText().toString(),et3.getText().toString());
				}else{
					if (et1.getText().toString()==null||TextUtils.isEmpty(et1.getText().toString())){
						ToastUtil.show(adtivity,"请输入相关值", Toast.LENGTH_SHORT);
						return;
					}
					if (et2.getText().toString()==null||TextUtils.isEmpty(et2.getText().toString())){
						ToastUtil.show(adtivity,"请输入相关值", Toast.LENGTH_SHORT);
						return;
					}
					showLvListener.add2(et1.getText().toString(),et2.getText().toString(),et3.getText().toString());
				}
				closeSoftInputFromWindow(adtivity,et1);
				dialog.dismiss();
			}
		});

		dialog_tige_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				closeSoftInputFromWindow(adtivity,et1);
				dialog.dismiss();
			}
		});
		et3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				showLvListener.add3(et3);
			}
		});
	}
	public void openSoftInputFromWindow(Context context,EditText editText){
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
	}
	public void closeSoftInputFromWindow(Context context,EditText editText){
		InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(),0);
	}
}
