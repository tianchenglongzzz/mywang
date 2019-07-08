package com.jhjz.emr.lstd_public.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.jhjz.emr.lstd_public.R;
import com.jhjz.emr.lstd_public.adapter.DrawBackPopAdapterr;
import com.jhjz.emr.lstd_public.utils.Utilss;

import java.util.List;

public class SelectPop {
	private Context mContext;
	private TextView mReason;
	private PopupWindow selectPopupWindow;
	private ImageView spinnerImage;
	public SelectPop(Context context, TextView textView, ImageView sspinnerImage) {
		this.mContext=context;
		this.mReason=textView;
		this.spinnerImage=sspinnerImage;
	}
	
	@SuppressWarnings("deprecation")
	public void initPopuWindow(final TextView et, final String str[]) {
		View loginwindow = (View) Utilss.getView(mContext, R.layout.pop_draw_back);
		ListView listView = (ListView) loginwindow.findViewById(R.id.draw_back_list);
		// 设置自定义Adapter
		final DrawBackPopAdapterr popAdapter = new DrawBackPopAdapterr(mContext, str);
		
		setListViewHeightBasedOnChildren(listView);
		
		listView.setAdapter(popAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@SuppressLint("ResourceAsColor")
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
				et.setText(str[position]);
				et.setTextColor(mContext.getResources().getColor(R.color.black));
				popupDismiss();
			}
		});
		selectPopupWindow = new PopupWindow(loginwindow, mReason.getWidth(), LayoutParams.WRAP_CONTENT, true);
		selectPopupWindow.setOutsideTouchable(true);
		// 当点击屏幕其他部分及Back键时PopupWindow消失
		selectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		selectPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				spinnerImage.setImageResource(R.drawable.draw_back_bottom);
			}
		});

	}
	
	//此方法在setAdapter之前调用
			public void setListViewHeightBasedOnChildren(ListView listView) {
			    ListAdapter listAdapter = listView.getAdapter();
			    if (listAdapter == null) {
			     return;
			    }
			    int totalHeight = 0;
			    for (int i = 0; i < listAdapter.getCount(); i++) {
			     View listItem = listAdapter.getView(i, null, listView);
			     listItem.measure(0, 0);
			     totalHeight += listItem.getMeasuredHeight();
                 if(i==5){
			    	 break;
			     }
			    }
			   
			    ViewGroup.LayoutParams params = listView.getLayoutParams();
			    
			    params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
			    listView.setLayoutParams(params);
			 }
	
	@SuppressWarnings("deprecation")
	public void initPopuWindow(final TextView et, List<String> str1) {
		final String[] str=new String[str1.size()];
		for(int i=0;i<str1.size();i++){
			str[i]=str1.get(i);
		}
		View loginwindow = (View) Utilss.getView(mContext, R.layout.pop_draw_back);
		ListView listView = (ListView) loginwindow.findViewById(R.id.draw_back_list);
		// 设置自定义Adapter
		final DrawBackPopAdapterr popAdapter = new DrawBackPopAdapterr(mContext, str);
		listView.setAdapter(popAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@SuppressLint("ResourceAsColor")
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
				et.setText(str[position]);
				et.setTextColor(mContext.getResources().getColor(R.color.black));
				popupDismiss();
			}
		});
		selectPopupWindow = new PopupWindow(loginwindow, mReason.getWidth(), LayoutParams.WRAP_CONTENT, true);
		selectPopupWindow.setOutsideTouchable(true);
		// 当点击屏幕其他部分及Back键时PopupWindow消失
		selectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		selectPopupWindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				spinnerImage.setImageResource(R.drawable.draw_back_bottom);
			}
		});

	}

	public void popupDismiss() {
		selectPopupWindow.dismiss();
	}

	public void popupWindwShowing(View view) {
		selectPopupWindow.showAsDropDown(view, 0, -1);
		spinnerImage.setImageResource(R.drawable.draw_back_top);
	}
}
