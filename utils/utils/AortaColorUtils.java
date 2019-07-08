package com.jhjz.emr.lstd_public.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;

import com.jhjz.emr.lstd_public.R;

public class AortaColorUtils {

    @SuppressLint("ResourceAsColor")
    public static void setColor(int id, View[] arr){
        for (int i=0;i<arr.length;i++){
            if(id==i){
                arr[i].setBackgroundColor(Color.parseColor("#E2F0D1"));
            }else{
                arr[i].setBackgroundColor(Color.WHITE);
            }
        }
    }
    //病史
    public static CheckBox[] getViewZBSId(View view) {
        //增加新条目添加id,返回,然后回到适配器里面添加点击事件即可
        CheckBox zbsCheckBox1 = view.findViewById(R.id.zbs_checkbox1);
        CheckBox zbsCheckBox2 = view.findViewById(R.id.zbs_checkbox2);
        CheckBox zbsCheckBox3 = view.findViewById(R.id.zbs_checkbox3);
        CheckBox zbsCheckBox4 = view.findViewById(R.id.zbs_checkbox4);
        CheckBox zbsCheckBox5 = view.findViewById(R.id.zbs_checkbox5);
        CheckBox zbsCheckBox6 = view.findViewById(R.id.zbs_checkbox6);
        return new CheckBox[]{zbsCheckBox1, zbsCheckBox2, zbsCheckBox3,zbsCheckBox4,zbsCheckBox5,zbsCheckBox6};

    }

    //胸痛特点
    public static CheckBox[] getViewXTId(View view){
        CheckBox tzCheckBox1 = view.findViewById(R.id.zxt_checkbox1);
        CheckBox tzCheckBox2 = view.findViewById(R.id.zxt_checkbox2);
        CheckBox tzCheckBox3 = view.findViewById(R.id.zxt_checkbox3);
        CheckBox tzCheckBox4 = view.findViewById(R.id.zxt_checkbox4);
        return new CheckBox[]{tzCheckBox1, tzCheckBox2, tzCheckBox3,tzCheckBox4};
    }

    //体征特点
    public static  CheckBox[] getViewTZId(View view){
        CheckBox zzCheckBox1 = view.findViewById(R.id.ztz_checkbox1);
        CheckBox zzCheckBox2 = view.findViewById(R.id.ztz_checkbox2);
        CheckBox zzCheckBox3 = view.findViewById(R.id.ztz_checkbox3);
        CheckBox zzCheckBox4 = view.findViewById(R.id.ztz_checkbox4);
        return new CheckBox[]{zzCheckBox1, zzCheckBox2, zzCheckBox3,zzCheckBox4};
    }


}
