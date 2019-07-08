package com.jhjz.emr.lstd_public.utils.maputils;

/**
 * Created by hopc on 2017/7/25.
 */

public class PositionEntity {

    public double latitue;

    public double longitude;

    public String address;

    public String city;

    public PositionEntity() {
    }

    public PositionEntity(double latitude, double longtitude, String address, String city) {
        this.latitue = latitude;
        this.longitude = longtitude;
        this.address = address;
    }

}
