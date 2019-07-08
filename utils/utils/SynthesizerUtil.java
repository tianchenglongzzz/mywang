package com.jhjz.emr.lstd_public.utils;

import com.iflytek.cloud.SpeechSynthesizer;
import com.jhjz.emr.lstd_public.bean.LoginBean;

/**
 * 语音播报工具类
 */
public class SynthesizerUtil {
    /**
     * 播报方法，如果患者已转归，不播报
     * @param content 需要播报的内容
     * @param patient 患者对象
     */
    public static void startSpeaking(String content,LoginBean.HzListBean patient){
        if(patient.getHZStateReason()==null||!patient.getHZStateReason().equals("患者已转归")){
            //如果患者未转归 播报语音
            if(SpeechSynthesizer.getSynthesizer()!=null){
                SpeechSynthesizer.getSynthesizer().startSpeaking(content,null);
            }
        }
    }
}
