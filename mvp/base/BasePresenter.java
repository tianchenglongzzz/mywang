package com.lc.myapplication.base;

import android.view.Display;

import java.lang.ref.WeakReference;

/**
 * 项目名称：MyModel
 * 创建人：刘
 * 创建时间：2019-07-08 08:42
 * 功能描述：
 */
public abstract class BasePresenter<M extends Model,V extends View> implements Presenter<M,V> {


    public WeakReference<V> wrf;
    public M model;

    @Override
    public void registerModel(M mdel) {
         this.model=mdel;
    }

    @Override
    public void registerView(V view) {
              wrf= new WeakReference(view);
    }

    @Override
    public V getView() {
        if (wrf==null){
            return null;
        }else {
            return wrf.get();
        }

    }

    @Override
    public void destroy() {
         if (wrf!=null){
             wrf.clear();
             wrf=null;
         }

    }
    protected abstract void onViewDestory();
}
