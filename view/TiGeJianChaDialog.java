package com.jhjz.emr.lstd_public.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jhjz.emr.lstd_public.R;
import com.jhjz.emr.lstd_public.bean.TagBean;
import com.jhjz.emr.lstd_public.bean.TigeBeen;
import com.jhjz.emr.lstd_public.utils.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by jhjz01 on 2018/12/18.
 */

public class TiGeJianChaDialog {
    //private AlertDialog alertDialog;
    String leixing="";
    private RadioGroup rg_leixing;
    private EditText et_qita;
    private List<TagBean> list = new ArrayList<>();
    private String tg_yishi_select;//已选择的数据
    private TagAdapter<String> tg_yishiAdapter;



    public interface ShowLvListener {
        void add1(String data1, String time,String leixing);
        void add2(String data1, String data2, String time,String leixing);
        void add3(EditText edittext);
    }

    public interface ShowLvListener1 {
        void add(TigeBeen tigeBeen);
    }
    public interface YishiListener{
        void add(String value);
    }
    public TiGeJianChaDialog(final Activity activity,final YishiListener listener,String zhi){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
        String shijian = df.format(new Date());// new Date()为获取当前系统时间
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        View view = View.inflate(activity, R.layout.dialog_tigejiancha_yishi, null);
        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();
        final TagFlowLayout tf_yishi=view.findViewById(R.id.tf_yishi);
        TextView tianjia=(TextView)view.findViewById(R.id.dialog_tige_tianjia);

        TextView dialog_tige_cancel=(TextView)view.findViewById(R.id.dialog_tige_cancel);
        final EditText et3=(EditText)view.findViewById(R.id.dialog_tige_item3_et);
        et3.setText(shijian);
        et3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(activity, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()), "yyyy-MM-dd HH:mm");
                dateTimePicKDialog.dateTimePicKDialogListener(et3, new DateTimePickDialogUtil.ConfirmListerer() {

                    @Override
                    public void timeConfirmListener(String selectedTime) {

                    }
                });
            }
        });
        String[] data_yishi;//意识数据源
        data_yishi=activity.getResources().getStringArray(R.array.tg_yishi);
        tg_yishiAdapter=new TagAdapter<String>(data_yishi) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = activity.getLayoutInflater().inflate(R.layout.tv,
                        tf_yishi, false);
                TextView tv = view.findViewById(R.id.tv);
                tv.setText(s);
                return view;
            }
            @Override
            public void onSelected(int position, View view) {
                super.onSelected(position, view);
                view.setBackgroundResource(0);
                TextView textView = view.findViewById(R.id.tv);
                textView.setTextColor(activity.getResources().getColor(R.color.white));
                textView.setBackground(activity.getResources().getDrawable(R.drawable.shape_tv_select));
                TagBean tagBean = new TagBean();
                tagBean.setPosition(position);
                tagBean.setTextView(textView);
                tg_yishi_select=textView.getText().toString().trim();
//                if ("其他".equals(textView.getText().toString().trim())) {
//                    et_medication_history.setVisibility(View.VISIBLE);
//                    mHandler.sendEmptyMessage(998);
//                }
//                if ("无".equals(textView.getText().toString().trim())) {
//                    for (int i = 0; i < list2.size(); i++) {
//                        TagBean bean = list2.get(i);
//                        if (!bean.getTextView().getText().toString().trim().equals("无")) {
//                            adapter_xinlv.unSelected(bean.getPosition(), bean.getTextView());
//                        }
//                    }
//                    list2.clear();
//                    list2.add(tagBean);
////                    adapter1.notifyDataChanged();
//                } else {
                list.add(tagBean);
                for (int i = 0; i < list.size(); i++) {
                    TagBean bean = list.get(i);
                    if (bean.getTextView().getText().toString().trim().equals("无")) {
                        tg_yishiAdapter.unSelected(bean.getPosition(), bean.getTextView());
                    }
//                    }
                }
                TagView tagView = (TagView) tf_yishi.getChildAt(position);
                tagView.setChecked(true);
            }

            @Override
            public void unSelected(int position, View view) {
                super.unSelected(position, view);
                LogUtil.d("app", "unSelected" + position);
                TextView textView = view.findViewById(R.id.tv);
                textView.setBackgroundResource(0);
                textView.setTextColor(activity.getResources().getColor(R.color.hui));
                textView.setBackground(activity.getResources().getDrawable(R.drawable.shape_tv));
                tg_yishi_select="";
//                LogUtil.d("hwz", "移除" + textView.getText().toString().trim() + "|" + medication_historys_temporary.remove(textView.getText().toString().trim()));
//                LogUtil.d("hwz", "数据长度" + tg_yishi_select.size());
//                if ("其他".equals(textView.getText().toString().trim())) {
//                    et_medication_history.setText("");
//                    et_medication_history.setVisibility(View.GONE);
//                }
                TagView tagView = (TagView) tf_yishi.getChildAt(position);
                tagView.setChecked(false);
            }
        };
        tf_yishi.setMaxSelectCount(1);
        tf_yishi.setAdapter(tg_yishiAdapter);

        et_qita=view.findViewById(R.id.et_qita);

        rg_leixing=view.findViewById(R.id.rg_leixing);
//        switch(leixing_res){
//            case "分诊":
//                rg_leixing.check(R.id.rb_fenzhen);
//                leixing="分诊";
//                et_qita.setVisibility(View.GONE);
//                break;
//            case "溶栓前":
//                rg_leixing.check(R.id.rb_rongshuanqian);
//                leixing="溶栓前";
//                et_qita.setVisibility(View.GONE);
//                break;
//            case "介入前":
//                rg_leixing.check(R.id.rb_jieruqian);
//                leixing="介入前";
//                et_qita.setVisibility(View.GONE);
//                break;
//            case "其他":
//                rg_leixing.check(R.id.rb_qita);
//                leixing="其他";
//                et_qita.setVisibility(View.VISIBLE);
//                et_qita.setText(leixing_res);
//                break;
//            case "":
//                break;
//            default:
//                rg_leixing.check(R.id.rb_qita);
//                leixing="其他";
//                et_qita.setVisibility(View.VISIBLE);
//                et_qita.setText(leixing_res.replace("其他:",""));
//                break;
//        }
//        rg_leixing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                switch(checkedId){
//                    case R.id.rb_fenzhen:
//                        leixing="分诊";
//                        et_qita.setVisibility(View.GONE);
//                        break;
//                    case R.id.rb_rongshuanqian:
//                        leixing="溶栓前";
//                        et_qita.setVisibility(View.GONE);
//                        break;
//                    case R.id.rb_jieruqian:
//                        leixing="介入前";
//                        et_qita.setVisibility(View.GONE);
//                        break;
//                    case R.id.rb_qita:
//                        leixing="其他";
//                        et_qita.setVisibility(View.VISIBLE);
//                        break;
//                }
//            }
//        });
        tianjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tg_yishi_select.equals("")||tg_yishi_select==null){
                    ToastUtil.show(activity,"请输入相关值", Toast.LENGTH_SHORT);
                    return;
                }else{
                    listener.add(tg_yishi_select);
                    dialog.dismiss();
                }
            }
        });
        dialog_tige_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if(zhi!=null&&!zhi.equals("")){
            int position = 0;
            for(int i=0;i<tg_yishiAdapter.getCount();i++){
                if(zhi.equals(tg_yishiAdapter.getItem(i))){
                    position=i;
                }
            }
            Set<Integer> set=new HashSet<>();
            set.add(position);
            tg_yishiAdapter.setSelectedList(set);
            tianjia.setText("修改");
        }
//        if(time!=null&&!time.equals("")){
//            et3.setText(time);
//        }
    }
    /**
     *
     * @param zhi  三个edittext上填的的内容
     */
    public TiGeJianChaDialog(List<Map<String, String>> list, Activity adtivity, TiGeJianChaDialog.ShowLvListener showLvListener, boolean cancelAble,
                                 String zhi,String leixing) {
        Map<String,String> map=new HashMap<>();
        if(zhi.equals("")){
            map.put("zhong1", "");
            map.put("zhong2", "");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
            String shijian = df.format(new Date());// new Date()为获取当前系统时间
            map.put("Shijian", shijian);
        }else{
            String[] temp=zhi.split("/");
            if(temp.length==2){
                map.put("zhong1", temp[0]);
                map.put("zhong2", temp[1]);
            }else{
                map.put("zhong1", zhi);
            }

//			map.put("Shijian", temp[0]);
        }
        showInfo(list,adtivity, showLvListener, cancelAble,map,leixing);
    }
    /**
     *
     * @param
     */
    public TiGeJianChaDialog(List<Map<String, String>> list, Activity adtivity, TiGeJianChaDialog.ShowLvListener1 showLvListener, boolean cancelAble,
                                 TigeBeen tigeBeen) {
        boolean isNew = false;
        Map<String,String> map=new HashMap<>();
        if(tigeBeen==null){
            isNew=true;
            map.put("zhong1", "");
            map.put("zhong2", "");
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 设置日期格式
            String shijian = df.format(new Date());// new Date()为获取当前系统时间
            map.put("Shijian", shijian);
        }else{
            String ttZhi=tigeBeen.getTZZhi();
            if(ttZhi!=null&&!ttZhi.isEmpty()){
                if(ttZhi.contains("/")){
                    String[] split = ttZhi.split("/");
                    map.put("zhong1",split[0]);
                    map.put("zhong2",split[1]);
                }else{
                    map.put("zhong1",tigeBeen.getTZZhi());
                    map.put("zhong2",tigeBeen.getTZZhi());
                }
                isNew=false;
            }else{
                isNew=true;
                map.put("zhong1",tigeBeen.getTZZhi());
                map.put("zhong2",tigeBeen.getTZZhi());
            }

            map.put("Shijian", tigeBeen.getTZZStr1());
        }
        showInfos(list,adtivity, showLvListener, cancelAble,map,tigeBeen,isNew);
    }
    private void showInfos(List<Map<String, String>> list, final Activity adtivity, final TiGeJianChaDialog.ShowLvListener1 showLvListener, boolean cancelAble,
                           Map<String,String> zhi, final TigeBeen tigeBeen, boolean isNew) {
        AlertDialog.Builder builder=new AlertDialog.Builder(adtivity);
        View view = View.inflate(adtivity, R.layout.dialog_tigejiancha, null);
        builder.setView(view);
        builder.setCancelable(cancelAble);
        final AlertDialog dialog = builder.create();
        dialog.show();
        LinearLayout layout1=(LinearLayout)view.findViewById(R.id.dialog_tige_item1);
        LinearLayout layout2=(LinearLayout)view.findViewById(R.id.dialog_tige_item2);
        TextView item1Tv1=(TextView)view.findViewById(R.id.dialog_tige_item1_tv1);
        TextView item1Tv2=(TextView)view.findViewById(R.id.dialog_tige_item1_tv2);
        TextView item2Tv1=(TextView)view.findViewById(R.id.dialog_tige_item2_tv1);
        TextView item2Tv2=(TextView)view.findViewById(R.id.dialog_tige_item2_tv2);
        final EditText et1=(EditText)view.findViewById(R.id.dialog_tige_item1_et);
        final EditText et2=(EditText)view.findViewById(R.id.dialog_tige_item2_et);
        final EditText et3=(EditText)view.findViewById(R.id.dialog_tige_item3_et);

        View vieww=view.findViewById(R.id.dialog_tige_view);
        final TextView tianjia=(TextView)view.findViewById(R.id.dialog_tige_tianjia);
        TextView dialog_tige_cancel=(TextView)view.findViewById(R.id.dialog_tige_cancel);
        if(!isNew){
            tianjia.setText("修改");
        }
        if(list.size()==1){
            layout2.setVisibility(View.GONE);
//			vieww.setVisibility(View.GONE);
        }else{
            item2Tv1.setText(list.get(1).get("zuo"));
            item2Tv2.setText(list.get(1).get("you"));
        }
        item1Tv1.setText(list.get(0).get("zuo"));
        item1Tv2.setText(list.get(0).get("you"));
        et1.setText(zhi.get("zhong1"));
        et2.setText(zhi.get("zhong2"));
        if (zhi.get("Shijian")!=null&&!TextUtils.isEmpty(zhi.get("Shijian"))){
            et3.setText(zhi.get("Shijian"));
        }else{
            et3.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        }
        final List<Map<String, String>> listt=list;
        tianjia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(listt.size()==1){
                    if (et1.getText().toString()==null||TextUtils.isEmpty(et1.getText().toString())){
                        ToastUtil.show(adtivity,"请输入相关值", Toast.LENGTH_SHORT);
                        return;
                    }
                    if (tigeBeen.getTZCode().equals("血压")){
                        if (Double.parseDouble(et1.getText().toString()) - 300 > 0 || Double.parseDouble(et1.getText().toString()) - 300 > 0) {
                            Utilss.showToastShort(adtivity, "血压超出范围");
                            return;
                        }
                    }else if (tigeBeen.getTZCode().equals("体温")){
                        if (Double.parseDouble(et1.getText().toString()) - 300 > 0 || Double.parseDouble(et1.getText().toString()) - 300 > 0) {
                            Utilss.showToastShort(adtivity, "血压超出范围");
                            return;
                        }
                    }
                    tigeBeen.setTZZStr1(et3.getText().toString());
                    tigeBeen.setTZZhi(et1.getText().toString());

//					showLvListener.add1(et1.getText().toString(),et3.getText().toString());
                }else{
                    if (et1.getText().toString()==null||TextUtils.isEmpty(et1.getText().toString())){
                        ToastUtil.show(adtivity,"请输入相关值", Toast.LENGTH_SHORT);
                        return;
                    }
                    if (et2.getText().toString()==null||TextUtils.isEmpty(et2.getText().toString())){
                        ToastUtil.show(adtivity,"请输入相关值", Toast.LENGTH_SHORT);
                        return;
                    }
                    tigeBeen.setTZZStr1(et3.getText().toString());
                    tigeBeen.setTZZhi(et1.getText().toString()+"/"+et2.getText().toString());
//					showLvListener.add2(et1.getText().toString(),et2.getText().toString(),et3.getText().toString());
                }
                showLvListener.add(tigeBeen);
                dialog.dismiss();
            }
        });
        dialog_tige_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
//		et3.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(adtivity, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()), "yyyy-MM-dd HH:mm");
//				dateTimePicKDialog.dateTimePicKDialogListener(et3, new DateTimePickDialogUtil.ConfirmListerer() {
//
//					@Override
//					public void timeConfirmListener() {
//
//					}
//				});
//			}
//		});
    }
    private void showInfo(List<Map<String, String>> list, final Activity adtivity, final TiGeJianChaDialog.ShowLvListener showLvListener, boolean cancelAble,
                          Map<String,String> zhi,String leixing_res) {
        AlertDialog.Builder builder=new AlertDialog.Builder(adtivity);
        View view = View.inflate(adtivity, R.layout.dialog_tigejiancha, null);
        builder.setView(view);
        builder.setCancelable(cancelAble);
        final AlertDialog dialog = builder.create();

        dialog.show();
        LinearLayout layout1=(LinearLayout)view.findViewById(R.id.dialog_tige_item1);
        LinearLayout layout2=(LinearLayout)view.findViewById(R.id.dialog_tige_item2);
        TextView item1Tv1=(TextView)view.findViewById(R.id.dialog_tige_item1_tv1);
        TextView item1Tv2=(TextView)view.findViewById(R.id.dialog_tige_item1_tv2);
        TextView item2Tv1=(TextView)view.findViewById(R.id.dialog_tige_item2_tv1);
        TextView item2Tv2=(TextView)view.findViewById(R.id.dialog_tige_item2_tv2);
        final EditText et1=(EditText)view.findViewById(R.id.dialog_tige_item1_et);
        final EditText et2=(EditText)view.findViewById(R.id.dialog_tige_item2_et);
        final EditText et3=(EditText)view.findViewById(R.id.dialog_tige_item3_et);
        et_qita=view.findViewById(R.id.et_qita);

        rg_leixing=view.findViewById(R.id.rg_leixing);
        switch(leixing_res){
            case "分诊":
                rg_leixing.check(R.id.rb_fenzhen);
                leixing="分诊";
                et_qita.setVisibility(View.GONE);
                break;
            case "溶栓前":
                rg_leixing.check(R.id.rb_rongshuanqian);
                leixing="溶栓前";
                et_qita.setVisibility(View.GONE);
                break;
            case "介入前":
                rg_leixing.check(R.id.rb_jieruqian);
                leixing="介入前";
                et_qita.setVisibility(View.GONE);
                break;
            case "其他":
                rg_leixing.check(R.id.rb_qita);
                leixing="其他";
                et_qita.setVisibility(View.VISIBLE);
                et_qita.setText(leixing_res);
                break;
            case "":
                break;
            default:
                rg_leixing.check(R.id.rb_qita);
                leixing="其他";
                et_qita.setVisibility(View.VISIBLE);
                et_qita.setText(leixing_res.replace("其他:",""));
                break;
        }
        rg_leixing.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.rb_fenzhen:
                        leixing="分诊";
                        et_qita.setVisibility(View.GONE);
                        break;
                    case R.id.rb_rongshuanqian:
                        leixing="溶栓前";
                        et_qita.setVisibility(View.GONE);
                        break;
                    case R.id.rb_jieruqian:
                        leixing="介入前";
                        et_qita.setVisibility(View.GONE);
                        break;
                    case R.id.rb_qita:
                        leixing="其他";
                        et_qita.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });


        View vieww=view.findViewById(R.id.dialog_tige_view);
        TextView tianjia=(TextView)view.findViewById(R.id.dialog_tige_tianjia);
        TextView dialog_tige_cancel=(TextView)view.findViewById(R.id.dialog_tige_cancel);
        if(list.size()==1){
            layout2.setVisibility(View.GONE);
//			vieww.setVisibility(View.GONE);
        }else{
            item2Tv1.setText(list.get(1).get("zuo"));
            item2Tv2.setText(list.get(1).get("you"));
        }
        item1Tv1.setText(list.get(0).get("zuo"));
        item1Tv2.setText(list.get(0).get("you"));
        et1.setText(zhi.get("zhong1"));
        if(zhi.get("zhong1")!=null&&zhi.get("zhong1").length()>0){
            et1.setSelection(zhi.get("zhong1").length());
            tianjia.setText("修改");
        }else{
            tianjia.setText("添加");
        }

        et2.setText(zhi.get("zhong2"));
        if(zhi.get("zhong2")!=null){
            et2.setSelection(zhi.get("zhong2").length());
        }
        if (zhi.get("Shijian")!=null&&!TextUtils.isEmpty(zhi.get("Shijian"))){
            et3.setText(zhi.get("Shijian"));
            if(zhi.get("Shijian")!=null){
                et3.setSelection(zhi.get("Shijian").length());
            }
        }else{
            et3.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            et3.setSelection(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()).length());
        }
        final List<Map<String, String>> listt=list;
        tianjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leixing.equals("其他")){
                    String s = et_qita.getText().toString();
                    if(s!=null&&!s.equals("")){
                        leixing="其他:"+et_qita.getText().toString();
                    }
                }
                if(listt.size()==1){
                    if (et1.getText().toString()==null||TextUtils.isEmpty(et1.getText().toString())){
                        ToastUtil.show(adtivity,"请输入相关值", Toast.LENGTH_SHORT);
                        return;
                    }
                    showLvListener.add1(et1.getText().toString(),et3.getText().toString(),leixing);
                }else{
                    if (et1.getText().toString()==null||TextUtils.isEmpty(et1.getText().toString())){
                        ToastUtil.show(adtivity,"请输入相关值", Toast.LENGTH_SHORT);
                        return;
                    }
                    if (et2.getText().toString()==null||TextUtils.isEmpty(et2.getText().toString())){
                        ToastUtil.show(adtivity,"请输入相关值", Toast.LENGTH_SHORT);
                        return;
                    }
                    showLvListener.add2(et1.getText().toString(),et2.getText().toString(),et3.getText().toString(),leixing);
                }
                dialog.dismiss();
            }
        });

        dialog_tige_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        et3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showLvListener.add3(et3);
            }
        });
    }
    public void hideRadioGroup(){
        if(rg_leixing!=null){
            rg_leixing.setVisibility(View.GONE);
        }
        if(et_qita!=null){
            et_qita.setVisibility(View.GONE);
        }
    }
}
