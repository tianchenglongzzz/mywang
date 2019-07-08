package com.jhjz.emr.lstd_public.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ListView;

public class ReportListView extends ListView {

	/**
	 * handler
	 */
	private Handler handler;
	/**
	 * 触发长按事件
	 */
	private Runnable performLongClick = new Runnable() {
		@Override
		public void run() {
			ReportListView.super.performLongClick();
		}
	};

	public ReportListView(Context paramContext) {
	        super(paramContext);
	    }

	    public ReportListView(Context paramContext, AttributeSet paramAttributeSet) {
	        super(paramContext, paramAttributeSet);
			handler = new Handler();
	    }

	    public ReportListView(Context paramContext, AttributeSet paramAttributeSet,
                              int paramInt) {
	        super(paramContext, paramAttributeSet, paramInt);
			handler = new Handler();
	    }
	    
	    @Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    }

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				handler.postDelayed(performLongClick, ViewConfiguration.getLongPressTimeout());
				break;
			case MotionEvent.ACTION_MOVE:
				handler.removeCallbacks(performLongClick);
				break;
			case MotionEvent.ACTION_UP:
				handler.removeCallbacks(performLongClick);
				break;
			default:
				break;
		}
		return super.onTouchEvent(ev);
	}
}
