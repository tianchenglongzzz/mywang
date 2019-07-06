package com.ruanmeng.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 作者 亢兰兰
 * 时间 2017/3/29 0029
 * 公司  郑州软盟
 */

public class GestureListener extends GestureDetector.SimpleOnGestureListener implements
        View.OnTouchListener {
    /**
     * 左右滑动的最短距离
     */
    private int distance = 100;
    /**
     * 左右滑动的最大速度
     */
    private int velocity = 200;
    //声明手势识别类
    private GestureDetector gestureDetector;

    public GestureListener(Context context) {
        super();
        gestureDetector = new GestureDetector(context, this);
        // TODO Auto-generated constructor stub
    }

    /**
     * 向左滑的时候调用的方法，子类应该重写
     *
     * @return
     */
    public boolean left() {
        return false;
    }

    /**
     * 向右滑的时候调用的方法，子类应该重写
     *
     * @return
     */
    public boolean right() {
        return false;
    }

    //移动的监听
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // TODO Auto-generated method stub
        // e1：第1个ACTION_DOWN MotionEvent，即手指所触及的位置的坐标
        // e2：最后一个ACTION_MOVE MotionEvent
        // velocityX：X轴上的移动速度（像素/秒）
        // velocityY：Y轴上的移动速度（像素/秒）

        // 向左滑
        if (e1.getX() - e2.getX() > distance
                && Math.abs(velocityX) > velocity) {
            left();
        }
        // 向右滑
        if (e2.getX() - e1.getX() > distance
                && Math.abs(velocityX) > velocity) {
            right();
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        gestureDetector.onTouchEvent(event);
        return false;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    //获得速度，
    public int getVelocity() {
        return velocity;
    }

    //设定速度
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public GestureDetector getGestureDetector() {
        return gestureDetector;
    }

    public void setGestureDetector(GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
    }
}
