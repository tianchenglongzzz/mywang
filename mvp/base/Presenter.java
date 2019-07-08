package com.lc.myapplication.base;

/**
 * 项目名称：MyModel
 * 创建人：刘
 * 创建时间：2019-07-08 08:38
 * 功能描述：
 */
public interface Presenter<M extends Model,V extends View> {

    /*
    * 注册Model
    *
    * */
    void registerModel(M mdel);

    /*
    * 注册View
    *
    *
    * */
    void registerView(V view);
    /*
    *获取View
    *@return
    *
    * */
    V getView();

    /*
    * 销毁动作
    *
    * */
    void destroy();





}
