package com.jhjz.emr.lstd_public.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HomeSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    public HomeSpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.bottom = space;
    }

}