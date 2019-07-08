package com.jhjz.emr.lstd_public.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 发送验证码倒计时工具类
 * http://blog.csdn.net/qq_16965811
 * Created by ypt on 2017/12/22.
 */
public  class SmsTimeUtils {

    /*倒计时时长  单位：秒*/
    private final static int COUNT = 120;
    /*当前做*/
    private static int CURR_COUNT = 0;
    /*设置提现账户  标识
      发送验证码*/
    public final static int SETTING_FINANCE_ACCOUNT_TIME = 1;
    public final static int REGISTER_TIME = 2;
    public final static int FORGET_PASSWORD_TIME = 3;
    public final static int WITHDRAW_CASH = 4;

    /*设置提现账户
      预计结束的时间*/
    private static long SETTING_FINANCE_ACCOUNT_TIME_END = 0;
    /*注册*/
    private static long REGISTER_TIME_END = 0;
    /*忘记密码*/
    private static long FORGET_PASSWORD_TIME_END = 0;
    /*提现*/
    private static long WITHDRAW_CASH_END = 0;


    private static Timer countdownTimer;
    private static TextView tvSendCode;

    /**
     * 检查是否超过60秒
     * 给当前要从多少开始倒数赋值
     *http://blog.csdn.net/qq_16965811
     * @param type  1，设置提现账户 2，注册3，忘记密码
     * @param first true 表示第一次   false不是
     * @return 是否需要调用startCountdown(TextView textView)，主要用于判断在重新打开页，需不需要继续倒计时
     */
    public static boolean check(int type, boolean first) {
        long data = System.currentTimeMillis();
        long time = 0;
        switch (type) {
            case SETTING_FINANCE_ACCOUNT_TIME:
                time = SETTING_FINANCE_ACCOUNT_TIME_END;
                break;
            case WITHDRAW_CASH:
                time = WITHDRAW_CASH_END;
                break;
        }
        if (data > time) {
            /*主要是区别于是否是第一次进入。第一次进入不需要赋值*/
            if (!first) {
                CURR_COUNT = COUNT;
                time = data + COUNT * 1000;
                switch (type) {
                    case SETTING_FINANCE_ACCOUNT_TIME:
                        SETTING_FINANCE_ACCOUNT_TIME_END = time;
                        break;
                    case WITHDRAW_CASH:
                        WITHDRAW_CASH_END = time;
                        break;
                }
            }
            return false;
        } else {
            int the_difference = ((int) (time - data)) / 1000;
            CURR_COUNT = the_difference;
            //DebugUtil.error("the_difference-----" + the_difference);
            return true;
        }
    }

    /**
     * 开始倒计时
     *http://blog.csdn.net/qq_16965811
     * @param textView 控制倒计时的view
     */
    public static void startCountdown(TextView textView) {
        tvSendCode = textView;
        if (countdownTimer == null) {
            countdownTimer = new Timer();
            countdownTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = CURR_COUNT--;
                    handler.sendMessage(msg);
                }
            }, 1000, 1000);
        }
    }


    @SuppressLint("HandlerLeak")
    private static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                if (countdownTimer != null) {
                    countdownTimer.cancel();
                    countdownTimer = null;
                }
                if(flagListener!=null){
                    flagListener.isno();
                }
                tvSendCode.setText("获取验证码");
                tvSendCode.setEnabled(true);
            } else {
                tvSendCode.setText(msg.what + "s");
                tvSendCode.setEnabled(false);
            }
            super.handleMessage(msg);
        }
    };
   private static FlagListener flagListener;
    public  void getFlag(FlagListener flagListener){
        this.flagListener=flagListener;
    }
    public interface FlagListener{
        void isno();
    }

}