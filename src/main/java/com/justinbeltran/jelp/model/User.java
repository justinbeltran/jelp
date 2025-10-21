package com.justinbeltran.jelp.model;

/**
 * Represents a Yelp user (reviewer)
 *
 * @param id The user's unique identifier
 * @param image_url URL to the user's profile image
 * @param name The user's display name
 */
public record User(String id, String image_url, String name) {

    // Backward compatibility getters
    public String getId() {
        return id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getName() {
        return name;
    }
}
