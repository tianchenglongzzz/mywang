package com.lc.myapplication.base;

import android.view.Display;

/**
 * 项目名称：MyModel
 * 创建人：刘
 * 创建时间：2019-07-08 08:54
 * 功能描述：
 */
public interface BaseMvp<M extends Model,V extends View,P extends BasePresenter> {
     M createModel();
     V createView();
     P createPresenter();


}
