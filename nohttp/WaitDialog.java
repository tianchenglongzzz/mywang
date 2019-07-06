package com.meida.shaokaoshop.nohttp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.meida.shaokaoshop.R;

import com.tuyenmonkey.mkloader.MKLoader;

/**
 * Created by Administrator on 2017/3/31.
 */

public class WaitDialog extends Dialog {

    private MKLoader dialog;
    private LinearLayout llEmpty;

    public WaitDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
    }

    public WaitDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected WaitDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_dialog);
        View view = View.inflate(getContext(), R.layout.dialog, null);
        llEmpty = (LinearLayout)findViewById(R.id.ll_empty);
        llEmpty.addView(view);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = getContext().getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.44); // 高度设置为屏幕的0.6
        lp.height = (int) (d.heightPixels * 0.25); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }
}
