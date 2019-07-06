package com.ruanmeng.utils;

import android.os.Handler;
import android.os.Message;

import com.ruanmeng.crc.CrcJiaoYan;

import static com.ruanmeng.service.SendSocketService.mBuffer;

/**
 * 作者 亢兰兰
 * 时间 2017/7/24 0024
 * 公司  郑州软盟
 */

public class Receivemessage {
    public static SendCallback callback;
    public Receivemessage(SendCallback callback) {
        this.callback = callback;
    }

    public static StringBuffer buffer = null;
    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://文本消息
                    buffer = new StringBuffer("");
                    synchronized (mBuffer) {
                        for (int i : mBuffer) {
                            if (Integer.parseInt(Integer.toHexString(i), 16) < 16) {
                                buffer.append("0" + Integer.toHexString(i));
                            } else {
                                buffer.append(Integer.toHexString(i));
                            }
                            buffer.append(' ');
                        }
                    }
                    if (CrcJiaoYan.iscrc(buffer.toString())) {
                        callback.doWork(buffer.toString());
//                        callback.doWork(DianCe.getdiance(CrcJiaoYan.replaceBlank(buffer.toString())));
                    }
                    break;
            }
        }
    };


    public  interface SendCallback {
        void doWork(String s);
    }

}
