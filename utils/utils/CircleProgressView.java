package com.jhjz.emr.lstd_public.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.jhjz.emr.lstd_public.R;

/**
 * Created by 时雷 2019/5/30 15:58
 */
public class CircleProgressView extends View {
    // 画实心圆的画笔
    private Paint mCirclePaint;
    // 画圆环的画笔
    private Paint mRingPaint;
    // 画圆环的画笔背景色
    private Paint mRingPaintBg;
    // 画字体的画笔
    private TextPaint mTextPaint;
    // 圆形颜色
    private int mCircleColor;
    // 圆环颜色
    private int mRingColor;
    // 圆环背景颜色
    private int mRingBgColor;
    // 半径
    private float mRadius;
    // 圆环半径
    private float mRingRadius;
    // 圆环宽度
    private float mStrokeWidth;
    // 圆心x坐标
    private int mXCenter;
    // 圆心y坐标
    private int mYCenter;
    //园中心显示文字
    private String mCircleText;
    // 字的长度
    private int mTxtWidth;
    // 字的高度
    private int mTxtHeight;
    // 文字大小
    private float mTextSize;
    // 总进度
    private int mTotalProgress;
    // 当前进度
    private int mProgress;
 
    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 获取自定义的属性
        initAttrs(context, attrs);
        initVariable();
    }
 
    //属性
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CircleProgressValue, 0, 0);
        mStrokeWidth = typeArray.getDimension(R.styleable.CircleProgressValue_strokeWidth, 10);
        mCircleColor = typeArray.getColor(R.styleable.CircleProgressValue_circleColor, 0xFFFFFFFF);
        mRingColor = typeArray.getColor(R.styleable.CircleProgressValue_ringColor, 0xFFFFFFFF);
        mRingBgColor = typeArray.getColor(R.styleable.CircleProgressValue_ringBgColor, 0xFFFFFFFF);
        mTotalProgress=typeArray.getInt(R.styleable.CircleProgressValue_totalProgress,100);
        mTextSize = typeArray.getDimension(R.styleable.CircleProgressValue_textSize, mRadius/4);
        if (!TextUtils.isEmpty(typeArray.getString(R.styleable.CircleProgressValue_circleText))){
            mCircleText=typeArray.getString(R.styleable.CircleProgressValue_circleText);
        }
 
    }
 
    //初始化画笔
    private void initVariable() {
        //内圆
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);
 
        //外圆弧背景
        mRingPaintBg = new Paint();
        mRingPaintBg.setAntiAlias(true);
        mRingPaintBg.setColor(mRingBgColor);
        mRingPaintBg.setStyle(Paint.Style.STROKE);
        mRingPaintBg.setStrokeWidth(mStrokeWidth);
 
 
        //外圆弧
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);
        //mRingPaint.setStrokeCap(Paint.Cap.ROUND);//设置线冒样式，有圆 有方
 
        //中间字
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(mRingColor);
        mTextPaint.setTextSize(mTextSize);
 
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTxtHeight = (int) Math.ceil(fm.descent - fm.ascent);
    }
 
    //画图
    @Override
    protected void onDraw(Canvas canvas) {
        mXCenter = getWidth() / 2;
        mYCenter = getHeight() / 2;
 
        if (getWidth()>getHeight()){//如果宽>高，半径=高/2-边粗
            mRadius=mYCenter-mStrokeWidth;
        }else {
            mRadius=mXCenter-mStrokeWidth;
        }
        mRingRadius = mRadius + mStrokeWidth / 2;
 
        //内圆
        canvas.drawCircle(mXCenter, mYCenter, mRadius, mCirclePaint);
 
        //外圆弧背景
        RectF oval1 = new RectF();
        oval1.left = (mXCenter - mRingRadius);
        oval1.top = (mYCenter - mRingRadius);
        oval1.right = mRingRadius * 2 + (mXCenter - mRingRadius);
        oval1.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
        canvas.drawArc(oval1, 0, 360, false, mRingPaintBg); //圆弧所在的椭圆对象、圆弧的起始角度、圆弧的角度、是否显示半径连线
 
        //外圆弧
        if (mProgress > 0 ) {
            RectF oval = new RectF();
            oval.left = (mXCenter - mRingRadius);
            oval.top = (mYCenter - mRingRadius);
            oval.right = mRingRadius * 2 + (mXCenter - mRingRadius);
            oval.bottom = mRingRadius * 2 + (mYCenter - mRingRadius);
            canvas.drawArc(oval, -90, ((float)mProgress / mTotalProgress) * 360, false, mRingPaint); //
 
            //字体
            if (!TextUtils.isEmpty(mCircleText)){
                //文字过长则换行
                StaticLayout textLayout = new StaticLayout(mCircleText,mTextPaint,mXCenter-mXCenter/4,
                        Layout.Alignment.ALIGN_CENTER, 1.0f, 0f,false);
                canvas.translate(mXCenter-mXCenter/2+mXCenter/8, mYCenter - mTxtHeight);
                textLayout.draw(canvas);
            }
        }
    }
 
    //设置进度
    public void setProgress(int progress) {
        mProgress = progress;
        postInvalidate();//重绘
    }
 
    public void setCircleText(String mCircleText) {
        this.mCircleText = mCircleText;
    }
}
