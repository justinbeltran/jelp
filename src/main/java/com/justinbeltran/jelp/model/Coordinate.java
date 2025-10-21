package com.justinbeltran.jelp.model;

/**
 * Represents geographic coordinates (latitude and longitude)
 *
 * @param latitude The latitude coordinate
 * @param longitude The longitude coordinate
 */
public record Coordinate(Double latitude, Double longitude) {

    // Backward compatibility getters with old naming
    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
