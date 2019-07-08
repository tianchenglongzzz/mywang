package com.jhjz.emr.lstd_public.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jhjz.emr.lstd_public.R;

/**
 * Toast管理工具类,有且只有一个Toast对象进行展示操作
 *
 * @author dzq
 */
public class ToastUtil {
    private static Toast toast;


    public static void show(Context context, String text, int duration, int gravity, int xOffset, int yOffset) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        //
        toast.setGravity(gravity, xOffset, yOffset);
        toast.setText(text);
        toast.setDuration(duration);
        toast.show();
    }

    public static void show(Context context, String text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, "",Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.setDuration(duration);
        toast.show();
    }


    public static void show(Context context, int text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.setDuration(duration);
        toast.show();
    }
    private static Toast mToast;
    private static TextView mTextView;
    private static String message;
    public static void show(Context context, int layoutId, String msg) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //自定义布局
        View view = inflater.inflate(layoutId, null);
        //自定义toast文本
        mTextView = (TextView)view.findViewById(R.id.toast_msg);
        mTextView.setText(msg);
        mTextView.setCompoundDrawablesWithIntrinsicBounds(null,
                context.getResources().getDrawable(R.drawable.toast_img), null, null);
        if (mToast == null) {
            mToast = new Toast(context);
        }
        //设置toast居中显示
        mToast.setGravity(Gravity.TOP, 0, 60);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(view);
        mToast.show();
    }
}
