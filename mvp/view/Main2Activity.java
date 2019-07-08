package com.lc.myapplication.view;

import android.os.Bundle;
import android.widget.TextView;

import com.lc.myapplication.R;
import com.lc.myapplication.base.BaseMvpActivity;
import com.lc.myapplication.model.MainModel;
import com.lc.myapplication.model.MainModelImpl;
import com.lc.myapplication.presenter.MainPresenter;

public class Main2Activity extends BaseMvpActivity<MainModel,MainView,MainPresenter> implements MainView {

   TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.tv_prompt);
        init();
    }

    private void init() {
         if (presenter!=null){
             presenter.getData();
         }
    }

    @Override
    public MainModel createModel() {
        return new MainModelImpl();
    }

    @Override
    public MainView createView() {
        return this;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void setData(String str) {
          textView.setText(str);
    }



}
