package com.jhjz.emr.lstd_public.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.jhjz.emr.lstd_public.CzjjMap_Application;
import com.jhjz.emr.lstd_public.bean.HospitalListBean;
import com.jhjz.emr.lstd_public.bean.LTKTaskBean;
import com.jhjz.emr.lstd_public.bean.LoginBean;
import com.jhjz.emr.lstd_public.bean.PatientTime;
import com.jhjz.emr.lstd_public.bean.ZhuYuanYiSheBean;
import com.jhjz.emr.lstd_public.txvideo.SignBean;
import com.jhjz.emr.lstd_public.txvideo.TokenBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jhjz01 on 2017/11/10.
 * sp工具类，用来储存一些全局的信息
 */

public class SpUtil {

    /**
     * 保存登录信息
     * @paraminfo
     */
    public static void saveLoginInfo(LoginBean loginBean) {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("loginInfo",new Gson().toJson(loginBean)).apply();
    }

    /**
     * 获取登录信息
     * @return
     */
    public static LoginBean getLoginInfo() {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("loginInfo", "");
        return new Gson().fromJson(string,LoginBean.class);
    }

    /**
     * 清除登录信息
     */
    public static void cleanLoginInfo(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().clear().apply();
    }
    /**
     * 保存全局操作的患者状态
     * @param bean
     */
    public static void savePatientState(PatientTime bean){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("patient_state", new Gson().toJson(bean)).commit();
    }

    /**
     * 获取全局操作的患者状态
     * @param
     */
    public static PatientTime getPatientState(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("patient_state", "");
        return new Gson().fromJson(string,PatientTime.class);
    }

    /**
     * 保存全局操作的患者
     * @param bean
     */
    public static void savePatient(LoginBean.HzListBean bean){
        if(bean!=null){
            CzjjMap_Application.HZID=bean.getHZID();
            SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
            sp.edit().putString("patient", new Gson().toJson(bean)).commit();
        }else{
            SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
            sp.edit().putString("patient", null).commit();
        }
    }

    /**
     * 获取全局操作的患者
     * @param
     */
    public static LoginBean.HzListBean getPatient(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("patient", "");
        return new Gson().fromJson(string,LoginBean.HzListBean.class);
    }
    /**
     * 保存是否对接住院信息
     * @param
     */
    public static void saveDuiJieZhuYuan(boolean shouFei){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        LogUtil.d("hwz","存入对接"+shouFei);
        sp.edit().putBoolean("IsDuiJie",shouFei).apply();
    }

    /**
     * 获取是否对接住院信息
     * @param
     */
    public static boolean getDuiJieZhuYuan(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("IsDuiJie", false);
        LogUtil.d("hwz","取出对接"+b);
        return b;
    }
    /**
     * 保存评分模板MD5
     * @param md5
     */
    public static void saveMd5(String md5){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("md5",md5).apply();
    }

    /**
     * 获取评分模板MD5
     * @param
     */
    public static String getMd5(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("md5", "");
        return string;
    }
    /**
     * 保存配置IP
     * @param md5
     */
    public static void saveIP(String md5){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("IP",md5).apply();
    }

    /**
     * 获取配置IP
     * @param
     */
    public static String getIP(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("IP", "");
        return string;
    }
    /**
     * 获取配置MQTTIP
     * @param md5
     */
    public static void saveMQTTIP(String md5){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("MQTTIP",md5).apply();
    }
    /**
     * 获取配置MQTTIP
     * @param
     */
    public static String getMQTTIP(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("MQTTIP", "");
        return string;
    }

    /**
     * 保存登录用户
     * @param userBean
     */
    public static  void saveUserInfo(LoginBean.UserBean userBean){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("user",new Gson().toJson(userBean)).apply();
    }
    /**
     * 获取登录信息
     * @return
     */
    public static LoginBean.UserBean getUserInfo() {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("user", "");
        return new Gson().fromJson(string,LoginBean.UserBean.class);
    }

    /**
     * 保存当前程序版本号
     */
    public static void saveVersion(Context context) throws Exception {
        PackageManager packageManager = context.getPackageManager();	//获取packagemanager的实例
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);

        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("Version",packInfo.versionName).apply();
    }

    /**
     * 获取当前程序版本号
     */
    public static String getVersion(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("Version", "");
        return string;
    }

    /**
     * 保存绿通卡状态列表
     */
    public static void saveLTKState(int stateCount){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putInt("ltkState",stateCount).apply();
    }

    /**
     * 获取绿通卡状态列表
     */
    public static int getLTKState(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        int count = sp.getInt("ltkState", Integer.parseInt("-1"));
        return count;
    }

    /**
     * 保存绿通卡列表个数
     */
    public static void saveLTKCount(int stateCount){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putInt("ltkCount",stateCount).apply();
    }

    /**
     * 获取绿通卡列表个数
     */
    public static int getLTKCount(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        int count = sp.getInt("ltkCount", Integer.parseInt("-1"));
        return count;
    }

    /**
     * 保存绿通卡绑定患者id
     */
    public static void saveLTKHzid(String hzid){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("ltkHzid",hzid).apply();
    }

    /**
     * 获取绿通卡患者id
     */
    public static String getLTKHzid(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String hzid = sp.getString("ltkHzid", "");
        return hzid;
    }

    /**
     * 保存绿通卡执行医生
     * @param userBean
     */
    public static  void saveOperateDoctor (LoginBean.UserBean userBean){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("operateDoctor",new Gson().toJson(userBean)).apply();
    }
    /**
     * 获取绿通卡执行医生
     * @return
     */
    public static LoginBean.UserBean getOperateDoctor() {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("operateDoctor", "");
        return new Gson().fromJson(string,LoginBean.UserBean.class);
    }

    /**
     * 保存选择送往的医院名称
     */
    public static void saveHospitalName(String hospitalName){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("hospitalName",hospitalName).apply();
    }

    /**
     * 获取选择送往的医院名称
     * @return 医院名称
     */
    public static String getHospitalName(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String hospitalName=sp.getString("hospitalName","");
        return hospitalName;
    }
    /**
     * 保存选择送往的医院
     */
    public static void saveHospital(HospitalListBean.HospitalBean hospitalBean){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("hospital",new Gson().toJson(hospitalBean)).commit();
    }
    /**
     * 获取保存的医院
     */
    public static HospitalListBean.HospitalBean getHospital(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String s=sp.getString("hospital","");
        return new Gson().fromJson(s,HospitalListBean.HospitalBean.class);
    }
    /**
     * 保存收费类型
     * @param
     */
    public static void saveShoufei(String shouFei){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("IsShouFei",shouFei).apply();
    }

    /**
     * 获取收费类型
     * @param
     */
    public static String getShoufei(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("IsShouFei", "");
        return string;
    }
    /**
     * 保存全局操作的患者状态
     * @param bean
     */
    public static void setGreenState(LTKTaskBean bean){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("ltk_state", new Gson().toJson(bean)).commit();
    }

    /**
     * 获取全局操作的患者状态
     * @param
     */
    public static LTKTaskBean getGreenState(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("ltk_state", "");
        return new Gson().fromJson(string,LTKTaskBean.class);
    }
    /**
     * 保存绿通卡全局操作的患者
     * @param bean
     */
    public static void saveLTKPatient(LTKTaskBean bean){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("ltkpatient", new Gson().toJson(bean)).commit();
    }

    /**
     * 获取绿通卡全局操作的患者
     * @param
     */
    public static LTKTaskBean getLTKPatient(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("ltkpatient", "");
        return new Gson().fromJson(string,LTKTaskBean.class);
    }


    /**
     * 保存是否是演练模式标识
     */

    public static void saveIsYanLian(boolean b){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putBoolean("isYanLian",b).commit();
    }

    /**
     * 获取是否是演练模式
     */
    public static boolean getIsYanLian(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        boolean b=sp.getBoolean("isYanLian",false);
        return b;
    }
    /**
     * 保存住院医生信息
     */
    public static void putZhuYuanYiSheBean(ZhuYuanYiSheBean zhuYuanYiSheBean){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
       sp.edit().putString("zhuyuanyishe",new Gson().toJson(zhuYuanYiSheBean)).commit();
    }
    /**
     * 获取住院医生信息
     */
    public static ZhuYuanYiSheBean getZhuYuanYiSheBean(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String zhuyuanyishe = sp.getString("zhuyuanyishe", "");
        return new Gson().fromJson(zhuyuanyishe,ZhuYuanYiSheBean.class);
    }

    /**
     * 保存用户登录密码
     */
    public static void savePassWord(String passWord){
        SharedPreferences sp=CzjjMap_Application.getInstance().getSharedPreferences("czMap",Context.MODE_PRIVATE);
        sp.edit().putString("passWord",passWord).commit();
    }
    /**
     * 获取用户登录密码
     */
    public static String getPassWord(){
        SharedPreferences sp=CzjjMap_Application.getInstance().getSharedPreferences("czMap",Context.MODE_PRIVATE);
        return sp.getString("passWord","");
    }

    /**
     * 保存是否开启语音播报
     */
    public static void saveIsOpenYuYin(boolean isOpen){
        SharedPreferences sp=CzjjMap_Application.getInstance().getSharedPreferences("czMap",Context.MODE_PRIVATE);
        sp.edit().putBoolean("isOpenYuYin",isOpen).commit();
    }
    /**
     * 获取是否开启语音播报
     */
    public static boolean getIsOpenYuYin(){
        SharedPreferences sp=CzjjMap_Application.getInstance().getSharedPreferences("czMap",Context.MODE_PRIVATE);
        return sp.getBoolean("isOpenYuYin",false);
    }
    /**
     * IM保存登录TOKEN
     * @paraminfo
     */
    public static void saveIMToken(String imToken) {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("imToken",imToken).apply();
    }

    /**
     * IM获取登录TOKEN
     * @return
     */
    public static String getIMToken() {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("imToken", "");
        return string;
    }
    /**
     * 保存胸痛患者
     * @paraminfo
     */
    public static void saveXTList(String xtlist) {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("xtlist",xtlist).apply();
    }

    /**
     * 获取胸痛患者
     * @return
     */
    public static List<LoginBean.HzListBean> getXTList() {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("xtlist", "");
        return JSON.parseArray(string, LoginBean.HzListBean.class);
    }
    /**
     * 保存卒中患者
     * @paraminfo
     */
    public static void saveCZList(String xtlist) {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("czlist",xtlist).apply();
    }

    /**
     * 获取卒中患者
     * @return
     */
    public static List<LoginBean.HzListBean> getCZList() {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("czlist", "");
        return JSON.parseArray(string, LoginBean.HzListBean.class);
    }
    /**
     * 保存token
     * @paraminfo
     */
    public static void saveToken(TokenBean tokenBean) {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("token",new Gson().toJson(tokenBean)).apply();
    }

    /**
     * 获取token
     * @return
     */
    public static TokenBean getToken() {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("token", "");
        return new Gson().fromJson(string,TokenBean.class);
    }
    /**
     * 保存签到信息
     * @paraminfo
     */
    public static void saveSignInfo(SignBean.QianDaoBean qianDaoBean) {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putString("sign_info",new Gson().toJson(qianDaoBean)).apply();
    }

    /**
     * 获取签到信息
     * @return
     */
    public static SignBean.QianDaoBean getSignInfo() {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        String string = sp.getString("sign_info", "");
        return new Gson().fromJson(string,SignBean.QianDaoBean.class);
    }

    /**
     * 保存服务器时间差
     * @paraminfo
     */
    public static void saveTimeDiff(long time) {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putLong("time_diff",time).apply();
    }
    /**
     * 获取时间差
     * @return
     */
    public static long getTimeDiff() {
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        return sp.getLong("time_diff", 0);
    }
    /**
     * 保存时间采集点列表
     */
    public static void saveTimeList(List list){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        sp.edit().putStringSet("timeList",  new HashSet(list)).apply();
    }

    /**
     * 获取时间采集点列表
     */
    public static List<String>  getTimeList(){
        SharedPreferences sp = CzjjMap_Application.getInstance().getSharedPreferences("czMap", Context.MODE_PRIVATE);
        Set set = sp.getStringSet("timeList",new HashSet<String>());
        List<String> result = new ArrayList<>(set);
        return result;
    }

}
