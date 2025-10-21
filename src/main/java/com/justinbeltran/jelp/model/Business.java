package com.justinbeltran.jelp.model;

import java.util.List;

/**
 * Represents a business from the Yelp API
 * Supports both v2 (deprecated fields) and v3 API responses
 *
 * @param id Business unique identifier
 * @param alias Business alias (v3)
 * @param name Business name
 * @param image_url URL to business image
 * @param is_claimed Whether the business is claimed by its owner
 * @param is_closed Whether the business is permanently closed
 * @param url Yelp URL for the business
 * @param phone Business phone number
 * @param display_phone Formatted phone number for display
 * @param review_count Number of reviews
 * @param categories List of business categories
 * @param rating Average rating (1-5)
 * @param location Business location information
 * @param coordinates Geographic coordinates (v3, at business level)
 * @param photos Array of photo URLs (v3)
 * @param price Price level indicator (v3, e.g., "$", "$$", "$$$", "$$$$")
 * @param hours Business hours (v3)
 * @param transactions Supported transaction types (v3, e.g., "pickup", "delivery", "restaurant_reservation")
 * @param reviews List of reviews (from business details endpoint)
 * @param mobile_url Mobile URL (deprecated in v3)
 * @param rating_img_url Rating image URL (deprecated in v3)
 * @param rating_img_url_large Large rating image URL (deprecated in v3)
 * @param rating_img_url_small Small rating image URL (deprecated in v3)
 * @param snippet_image_url Snippet image URL (deprecated in v3)
 * @param snippet_text Snippet text (deprecated in v3)
 */
public record Business(
        String id,
        String alias,
        String name,
        String image_url,
        Boolean is_claimed,
        Boolean is_closed,
        String url,
        String phone,
        String display_phone,
        Integer review_count,
        List<Category> categories,
        Double rating,
        Location location,
        Coordinate coordinates,
        List<String> photos,
        String price,
        Object hours,  // Complex structure, using Object for now
        List<String> transactions,
        List<Review> reviews,
        // Deprecated v2 fields
        String mobile_url,
        String rating_img_url,
        String rating_img_url_large,
        String rating_img_url_small,
        String snippet_image_url,
        String snippet_text) {

    // Backward compatibility getters
    public String getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public Boolean getIs_claimed() {
        return is_claimed;
    }

    public Boolean getIs_closed() {
        return is_closed;
    }

    public String getUrl() {
        return url;
    }

    public String getPhone() {
        return phone;
    }

    public String getDisplay_phone() {
        return display_phone;
    }

    public String getDisplayPhone() {
        return display_phone;
    }

    public Integer getReview_count() {
        return review_count;
    }

    public Integer getReviewCount() {
        return review_count;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Double getRating() {
        return rating;
    }

    public Location getLocation() {
        return location;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public String getPrice() {
        return price;
    }

    public Object getHours() {
        return hours;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    // Deprecated v2 getters
    @Deprecated
    public String getMobile_url() {
        return mobile_url;
    }

    @Deprecated
    public String getRating_img_url() {
        return rating_img_url;
    }

    @Deprecated
    public String getRating_img_url_large() {
        return rating_img_url_large;
    }

    @Deprecated
    public String getRating_img_url_small() {
        return rating_img_url_small;
    }

    @Deprecated
    public String getSnippet_image_url() {
        return snippet_image_url;
    }

    @Deprecated
    public String getSnippet_text() {
        return snippet_text;
    }
}
