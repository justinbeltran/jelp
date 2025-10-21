package com.justinbeltran.jelp.model;

/**
 * Represents a business review
 *
 * @param excerpt Review text excerpt
 * @param id Review unique identifier
 * @param rating Review rating (1-5)
 * @param rating_image_large_url URL to large rating image (deprecated in v3)
 * @param rating_image_small_url URL to small rating image (deprecated in v3)
 * @param rating_image_url URL to rating image (deprecated in v3)
 * @param time_created Unix timestamp when review was created
 * @param user The user who wrote the review
 */
public record Review(
        String excerpt,
        String id,
        Double rating,
        String rating_image_large_url,
        String rating_image_small_url,
        String rating_image_url,
        Long time_created,
        User user) {

    // Backward compatibility getters
    public String getExcerpt() {
        return excerpt;
    }

    public String getId() {
        return id;
    }

    public Double getRating() {
        return rating;
    }

    public String getRating_image_large_url() {
        return rating_image_large_url;
    }

    public String getRating_image_small_url() {
        return rating_image_small_url;
    }

    public String getRating_image_url() {
        return rating_image_url;
    }

    public Long getTime_created() {
        return time_created;
    }

    public User getUser() {
        return user;
    }
}
