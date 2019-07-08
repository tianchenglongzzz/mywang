package com.jhjz.emr.lstd_public.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.jhjz.emr.lstd_public.R;


public class MyDialogBasicInfo extends Dialog implements View.OnClickListener {
    private Button yes;
    private Button no;
    private TextView titleTV;
    private TextView tvProject;
    private String titleStr;
    private String messageStr;
    private int start;
    private int end;
    private String yesStr, noStr;
    private onNoOnclickListener noOnclickListener;
    private onYesOnclickListener yesOnclickListener;
    private EditText etInfo;
    private TextView tvFanWei;
    private String number;
    private Context context;
    private CheckBox cbJzys;
    private CheckBox cbRsys;
    private CheckBox cbJrys;
    private String checkBoxStr = "";
    private String dataStr = "";

    public MyDialogBasicInfo(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }


    public void setYesOnclickListener(String str, onYesOnclickListener yesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = yesOnclickListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_basic_info);
        setCanceledOnTouchOutside(false);
        initView();
        initData();
        initEvent();
    }


    private void initView() {
        yes = findViewById(R.id.btn_confirm);
        no = findViewById(R.id.btn_cancel);
        titleTV = findViewById(R.id.tv_str);
        cbJzys = findViewById(R.id.cb_jzys);
        cbRsys = findViewById(R.id.cb_rsys);
        cbJrys = findViewById(R.id.cb_jrys);

        cbJzys.setOnClickListener(this);
        cbRsys.setOnClickListener(this);
        cbJrys.setOnClickListener(this);

        if (messageStr != null && !TextUtils.isEmpty(messageStr)){
            if (messageStr.contains("急诊医生")){
                cbJzys.setChecked(true);
                checkBoxMethod(cbJzys);
            }
            if (messageStr.contains("溶栓医生")){
                cbRsys.setChecked(true);
                checkBoxMethod(cbRsys);
            }
            if (messageStr.contains("介入医生")){
                cbJrys.setChecked(true);
                checkBoxMethod(cbJrys);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_jzys:
                checkBoxMethod(cbJzys);
                break;
            case R.id.cb_rsys:
                checkBoxMethod(cbRsys);
                break;
            case R.id.cb_jrys:
                checkBoxMethod(cbJrys);
                break;
        }
    }


    private void initData() {
        if (titleStr != null) {
            titleTV.setText(titleStr);
        }
    }

    public void checkBoxMethod(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            checkBoxStr = checkBoxStr + checkBox.getText().toString() + "/";
        } else {
            if (!TextUtils.isEmpty(checkBoxStr)){
                checkBoxStr = checkBoxStr.replace(checkBox.getText().toString() + "/", "");
            } else {
                checkBoxStr = "";
            }
        }
    }

    private void initEvent() {

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    dataStr = checkBoxStr;
                    if (dataStr != ""){
                        yesOnclickListener.onYesOnclick(checkBoxStr, dataStr);
                        dismiss();
                    }
                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
                dismiss();
            }
        });

    }

    public void setTitle(String title) {
        titleStr = title;
    }


    public void setMessage(String message) {
        messageStr = message;
    }

    public void setFanwei(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public interface onNoOnclickListener {
        void onNoClick();
    }

    public interface onYesOnclickListener {
        void onYesOnclick(String data, String dataStr);
    }


    private void voidrun(EditText editText) {//弹出软键盘的代码
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void closeSoftInputFromWindow(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}