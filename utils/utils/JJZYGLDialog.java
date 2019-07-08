package com.jhjz.emr.lstd_public.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.jhjz.emr.lstd_public.R;
import com.jhjz.emr.lstd_public.base.BaseActivity;
import com.jhjz.emr.lstd_public.callback.StringDialogCallback;
import com.jhjz.emr.lstd_public.config.HandlerCode;
import com.jhjz.emr.lstd_public.config.UrlConfig;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by CSDC-UCT on 2017/11/17.
 */

public class JJZYGLDialog {
    private static JJZYGLDialog single = null;
    private static AlertDialog.Builder builder;// 先得到构造器
    private static Dialog dialog;
    private static String zyglName;
    private static JSONObject hospitalJson;
    private static Handler mHandler;
    private static Message message;
    private static int currentInt;

    public static JJZYGLDialog getInstance(Handler handler, BaseActivity activity, String name ,JSONObject hosJson,int position) {
        zyglName = name;
        hospitalJson = hosJson;
        mHandler = handler;
        currentInt = position;

        if (single == null) {
            single = new JJZYGLDialog(activity);
        } else {
            showInfo(activity);
        }

        return single;
    }

    private JJZYGLDialog(BaseActivity activity) {
        showInfo(activity);
    }

    private static void showInfo(final BaseActivity activity) {
        try {
            if (dialog != null) {
                dialog.dismiss();
            }
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);// THEME_DEVICE_DEFAULT_LIGHT
            View view = View.inflate(activity, R.layout.dialog_jjzygl, null);
            builder.setView(view);
            builder.setCancelable(true);
            final AlertDialog alertDialog = builder.create();

            alertDialog.show();
            final TextView zyglNameText = (TextView) view.findViewById(R.id.zyglNameText);
            zyglNameText.setText(zyglName);

            TextView kongxianText = (TextView) view.findViewById(R.id.kongxianText);
            kongxianText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    // 修改医院实体类
                    if (zyglName.equals("急诊室")){
                        try {
                            hospitalJson.put("JiZhenShi","空闲中");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (zyglName.equals("ICU")){
                        try {
                            hospitalJson.put("ICU","空闲中");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (zyglName.equals("CT室")){
                        try {
                            hospitalJson.put("CT","空闲中");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (zyglName.equals("MR室")){
                        try {
                            hospitalJson.put("MR","空闲中");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (zyglName.equals("NICU")){
                        try {
                            hospitalJson.put("NICU","空闲中");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (zyglName.equals("DSA")){
                        try {
                            hospitalJson.put("DSA","空闲中");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (zyglName.equals("病床")){
                        try {
                            hospitalJson.put("BingChuang","空闲中");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    OkGo.<String>post(UrlConfig.UPDATE_ZiYuanGuanLi)
                            .params("entity_", hospitalJson.toString())
                            .execute(new StringDialogCallback(activity,true) {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    String jsonInfo = response.body();
                                    try {
                                        JSONObject response_result = new JSONObject(jsonInfo);
                                        int Err = response_result.getInt("Err");
                                        String Msg = response_result.getString("Msg");
                                        message = new Message();

                                        if (Err == 0) {
                                            message.what = HandlerCode.UPDATEZYGL_SUCCESS;
                                            message.arg1 = currentInt;
                                            message.obj = "空闲中";
                                            mHandler.sendMessage(message);
                                        } else {
                                            message.what= HandlerCode.UPDATEZYGL_FAILED;
                                            message.obj = Msg;
                                            mHandler.sendMessage(message);
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    LogUtil.d("JJZYGLActivity","急救资源更新失败");
                                }
                            });
                }
            });
            TextView zhanyongText = (TextView) view.findViewById(R.id.zhanyongText);
            zhanyongText.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();

                    // 修改医院实体类
                    if (zyglName.equals("急诊室")){
                        try {
                            hospitalJson.put("JiZhenShi","已占用");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (zyglName.equals("ICU")){
                        try {
                            hospitalJson.put("ICU","已占用");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (zyglName.equals("CT室")){
                        try {
                            hospitalJson.put("CT","已占用");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (zyglName.equals("MR室")){
                        try {
                            hospitalJson.put("MR","已占用");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (zyglName.equals("NICU")){
                        try {
                            hospitalJson.put("NICU","已占用");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (zyglName.equals("DSA")){
                        try {
                            hospitalJson.put("DSA","已占用");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else if (zyglName.equals("病床")){
                        try {
                            hospitalJson.put("BingChuang","已占用");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    OkGo.<String>post(UrlConfig.UPDATE_ZiYuanGuanLi)
                            .params("entity_", hospitalJson.toString())
                            .execute(new StringDialogCallback(activity,true) {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    String jsonInfo = response.body();
                                    try {
                                        JSONObject response_result = new JSONObject(jsonInfo);
                                        int Err = response_result.getInt("Err");
                                        String Msg = response_result.getString("Msg");
                                        message = new Message();

                                        if (Err == 0) {
                                            message.what = HandlerCode.UPDATEZYGL_SUCCESS;
                                            message.arg1 = currentInt;
                                            message.obj = "已占用";
                                            mHandler.sendMessage(message);
                                        } else {
                                            message.what= HandlerCode.UPDATEZYGL_FAILED;
                                            message.obj = Msg;
                                            mHandler.sendMessage(message);
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    LogUtil.d("JJZYGLActivity","急救资源更新失败");
                                }
                            });

                }
            });
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
