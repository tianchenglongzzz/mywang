package com.jhjz.emr.lstd_public.utils;

import android.text.TextUtils;

import com.jhjz.emr.lstd_public.bean.HzState;
import com.jhjz.emr.lstd_public.bean.LTKTaskBean;
import com.jhjz.emr.lstd_public.bean.LoginBean;
import com.jhjz.emr.lstd_public.bean.TaskListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hopc on 2017/11/15.
 */

public class HZStateUtils {
    /**
     *赋值key
     * @param names
     *
     * @return
     */
    public static List<HzState>  getList(String[] names){
        List<HzState> list=new ArrayList<>();
        if(names!=null){
            LoginBean.HzListBean patient = SpUtil.getPatient();
            for (int i = 0; i < names.length; i++) {
            HzState hzState=new HzState();
            hzState.setKey(names[i]);
            if (names[i].equals("LINE")){
                hzState.setType(0);
            }else{
                hzState.setType(1);
            }
            hzState.setValue("未完成");

            if (patient!=null){
                if(names[i].equals("来院方式")&&!patient.getHZStr6().equals("")){
                    hzState.setValue(patient.getHZStr6());
                }else{
                    hzState.setValue("未完成");
                }
                if(names[i].equals("绑定腕带")){
                    if(!patient.getWanDai().equals("")){
                        hzState.setValue(patient.getWanDai());
                    }else{
                        hzState.setValue("未绑定");
                    }
                }
                if (names[i].equals("分检诊结果")){
                    String type = !TextUtils.isEmpty(patient.getHZStr13()) ? patient.getHZStr13() : "卒中";
                    if (type.equals("胸痛")){
                        hzState.setKey("初步决策");
                    }
                }
            }
            list.add(hzState);
        }
        }
        return list;
    }
    /**
     *赋值key
     * @param names
     *
     * @return
     */
    public static List<HzState>  getList(String[] names,ArrayList<TaskListBean> arraylist){
        List<HzState> list=new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            HzState hzState=new HzState();
            hzState.setKey(names[i]);
            hzState.setValue("未完成");
            list.add(hzState);
        }
        return list;
    }

    /**
     * 赋值value
     * @param list 本地
     * @param stateList 网络
     * @return
     */
    public  static List<HzState>  getStateList(List<HzState> list,List<HzState> stateList){
        List<HzState> sList=new ArrayList<>();
        for (int i = 0; i < stateList.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (stateList.get(i).getKey().equals(list.get(j).getKey())){
                    if (!stateList.get(i).getKey().equals("治疗后NIHSS评分")){
                        list.get(j).setValue(stateList.get(i).getValue());
                    }else{
                        list.get(j).setValue("未完成");
                    }
                }
//                if (list.get(j).getKey().equals("诊断")&&stateList.get(i).getKey().equals("初步诊断")){
//                    list.get(j).setValue(stateList.get(i).getValue());
//                }
//                if (list.get(j).getKey().equals("初步诊断")&&stateList.get(i).getKey().equals("初步诊断2")){
//                    list.get(j).setValue(stateList.get(i).getValue());
//                }
                if ((list.get(j).getKey().equals("初步决策")||list.get(j).getKey().equals("治疗决策")||(list.get(j).getKey().equals("分检诊结果")))&&(stateList.get(i).getKey().equals("治疗决策")||stateList.get(i).getKey().equals("初步决策"))){
                    list.get(j).setValue(stateList.get(i).getValue());
                }
            }
//            if(stateList.get(i).getKey().equals("来院方式")){
//
//                if(!stateList.get(i).getValue().equals("本院急救车")){
//                    for(int k=0;k<list.size();k++){
//                        if(list.get(k).getKey().equals("院前处置")){
//                            list.get(k).setKey("院内处置");
//                        }
//                    }
//                }
//            }

        }
        sList.addAll(list);
        return sList;
    }
    /**
     * 赋值value
     * @param list
     * @param stateList
     * @return
     */
    public  static List<LTKTaskBean.NewLTKMoKuaiBean.TmListBean>  getLTKStateList(List<LTKTaskBean.NewLTKMoKuaiBean.TmListBean> list,List<LTKTaskBean.NewLTKMoKuaiBean.TmListBean> stateList){
        List<LTKTaskBean.NewLTKMoKuaiBean.TmListBean> sList=new ArrayList<>();
        for (int i = 0; i < stateList.size(); i++) {

            for (int j = 0; j < list.size(); j++) {

                if (stateList.get(i).getKey().equals(list.get(j).getKey())){
                    list.get(j).setValue(stateList.get(i).getValue());
                }
            }
        }
        sList.addAll(list);
        return sList;
    }
}
