package com.jhjz.emr.lstd_public.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Chronometer;

public class MyChronometer extends Chronometer {
    public MyChronometer(Context context) {
        super(context);
    }

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyChronometer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }
}
