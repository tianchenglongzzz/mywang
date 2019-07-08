package com.lc.myapplication.presenter;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.lc.myapplication.base.BasePresenter;
import com.lc.myapplication.base.Model;
import com.lc.myapplication.model.MainModel;
import com.lc.myapplication.view.MainView;

/**
 * 项目名称：MyModel
 * 创建人：刘
 * 创建时间：2019-07-08 09:28
 * 功能描述：
 */
public class MainPresenter extends BasePresenter<MainModel, MainView> {

    public void getData(){
        String dataFromNet =null;
        if (model!=null){
            dataFromNet= model.getDataFromNet();
        }

        if (getView()!=null){
            Toast.makeText((Activity)getView(),dataFromNet,Toast.LENGTH_SHORT).show();
            getView().setData(dataFromNet);
        }
    }

    @Override
    protected void onViewDestory() {
         if (model !=null){
              model.stopResquest();
         }
    }
}
