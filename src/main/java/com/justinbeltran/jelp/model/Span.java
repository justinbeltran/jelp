package com.justinbeltran.jelp.model;

/**
 * Represents the span (dimensions) of a geographic region
 *
 * @param latitude_delta The latitude span
 * @param longitude_delta The longitude span
 */
public record Span(Double latitude_delta, Double longitude_delta) {

    // Backward compatibility getters
    public Double getLatitude_delta() {
        return latitude_delta;
    }

    public Double getLongitude_delta() {
        return longitude_delta;
    }
}
