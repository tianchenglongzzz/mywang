package com.jhjz.emr.lstd_public.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.jhjz.emr.lstd_public.bean.LoginBean;
import com.jhjz.emr.lstd_public.utils.SpUtil;

public class MyRecycleview extends RecyclerView {
    private boolean isClick=true;

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public MyRecycleview(Context context) {
        super(context);
    }

    public MyRecycleview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecycleview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LoginBean.HzListBean patient = SpUtil.getPatient();
//        if (patient==null){
//            return true;
//        }
//        if (patient!=null){
//            if (patient.getHZStateReason().equals("患者已转归")){
//                return false;
//            }else{
//                return super.dispatchTouchEvent(ev);
//            }
//        }
        if (isClick){
            return super.dispatchTouchEvent(ev);
        }else{
            return false;
        }
    }
}
