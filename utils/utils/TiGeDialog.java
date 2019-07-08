package com.jhjz.emr.lstd_public.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jhjz.emr.lstd_public.R;

import java.util.zip.Inflater;

/**
 * Created by jhjz01 on 2018/7/12.
 */

public class TiGeDialog  {
    private EditText et_input,et_input_1;
    private TextView tv_danwei,tv_danwei_1;
    private Button btn_cancle;
    private Button btn_ok;
    private LinearLayout ll_layout2;

    private Dialog mDialog;
    private Activity mActivity;

    /**
     * 初始化对话框
     * @param activity
     */
    public TiGeDialog(Activity activity, final String danwei, final TextView textView){
        this.mActivity=activity;
        mDialog=new Dialog(activity);
        View view= activity.getLayoutInflater().inflate(R.layout.dialog_tg,null);
        et_input=view.findViewById(R.id.et_input);
        tv_danwei=view.findViewById(R.id.tv_danwei);
        btn_cancle=view.findViewById(R.id.btn_cancle);
        btn_ok=view.findViewById(R.id.btn_ok);
        ll_layout2=view.findViewById(R.id.ll_layout2);
        et_input_1=view.findViewById(R.id.et_input_1);
        tv_danwei_1=view.findViewById(R.id.tv_danwei_1);
        mDialog.setContentView(view);
        //dialog中含有edittext默认不会弹出键盘，需要单独设置自动弹出键盘
        Window window = mDialog.getWindow();
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        if(danwei.equals("mmHg")){
            //说明是血压调用，显示第二个输入框
            ll_layout2.setVisibility(View.VISIBLE);
            tv_danwei.setText("收缩压 "+danwei);
            tv_danwei_1.setText("舒张压 "+danwei);
        }else{
            ll_layout2.setVisibility(View.GONE);
            tv_danwei.setText(danwei);
        }
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(danwei.equals("mmHg")){
                    //说明是血压，高低压必须都填写
                    if(et_input.getText().toString().isEmpty()||et_input_1.getText().toString().isEmpty()){
                        ToastUtil.show(mActivity,"请填写完整", Toast.LENGTH_SHORT);
                        return;
                    }
                    textView.setText(et_input.getText().toString()+"/"+et_input_1.getText().toString()+" "+danwei);
                }else{
                    String inputContent=et_input.getText().toString();
                    if(inputContent.isEmpty()){
                        textView.setText("");
                    }else{
                        textView.setText(et_input.getText().toString()+" "+danwei);
                    }


                }
                mDialog.dismiss();
            }
        });

    }

    /**
     * 显示对话框
     */
    public void showDiaglog(){
        if(mDialog!=null){
            mDialog.show();
            WindowManager.LayoutParams attributes = mDialog.getWindow().getAttributes();
            attributes.width=Utilss.dip2px(mActivity,300);
            mDialog.getWindow().setAttributes(attributes);
        }
    }

    /**
     * 隐藏对话框
     */
    public void dismissDialog(){
        if(mDialog!=null){
            mDialog.dismiss();
        }
    }
}
