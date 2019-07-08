package com.lc.myapplication.model;

import android.database.Observable;

import com.lc.myapplication.base.Model;

/**
 * 项目名称：MyModel
 * 创建人：刘
 * 创建时间：2019-07-08 09:13
 * 功能描述：
 */
public interface MainModel extends Model {
    /*
    *   从网络上获取数据
    *
    * */

    String getDataFromNet();

    /*
    *  停止请求
    *
    * */
    void stopResquest();
    /**
     * 从网络获取数据
     *
     * @return
     */





}
