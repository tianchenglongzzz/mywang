package com.jhjz.emr.lstd_public.utils;

import android.content.Context;
import android.view.View;
import android.widget.SeekBar;

import com.jhjz.emr.lstd_public.R;

import org.greenrobot.eventbus.EventBus;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by 时雷 2019/4/30 10:35
 */
public class JzvPlayerUtils extends JZVideoPlayerStandard{
    private Context context;
    public JzvPlayerUtils(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.back) {
            backPress();
            EventBus.getDefault().post("s");
        }
//        else if(i==R.id.surface_container){
//         /*   backPress();
//            EventBus.getDefault().post("s");*/
//        }else if(i==R.id.fullscreen){
//            if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
//                EventBus.getDefault().post("s");
//            } else {
//                EventBus.getDefault().post("s");
//            }
//        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        super.onProgressChanged(seekBar, progress, fromUser);
        if(progress==100){
            backPress();
            EventBus.getDefault().post("s");

           /* TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    EventBus.getDefault().post("s");
                }
            };
            Timer timer = new Timer();
            timer.schedule(timerTask,100);*/
        }
    }
    public void startWindowFullscreen() {

    }

}
