package com.meida.shaokaoshop.nohttp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.meida.shaokaoshop.skactivity.LoginActivity;
import com.meida.shaokaoshop.utils.JsonUtil;
import com.meida.shaokaoshop.utils.Utils;

import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

public abstract class CustomHttpListener<T> implements HttpListener<String> {

    private JSONObject object;
    private Context context;
    private boolean isGson;
    private Class<T> dataM;

    public CustomHttpListener(Context context, boolean isGson, Class<T> dataM) {
        this.context = context;
        this.isGson = isGson;
        this.dataM = dataM;
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        Log.i("Nohttp_OnSuccess", "请求成功：\n" + response.get());
        try {
            String jsonStr = response.get();
            object = new JSONObject(response.get());
            if (dataM == null && !"100".equals(object.getString("code"))) {
                LUtils.showToask(context, object.getString("info"));
                return;
            }
            if ("102".equals(object.getString("code"))) {
                Utils.showToast(context, object.getString("info"));
                SharedPreferences sp = context.getSharedPreferences("info", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("token", "");
                editor.putString("tel", "");
                editor.putString("pwd", "");
                editor.putString("msgnum", "");
                editor.putString("rongtoken", "");
                editor.putString("qq", "");
                editor.putString("alipay", "");
                editor.putString("mobile", "");
                editor.putString("nickName", "");
                editor.putString("ranking", "");
                editor.putString("jibie", "");
                editor.putString("userHead", "");
                editor.putString("userName", "");
                editor.putString("wechat", "");
                editor.putString("weibo", "");
                editor.putString("jifen", "");
                editor.putString("tuijian", "");
                editor.putString("accountInfoId", "");
                editor.commit();

//                Intent intent = new Intent(context, LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                context.startActivity(intent);
            }


            if ("100".equals(object.getString("code"))) {
                if (isGson && dataM != null) {
                    doWork(JsonUtil.jsonToBean(object.toString(), dataM), object.getString("code"), jsonStr);
                } else {
                    doWork((T) object, object.getString("code"), jsonStr);
                }
                if (!object.getString("info").isEmpty()){
                    Utils.showToast(context, object.getString("info"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                onFinally(object, object.getString("code"), true);
            } catch (Exception e) {
                e.printStackTrace();
                onFinally(object, "-100", true);
            }
        }
    }

    public abstract void doWork(T data, String code, String jsonStr);

    public void onFinally(JSONObject obj, String code, boolean isSucceed) {

    } // 解析完成，如要执行操作，可重写该方法。

    @Override
    public void onFailed(int what, String url, Object tag, Exception e, int responseCode, long networkMillis) {
//        Log.i("Nohttp_OnFailed", "请求失败：\n" + e.getMessage());
//        LUtils.showToask(context, "请求数据失败");
        onFinally(new JSONObject(), "-1", false); // json数据为空
    }

}
