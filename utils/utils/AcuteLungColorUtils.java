package com.jhjz.emr.lstd_public.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;

import com.jhjz.emr.lstd_public.R;

public class AcuteLungColorUtils {


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
    //危险因素
    public static CheckBox[] getViewWXId(View view) {
        //增加新条目添加id,返回,然后回到适配器里面添加点击事件即可
        CheckBox wxCheckBox1 = view.findViewById(R.id.wx_checkbox1);
        CheckBox wxCheckBox2 = view.findViewById(R.id.wx_checkbox2);
        CheckBox wxCheckBox3 = view.findViewById(R.id.wx_checkbox3);
        CheckBox wxCheckBox4 = view.findViewById(R.id.wx_checkbox4);
        CheckBox wxCheckBox5 = view.findViewById(R.id.wx_checkbox5);
            return new CheckBox[]{wxCheckBox1, wxCheckBox2, wxCheckBox3,wxCheckBox4,wxCheckBox5};

    }

    //症状
    public static  CheckBox[] getViewZZId(View view){
        CheckBox zzCheckBox1 = view.findViewById(R.id.zz_checkbox1);
        CheckBox zzCheckBox2 = view.findViewById(R.id.zz_checkbox2);
        CheckBox zzCheckBox3 = view.findViewById(R.id.zz_checkbox3);
        return new CheckBox[]{zzCheckBox1, zzCheckBox2, zzCheckBox3};
    }

    //体征
    public static CheckBox[] getViewTZId(View view){
        CheckBox tzCheckBox1 = view.findViewById(R.id.tz_checkbox1);
        CheckBox tzCheckBox2 = view.findViewById(R.id.tz_checkbox2);
        CheckBox tzCheckBox3 = view.findViewById(R.id.tz_checkbox3);
        CheckBox tzCheckBox4 = view.findViewById(R.id.tz_checkbox4);
        return new CheckBox[]{tzCheckBox1, tzCheckBox2, tzCheckBox3,tzCheckBox4};
    }

}
