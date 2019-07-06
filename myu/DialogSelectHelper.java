/**
 * created by 小卷毛, 2017/02/20
 * Copyright (c) 2017, 416143467@qq.com All Rights Reserved.
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG             #
 * #                                                   #
 */
package com.meida.shaokaoshop.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.meida.shaokaoshop.R;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * 项目名称：Tiger_Treasure
 * 创建人：小卷毛
 * 创建时间：2017-10-25 17:54
 */
public class DialogSelectHelper {

    @SuppressLint("StaticFieldLeak")
//    private static MProgressDialog mMProgressDialog;
    private static BottomBaseDialog dialog;

    private DialogSelectHelper() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 选择日期
     *
     * @param context

     * @param callback
     */

    public static void showDateDialog(

            final Context context, final List<String>items,

            final DateAllCallBack callback) {

             dialog = new BottomBaseDialog(context) {

            private LoopView loop_item;
            List<String> apmList=new ArrayList<>();
            @Override
            public View onCreateView() {
                View view = View.inflate(context, R.layout.dialog_select_item, null);

                TextView tv_title = view.findViewById(R.id.tv_dialog_select_title);
                TextView tv_cancel = view.findViewById(R.id.tv_dialog_select_cancle);
                TextView tv_ok = view.findViewById(R.id.tv_dialog_select_ok);
                loop_item = view.findViewById(R.id.lv_dialog_select);


//                tv_title.setText(title);
                loop_item.setTextSize(15f);

                loop_item.setNotLoop();


                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        callback.dismissDialog();
                    }
                });

                tv_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();


                        callback.doWork(items.get(loop_item.getSelectedItem()));

                        callback.dismissDialog();
                    }
                });

                return view;
            }

            @Override
            public void setUiBeforShow() {

                loop_item.setItems(items);

            }

        };
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 选择地址  仿京东
     * @return
     */
    public static void showLocationDialog(){



    }


    private static List<String> dateToList(int minValue, int maxValue, String format) {
        List<String> items = new ArrayList<>();

        for (int i = 0; i < maxValue - minValue + 1; i++) {
            int value = minValue + i;
            items.add(format != null ? String.format(format, value) : Integer.toString(value));
        }

        return items;
    }

    public interface HintCallBack {
        void doWork();
    }

    public interface ItemCallBack {
        void doWork(int position, String name);
    }

    public interface WayCallBack {
        void doWork(String id, String name);
    }

    public interface DateAllCallBack {
        void doWork(String item);


        void dismissDialog();
    }

    public interface LocationAllCallBack {
        void doWork(String date);

        void dismissDialog();
    }
}
