package com.jhjz.emr.lstd_public.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jhjz.emr.lstd_public.R;

/**
 * Created by jhjz01 on 2017/12/1.
 */

public class CustomCallView extends View {
    public CustomCallView(Context context) {
        super(context);
    }

    public CustomCallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Paint paint=new Paint();
        paint.setColor(getResources().getColor(R.color.main_color));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public CustomCallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawOval();
    }
}
