package com.lc.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * 项目名称：MyModel
 * 创建人：刘
 * 创建时间：2019-07-08 08:58
 * 功能描述：
 */
public abstract class BaseMvpActivity <M extends Model,V extends View,P extends BasePresenter> extends AppCompatActivity implements BaseMvp<M,V,P> ,View {
    protected   P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Presenter
        presenter=createPresenter();
        if (presenter!=null){
            //将Model层注册到Presenter
            presenter.registerView(createView());
            //将View层注册到Ppresenter
            presenter.registerModel(createModel());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       if (presenter!=null){
           //Activity销毁时的调用，让具体实现BasePresenter中onViewDestroy()方法做出决定
           presenter.destroy();
       }
    }

    @Override
    public void showToast(String tost) {
        Toast.makeText(this,tost,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }
}
