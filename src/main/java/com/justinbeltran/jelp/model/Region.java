package com.justinbeltran.jelp.model;

/**
 * Represents a geographic region with center point and span
 *
 * @param center The center coordinates of the region
 * @param span The span (dimensions) of the region
 */
public record Region(Center center, Span span) {

    // Backward compatibility getters
    public Center getCenter() {
        return center;
    }

    public Span getSpan() {
        return span;
    }
}
