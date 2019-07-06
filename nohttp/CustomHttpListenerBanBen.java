package com.meida.shaokaoshop.nohttp;

import android.content.Context;
import android.util.Log;

import com.meida.shaokaoshop.utils.JsonUtil;

import com.yolanda.nohttp.rest.Response;

import org.json.JSONObject;

public abstract class CustomHttpListenerBanBen<T> implements HttpListener<String> {

    private JSONObject object;
    private Context context;
    private boolean isGson;
    private Class<T> dataM;

    public CustomHttpListenerBanBen(Context context, boolean isGson, Class<T> dataM) {
        this.context = context;
        this.isGson = isGson;
        this.dataM = dataM;
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        Log.i("Nohttp_OnSuccess", "请求成功：\n" + response.get());
        try {
            String jsonStr=response.get();
            object = new JSONObject(response.get());
                if (isGson && dataM != null) {
//                    Gson gson = new Gson();
                    doWork(JsonUtil.jsonToBean (object.toString(), dataM), "100", jsonStr);
                } else {
                    doWork((T) object, "-100", jsonStr);
                }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {



            try {
                onFinally(object, object.getString("msgcode"), true);
            } catch (Exception e) {
                e.printStackTrace();
                onFinally(object,"-100", true);
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
