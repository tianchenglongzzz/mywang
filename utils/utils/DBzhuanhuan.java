package com.jhjz.emr.lstd_public.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by hopc on 2017/11/14.
 */

public class DBzhuanhuan {
    // 将json 数组转换为List 对象
    public List<String> getListString(String jsonString) {
        List<String> list = new ArrayList<String>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                list.add(jsonArray.getString(i));
            }
            return list;
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    // 将json 数组转换为Map 对象
    public Map<String, Object> getMap(String jsonString) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonString);
            @SuppressWarnings("unchecked")
            Iterator<String> keyIter = jsonObject.keys();
            String key;
            Object value;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key);
                if (value.toString() == "null") {
                    valueMap.put(key, " ");
                } else {
                    valueMap.put(key, value);
                }

            }
            return valueMap;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 把json 转换为 ArrayList 形式
    public List<Map<String, Object>> getList(String jsonString) {
        List<Map<String, Object>> list = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            JSONObject jsonObject;
            list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                list.add(getMap(jsonObject.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 把json 转换为 ArrayList 形式
    public List<Map<String, Object>> getLisTime(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonString);
            @SuppressWarnings("unchecked")
            Iterator<String> keyIter = jsonObject.keys();
            String key;
            Object value;
            while (keyIter.hasNext()) {
                Map<String, Object> valueMap = new HashMap<String, Object>();
                key = keyIter.next();
                value = jsonObject.get(key);
                valueMap.put("key", key);
                valueMap.put("value", value);
                list.add(valueMap);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 针对时间
     *
     * @param jsonString
     * @return
     */
    // 把json 转换为 ArrayList 形式
    public List<Map<String, Object>> getListTimeAll(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray jsonArray2 = jsonArray.getJSONArray(i);
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("text", jsonArray2.get(0));
                map.put("code", jsonArray2.get(1));
                String value = jsonArray2.get(2) != null ? jsonArray2.get(2).toString() : "";
                map.put("value", value.indexOf("1970") > -1 ? "" : value);
                map.put("leixing", jsonArray2.get(3));
                map.put("yanse", jsonArray2.get(4));
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 针对下载的返回的文件
     *
     * @param jsonString
     * @return
     */
    // 把json 转换为 ArrayList 形式
    public List<Map<String, Object>> getFileList(String jsonString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray jsonArray2 = jsonArray.getJSONArray(i);
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("name", jsonArray2.get(0));
                map.put("http", jsonArray2.get(1));
                map.put("key", jsonArray2.get(2));
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}