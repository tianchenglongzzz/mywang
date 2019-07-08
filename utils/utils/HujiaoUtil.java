package com.jhjz.emr.lstd_public.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jhjz.emr.lstd_public.base.BaseActivity;
import com.jhjz.emr.lstd_public.bean.LoginBean;
import com.jhjz.emr.lstd_public.bean.PatientTime;
import com.jhjz.emr.lstd_public.bean.XiaoXi;
import com.jhjz.emr.lstd_public.callback.StringDialogCallback;
import com.jhjz.emr.lstd_public.config.UrlConfig;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

public class HujiaoUtil {
    private BaseActivity activity;
    private String code;
    private String qunming;// 群组名
    private String changjing;// 场景
    private String leibie;
    private LoginBean.HzListBean hzListBean;
    private PatientTime patientTime;
    private LoginBean.UserBean userBean;
    public HujiaoUtil(BaseActivity activitys, String codes, String leibies,String jieshouren) {
        this.activity = activitys;
        this.code = codes;
        this.leibie = leibies;
        hzListBean = SpUtil.getPatient();
        userBean = SpUtil.getUserInfo();
        patientTime=SpUtil.getPatientState();
        XiaoXi xiaoxi = new XiaoXi();
        xiaoxi.setHZID(hzListBean.getHZID() + "");
        xiaoxi.setLeiXing("通知");
        xiaoxi.setFaSongRen(userBean.getUNickName());
        xiaoxi.setUUCode(userBean.getUUCode());
        if (leibie.equals("呼叫团队")) {
            xiaoxi.setJieShouRen(jieshouren);

            String titleStr = !TextUtils.isEmpty(hzListBean.getHZStr13()) ? hzListBean.getHZStr13() : "卒中";
            if (userBean.getUGCode().equals("急诊分诊")) {
                xiaoxi.setNeiRong("患者" + hzListBean.getXingMing() +  (hzListBean.getXingBie()==""?"":"("+hzListBean.getXingBie()+")") +
                        "疑似"+ titleStr +",请立即到达" + codes);
            }else  if (userBean.getUGCode().equals("急救车医生")) {
                xiaoxi.setNeiRong(codes);
            }  else {
                String hzStr12 = patientTime.getChuBuZhenDuan();//初步诊断结果
                if (hzStr12 == null || hzStr12 == "" || TextUtils.isEmpty(hzStr12)) {
                    xiaoxi.setNeiRong("患者" + hzListBean.getXingMing() + (hzListBean.getXingBie()==""?"":"("+hzListBean.getXingBie()+")") + "初步诊断暂未填写,请立即到达" + codes);
                } else {
                    xiaoxi.setNeiRong("患者" +  hzListBean.getXingMing() + (hzListBean.getXingBie()==""?"":"("+hzListBean.getXingBie()+")" )+ "初步诊断为" + hzStr12 + ",请立即到达" + codes);
                }
            }
        }
        OkGo.<String>post(UrlConfig.ADD_MESSAGE)
                .params("entity_",JSON.toJSONString(xiaoxi))
                .execute(new StringDialogCallback(activity,true) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtil.d("hwz","消息添加成功"+response.body());
                        String body = response.body();
                        JSONObject jsonObject = JSON.parseObject(body);
                        int err = jsonObject.getIntValue("Err");
                        if (err==0){
                            ToastUtil.show(activity,"呼叫成功", Toast.LENGTH_SHORT);
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        LogUtil.d("hwz","消息添加失败"+response.message());
                    }
                });
    }

    }


