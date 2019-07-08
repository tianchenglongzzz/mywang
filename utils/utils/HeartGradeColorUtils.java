package com.jhjz.emr.lstd_public.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jhjz.emr.lstd_public.R;

public class HeartGradeColorUtils {

    @SuppressLint("ResourceAsColor")
    public static void setColor(int id, View[] arr,TextView[] scoreArr,TextView[] infoArr ) {
        for (int i = 0; i < arr.length; i++) {
            if (id == i) {
                scoreArr[i].setTextColor(Color.WHITE);
                infoArr[i].setTextColor(Color.WHITE);
               // arr[i].setBackgroundColor(Color.parseColor("#70c397"));
            } else {
                arr[i].setVisibility(View.GONE);
               // arr[i].setBackgroundColor(Color.WHITE);
            }
        }
    }
    private static  String black="#666666";
    public static void setColor1(int id, View[] arr,TextView[] scoreArr,TextView[] infoArr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i].setVisibility(View.VISIBLE);
            if (id == i) {
                //scoreArr[i].setTextColor(Color.parseColor("#70c397"));
                //infoArr[i].setTextColor(Color.parseColor("#70c397"));
                scoreArr[i].setTextColor(Color.parseColor(black));
                infoArr[i].setTextColor(Color.parseColor(black));
                //arr[i].setBackgroundColor(Color.parseColor("#70c397"));
            } else {
                scoreArr[i].setTextColor(Color.parseColor(black));
                infoArr[i].setTextColor(Color.parseColor(black));
              //  arr[i].setBackgroundColor(Color.WHITE);
            }
        }
    }

    //病史
    public static RelativeLayout[] getViewBSId(View view) {

        RelativeLayout bsRelativeLayout1 = view.findViewById(R.id.bs_relative1);
        RelativeLayout bsRelativeLayout2 = view.findViewById(R.id.bs_relative2);
        RelativeLayout bsRelativeLayout3 = view.findViewById(R.id.bs_relative3);
        return new RelativeLayout[]{bsRelativeLayout1, bsRelativeLayout2, bsRelativeLayout3};

    }

    //心电图
    public static RelativeLayout[] getViewXDTId(View view) {
        RelativeLayout xdtRelativeLayout1 = view.findViewById(R.id.xdt_relative1);
        RelativeLayout xdtRelativeLayout2 = view.findViewById(R.id.xdt_relative2);
        RelativeLayout xdtRelativeLayout3 = view.findViewById(R.id.xdt_relative3);
        RelativeLayout xdtRelativeLayout4 = view.findViewById(R.id.xdt_relative4);
        RelativeLayout xdtRelativeLayout5 = view.findViewById(R.id.xdt_relative5);
        return new RelativeLayout[]{xdtRelativeLayout1, xdtRelativeLayout2, xdtRelativeLayout3, xdtRelativeLayout4, xdtRelativeLayout5};
    }

    //年龄
    public static RelativeLayout[] getViewAGEId(View view) {
        RelativeLayout ageRelativeLayout1 = view.findViewById(R.id.age_relative1);
        RelativeLayout ageRelativeLayout2 = view.findViewById(R.id.age_relative2);
        RelativeLayout ageRelativeLayout3 = view.findViewById(R.id.age_relative3);
        return new RelativeLayout[]{ageRelativeLayout1, ageRelativeLayout2, ageRelativeLayout3};
    }

    //肌钙蛋白
    public static RelativeLayout[] getViewJGDBId(View view) {
        RelativeLayout jgdbRelativeLayout1 = view.findViewById(R.id.jg_relative1);
        RelativeLayout jgdbRelativeLayout2 = view.findViewById(R.id.jg_relative2);
        RelativeLayout jgdbRelativeLayout3 = view.findViewById(R.id.jg_relative3);
        return new RelativeLayout[]{jgdbRelativeLayout1, jgdbRelativeLayout2, jgdbRelativeLayout3};
    }

    //绿线
    public static View[] getGreenLine(View view) {
        View viewBs = view.findViewById(R.id.bs_line_green);
        View viewXdt = view.findViewById(R.id.xdt_line_green);
        View viewAge = view.findViewById(R.id.age_line_green);
        View viewJg = view.findViewById(R.id.jg_line_green);
        return new View[]{viewBs, viewXdt, viewAge, viewJg};
    }

    public static CardView[] getCardView(View view) {
        CardView cardViewBs = view.findViewById(R.id.bs_card_view);
        CardView cardViewXdt = view.findViewById(R.id.xdt_card_view);
        CardView cardViewAge = view.findViewById(R.id.age_card_view);
        CardView cardViewJg = view.findViewById(R.id.jg_card_view);
        return new CardView[]{cardViewBs, cardViewXdt, cardViewAge, cardViewJg};
    }

    //病史分数
    public static TextView[] getBsScore(View view) {
        TextView bsScore1 = view.findViewById(R.id.bs_score1);
        TextView bsScore2 = view.findViewById(R.id.bs_score2);
        TextView bsScore3 = view.findViewById(R.id.bs_score3);
        return new TextView[]{bsScore1, bsScore2, bsScore3};
    }

    //心电图分数
    public static TextView[] getXdtScore(View view) {
        TextView xdtScore1 = view.findViewById(R.id.xdt_score1);
        TextView xdtScore2 = view.findViewById(R.id.xdt_score2);
        TextView xdtScore3 = view.findViewById(R.id.xdt_score3);
        TextView xdtScore4 = view.findViewById(R.id.xdt_score4);
        TextView xdtScore5 = view.findViewById(R.id.xdt_score5);
        return new TextView[]{xdtScore1, xdtScore2, xdtScore3, xdtScore4, xdtScore5};
    }

    //年龄分数
    public static TextView[] getAgeScore(View view) {
        TextView ageScore1 = view.findViewById(R.id.age_score1);
        TextView ageScore2 = view.findViewById(R.id.age_score2);
        TextView ageScore3 = view.findViewById(R.id.age_score3);
        return new TextView[]{ageScore1, ageScore2, ageScore3};
    }

    //肌钙蛋白分数
    public static TextView[] getJgScore(View view) {
        TextView jgScore1 = view.findViewById(R.id.jg_score1);
        TextView jgScore2 = view.findViewById(R.id.jg_score2);
        TextView jgScore3 = view.findViewById(R.id.jg_score3);
        return new TextView[]{jgScore1, jgScore2, jgScore3};
    }
    //病史内容
    public static TextView[] getBsInfo(View view){
        TextView bsInfo1 = view.findViewById(R.id.bs_info1);
        TextView bsInfo2 = view.findViewById(R.id.bs_info2);
        TextView bsInfo3 = view.findViewById(R.id.bs_info3);
        return new TextView[]{bsInfo1, bsInfo2, bsInfo3};
    }

    //心电图内容
    public static TextView[] getXdtInfo(View view) {
        TextView xdtInfo1 = view.findViewById(R.id.xdt_info1);
        TextView xdtInfo2 = view.findViewById(R.id.xdt_info2);
        TextView xdtInfo3 = view.findViewById(R.id.xdt_info3);
        TextView xdtInfo4 = view.findViewById(R.id.xdt_info4);
        TextView xdtInfo5 = view.findViewById(R.id.xdt_info5);
        return new TextView[]{xdtInfo1, xdtInfo2, xdtInfo3, xdtInfo4, xdtInfo5};
    }
    //年龄内容
    public static TextView[] getAgeInfo(View view) {
        TextView ageInfo1 = view.findViewById(R.id.age_info1);
        TextView ageInfo2 = view.findViewById(R.id.age_info2);
        TextView ageInfo3 = view.findViewById(R.id.age_info3);
        return new TextView[]{ageInfo1, ageInfo2, ageInfo3};
    }
    //肌钙蛋白内容
    public static TextView[] getJgInfo(View view) {
        TextView jgInfo1 = view.findViewById(R.id.jg_info1);
        TextView jgInfo2 = view.findViewById(R.id.jg_info2);
        TextView jgInfo3 = view.findViewById(R.id.jg_info3);
        return new TextView[]{jgInfo1, jgInfo2, jgInfo3};
    }
}
