package com.justinbeltran.jelp.model;

import java.util.List;

/**
 * Represents a business location with address and coordinates
 *
 * @param address Address lines (street, etc.)
 * @param city City name
 * @param coordinate Geographic coordinates (may be null in v3, as coordinates can be at business level)
 * @param country_code Country code (e.g., "US")
 * @param display_address Formatted address for display
 * @param geo_accuracy Geographic accuracy (deprecated in v3)
 * @param postal_code Postal/ZIP code
 * @param state_code State/province code (e.g., "CA")
 * @param cross_streets Cross streets information (v3)
 * @param address1 Primary address line (v3)
 * @param address2 Secondary address line (v3)
 * @param address3 Tertiary address line (v3)
 * @param zip_code ZIP code (v3, alternative to postal_code)
 */
public record Location(
        List<String> address,
        String city,
        Coordinate coordinate,
        String country_code,
        List<String> display_address,
        Integer geo_accuracy,
        String postal_code,
        String state_code,
        String cross_streets,
        String address1,
        String address2,
        String address3,
        String zip_code) {

    // Backward compatibility getters
    public List<String> getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getCountry_code() {
        return country_code;
    }

    public List<String> getDisplay_address() {
        return display_address;
    }

    public Integer getGeo_accuracy() {
        return geo_accuracy;
    }

    public String getPostal_code() {
        return postal_code != null ? postal_code : zip_code;
    }

    public String getState_code() {
        return state_code;
    }

    public String getCross_streets() {
        return cross_streets;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getZip_code() {
        return zip_code;
    }
}
