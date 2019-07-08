package com.lc.myapplication.model;

import android.util.Log;

/**
 * 项目名称：MyModel
 * 创建人：刘
 * 创建时间：2019-07-08 09:20
 * 功能描述：
 */
public class MainModelImpl implements MainModel {
    @Override
    public String getDataFromNet() {
        return "MVP 模式,into fragment";
    }

    @Override
    public void stopResquest() {
        Log.i("MainModelImpl", "stop request...");
    }
}
