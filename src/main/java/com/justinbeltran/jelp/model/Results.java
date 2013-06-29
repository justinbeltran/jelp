package com.justinbeltran.jelp.model;

import java.util.List;

public class Results {
    private List<Business> businesses;
    private Region region;
    private Integer total;

    public List<Business> getBusinesses() {
        return this.businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public Region getRegion() {
        return this.region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
