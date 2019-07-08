package com.jhjz.emr.lstd_public.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jhjz.emr.lstd_public.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowTiGeJianChaDialogUpdate {
	//private AlertDialog alertDialog;

	public interface ShowLvListener {
		void add1(String data1, String data3);
		void add2(String data1, String data2, String data3);
		void add3(EditText edittext);
	}
	/**
	 *
	 * @param zhi  三个edittext上填的的内容
	 */
	public ShowTiGeJianChaDialogUpdate(List<Map<String, String>> list, Activity adtivity, ShowLvListener showLvListener, boolean cancelAble,
                                       String zhi) {
		Map<String,String> map=new HashMap<>();
		if(zhi.equals("")){
			map.put("zhong1", "");
			map.put("zhong2", "");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
			String shijian = df.format(new Date());// new Date()为获取当前系统时间
			map.put("Shijian", shijian);
		}else{
			String[] temp=zhi.split("  ");
			map.put("zhong1", temp[1]);
//			map.put("zhong2", temp[2]);
			map.put("Shijian", temp[0]);
		}
		showInfo(list,adtivity, showLvListener, cancelAble,map);
	}


	private void showInfo(List<Map<String, String>> list, Activity adtivity, final ShowLvListener showLvListener, boolean cancelAble,
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

		View vieww=view.findViewById(R.id.dialog_tige_view);
		TextView tianjia=(TextView)view.findViewById(R.id.dialog_tige_tianjia);
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
					showLvListener.add1(et1.getText().toString(),et3.getText().toString());
				}else{
					showLvListener.add2(et1.getText().toString(),et2.getText().toString(),et3.getText().toString());
				}
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
}
