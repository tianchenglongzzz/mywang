package com.jhjz.emr.lstd_public.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jhjz.emr.lstd_public.R;

/**
 * Created by jhjz01 on 2018/7/20.
 */

public class BottomDialog {
    private BottomDialogListener dialogListener;
    private  Dialog mCameraDialog;
    private LinearLayout root;

    public BottomDialog(Context context){
        mCameraDialog = new Dialog(context, R.style.BottomDialog);
        root = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.dialog_bottom, null);
        //初始化视图
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) context.getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);

        root.findViewById(R.id.btn_choose_img).setOnClickListener(onClickListener);
        root.findViewById(R.id.btn_open_camera).setOnClickListener(onClickListener);
        root.findViewById(R.id.btn_cancel).setOnClickListener(onClickListener);
    }

    public void  setSheXiang(){
        Button btn = root.findViewById(R.id.btn_open_camera);
        btn.setText("摄像");
    }

    public void show(){
        if(mCameraDialog!=null&&!mCameraDialog.isShowing()){
            mCameraDialog.show();
        }
    }
    public void dismiss(){
        if(mCameraDialog!=null&&mCameraDialog.isShowing()){
            mCameraDialog.dismiss();
        }
    }
    public void setDialogListener(BottomDialogListener dialogListener){
        this.dialogListener=dialogListener;
    }
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_choose_img:
                    dialogListener.chooseImg();
                    break;
                case R.id.btn_open_camera:
                    dialogListener.openCamera();
                    break;
                case R.id.btn_cancel:
                    dialogListener.cancel();
                    break;
            }
        }
    };

    public interface BottomDialogListener{
        void chooseImg();
        void openCamera();
        void cancel();
    }
}
