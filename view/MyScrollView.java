package com.jhjz.emr.lstd_public.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by hopc on 2017/11/29.
 */

public class MyScrollView extends ScrollView {

    private float xDistance;
    private float yDistance;
    private float xLast;
    private float yLast;


    public MyScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyScrollView(Context context) {
        this(context,null);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                /**
                 * X轴滑动距离大于Y轴滑动距离，也就是用户横向滑动时，返回false，ScrollView不处理这次事件，
                 * 让子控件中的TouchEvent去处理，所以横向滑动的事件交由ViewPager处理，
                 * ScrollView只处理纵向滑动事件
                 */
                if (xDistance > yDistance) {
                    return false;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }
}
