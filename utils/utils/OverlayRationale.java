package com.jhjz.emr.lstd_public.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.jhjz.emr.lstd_public.R;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

public class OverlayRationale implements Rationale<Void> {
    @Override
    public void showRationale(Context context, Void data, final RequestExecutor executor) {
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle("提示")
                .setMessage(R.string.message_overlay_failed)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        executor.execute();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        executor.cancel();
                    }
                })
                .show();
    }
}
