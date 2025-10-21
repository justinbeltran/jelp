package com.justinbeltran.jelp.model;

/**
 * Represents the center point of a geographic region
 *
 * @param latitude The latitude of the center point
 * @param longitude The longitude of the center point
 */
public record Center(Double latitude, Double longitude) {

    // Backward compatibility getters
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
