package com.jhjz.emr.lstd_public.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.jhjz.emr.lstd_public.R;

/**
 * Created by jhjz01 on 2018/5/24.
 */

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    private int itemSpace;
    private int itemNum;
    private Context context;

    /**
     *
     * @param itemSpace item间隔
     * @param itemNum 每行item的个数
     */
    public RecyclerItemDecoration(Context context,int itemSpace, int itemNum) {
        this.itemSpace = itemSpace;
        this.itemNum = itemNum;
        this.context=context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = convertDpToPixel(itemSpace);
        if (parent.getChildLayoutPosition(view)%itemNum == 0){
            outRect.left = 0;
        } else {
            outRect.left = convertDpToPixel(itemSpace);
        }

    }


    private int convertDpToPixel(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (dp * displayMetrics.density);
    }
}
