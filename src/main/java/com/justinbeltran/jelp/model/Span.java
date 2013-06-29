package com.justinbeltran.jelp.model;

public class Span {
    private Number latitude_delta;
    private Number longitude_delta;

    public Number getLatitude_delta() {
        return this.latitude_delta;
    }

    public void setLatitude_delta(Number latitude_delta) {
        this.latitude_delta = latitude_delta;
    }

    public Number getLongitude_delta() {
        return this.longitude_delta;
    }

    public void setLongitude_delta(Number longitude_delta) {
        this.longitude_delta = longitude_delta;
    }
}
