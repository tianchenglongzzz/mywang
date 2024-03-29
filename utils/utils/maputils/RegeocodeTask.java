package com.jhjz.emr.lstd_public.utils.maputils;

import android.content.Context;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

/**
 * Created by hopc on 2017/7/25.
 */

public class RegeocodeTask implements GeocodeSearch.OnGeocodeSearchListener {
    private static final float SEARCH_RADIUS = 50;
    private OnLocationGetListener mOnLocationGetListener;

    private GeocodeSearch mGeocodeSearch;

    public RegeocodeTask(Context context) {
        mGeocodeSearch = new GeocodeSearch(context);
        mGeocodeSearch.setOnGeocodeSearchListener(this);
    }

    public void search(double latitude, double longitude) {
        RegeocodeQuery regecodeQuery = new RegeocodeQuery(new LatLonPoint(
                latitude, longitude), SEARCH_RADIUS, GeocodeSearch.AMAP);
        mGeocodeSearch.getFromLocationAsyn(regecodeQuery);
    }

    public void setOnLocationGetListener(
            OnLocationGetListener onLocationGetListener) {
        mOnLocationGetListener = onLocationGetListener;
    }

    @Override
    public void onGeocodeSearched(GeocodeResult arg0, int arg1) {

    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeReult,
                                    int resultCode) {
        if (resultCode == AMapException.CODE_AMAP_SUCCESS) {
            if (regeocodeReult != null
                    && regeocodeReult.getRegeocodeAddress() != null
                    && mOnLocationGetListener != null) {
                String address = regeocodeReult.getRegeocodeAddress()
                        .getFormatAddress();
                String city = regeocodeReult.getRegeocodeAddress().getCityCode();

                PositionEntity entity = new PositionEntity();
                entity.address = address;
                entity.city = city;
                mOnLocationGetListener.onRegecodeGet(entity);

            }
        }
        //TODO 可以根据app自身需求对查询错误情况进行相应的提示或者逻辑处理
    }

}
