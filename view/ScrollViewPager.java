package com.jhjz.emr.lstd_public.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class ScrollViewPager extends ViewPager {


    public ScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewPager(Context context) {
        super(context);
    }


    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        return false;
    }
}
