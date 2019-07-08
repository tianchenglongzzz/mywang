package com.jhjz.emr.lstd_public.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jhjz.emr.lstd_public.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义退出弹出框
 *
 * @author qinfan
 * <p>
 * 2015-11-6
 */
public class MyDialog {
    private Activity activity;
    private AlertDialog dialog;
    private int count = 0;
    //标记
    private String content = null;
    private int flagI;


    public MyDialog(Activity activity) {
        this.activity = activity;
    }

    public void showDialog() {
        dialog = new AlertDialog.Builder(activity).create();
        //点击外部区域不能取消dialog
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(keylistener);
        dialog.show();

        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_tasklist);
//		TextView tv_title = (TextView) window.findViewById(R.id.dialog_title);
//		tv_title.setText(title);
        TextView tv_confirm = (TextView) window.findViewById(R.id.btn_sure);
        TextView tv_cancel = (TextView) window.findViewById(R.id.btn_quxiao);
        tv_confirm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                Toast.makeText(activity, "确定", Toast.LENGTH_SHORT).show();
            }
        });

        tv_cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                Toast.makeText(activity, "取消", Toast.LENGTH_SHORT).show();
            }
        });

        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
    }

    public void detach() {
        if(raoXingLocationListener!=null){
            raoXingLocationListener = null;
        }
        if(jjRelationListener!=null){
            jjRelationListener = null;
        }
    }
  /**
   * 添加紧急联系人与患者的关系
   * */
    public void showJjRelation() {
        final AlertDialog dialog = new AlertDialog.Builder(activity).create();
        //点击外部区域不能取消dialog
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(keylistener);
        dialog.show();
        Window window = dialog.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setContentView(R.layout.dialog_jj_relation);
        final RadioGroup radioGroup = window.findViewById(R.id.group);
        TextView tv_confirm = (TextView) window.findViewById(R.id.yes);
        TextView tv_cancel = (TextView) window.findViewById(R.id.no);
        final EditText editText = window.findViewById(R.id.et_content);
        final RadioButton other = window.findViewById(R.id.rb_other);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_jiaren:
                        editText.setVisibility(View.GONE);
                        hideInput(activity, editText);
                        break;
                    case R.id.rb_qinshu:
                        editText.setVisibility(View.GONE);
                        hideInput(activity, editText);
                        break;
                    case R.id.rb_tongshi:
                        editText.setVisibility(View.GONE);
                        hideInput(activity, editText);
                        break;
                    case R.id.rb_other:
                        editText.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                other.setChecked(true);
                InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(editText, 0);
            }
        });
        tv_confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
               int  groupCount = radioGroup.getChildCount();
                for (int i = 0; i < groupCount; i++) {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
                    if (rb.isChecked()) {
                        flagI = i;
                        content = rb.getText().toString().trim();
                    }
                }
                if (content != null && !("").equals(content)) {
                    if (content.equals("其他")) {
                        String etContent = editText.getText().toString().trim();
                        if(!etContent.equals("") && etContent != null){
                            jjRelationListener.clickYes(etContent);
                        }else{
                            jjRelationListener.clickYes("其他");
                        }
                    } else {
                        jjRelationListener.clickYes(content);
                    }
                } else {
                    ToastUtil.show(activity, "请选择关系", 0);
                }
                dialog.dismiss();
            }
        });

        tv_cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                jjRelationListener.clickNo();
            }
        });

        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
    }

    JjRelationListener jjRelationListener;

    public void setClick(JjRelationListener jjRelationListener) {
        this.jjRelationListener = jjRelationListener;
    }

    public interface JjRelationListener {
        void clickYes(String s);
        void clickNo();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void showRaoXingLocation() {

        final AlertDialog dialogLocation = new AlertDialog.Builder(activity).create();
        //点击外部区域不能取消dialog
        dialogLocation.setCanceledOnTouchOutside(false);
        dialogLocation.setOnKeyListener(keylistener);
        dialogLocation.show();


        Window window = dialogLocation.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setContentView(R.layout.dialog_raoxing_location);
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);
        //时间
        String substring = format.substring(11);
        //日期
        String substring1 = format.substring(0, 11);
        //时间
        TextView time = window.findViewById(R.id.time);
        //日期
        TextView rq = window.findViewById(R.id.date);
        rq.setText(substring1);
        time.setText(substring);
        final RadioGroup radioGroup = window.findViewById(R.id.group);


        final RadioButton ct = window.findViewById(R.id.ct_tv);
        final RadioButton no_rx = window.findViewById(R.id.no_rx);
        RadioButton rs = window.findViewById(R.id.rs_tv);
        RadioButton dg = window.findViewById(R.id.dg_tv);
        final RadioButton other = window.findViewById(R.id.other);
        final EditText editText = window.findViewById(R.id.add_content);
        TextView yes = window.findViewById(R.id.yes);
        TextView no = window.findViewById(R.id.no);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.no_rx:
                        editText.setVisibility(View.GONE);
                        hideInput(activity, editText);
                        break;
                    case R.id.ct_tv:
                        editText.setVisibility(View.GONE);
                        hideInput(activity, editText);
                        break;
                    case R.id.rs_tv:
                        editText.setVisibility(View.GONE);
                        hideInput(activity, editText);
                        break;
                    case R.id.dg_tv:
                        editText.setVisibility(View.GONE);
                        hideInput(activity, editText);
                        break;
                    case R.id.other:
                        editText.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                other.setChecked(true);
                InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(editText, 0);
            }
        });

        yes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                count = radioGroup.getChildCount();
                for (int i = 0; i < count; i++) {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
                    if (rb.isChecked()) {
                        flagI = i;
                        content = rb.getText().toString();
                    }
                }
                if (content != null && !("").equals(content)) {
                    if (content.equals("其他")) {
                        String etContent = editText.getText().toString().trim();
                        if(!etContent.equals("") && etContent != null){
                            raoXingLocationListener.locationClickYes(etContent, flagI);
                        }else{
                            ToastUtil.show(activity, "请输入绕行位置", 0);
                        }
                    } else {
                        raoXingLocationListener.locationClickYes(content, flagI);
                    }
                } else {
                    ToastUtil.show(activity, "请选择绕行位置", 0);
                }
            }
        });
        no.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLocation.dismiss();
                raoXingLocationListener.locationClickNo();
                //  dialog.show();
            }
        });
        dialogLocation.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
    }

    RaoXingLocationListener raoXingLocationListener;

    public void setLocationClick(RaoXingLocationListener raoXingLocationListener) {
        this.raoXingLocationListener = raoXingLocationListener;
    }

    public interface RaoXingLocationListener {
        void locationClickYes(String content, int flag);

        void locationClickNo();
    }

    public static OnKeyListener keylistener = new OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };

    private void hideInput(Context context, View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
