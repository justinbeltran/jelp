package com.justinbeltran.jelp.model;

public class Review {
    private String excerpt;
    private String id;
    private Double rating;
    private String rating_image_large_url;
    private String rating_image_small_url;
    private String rating_image_url;
    private Double time_created;
    private User user;

    public String getExcerpt() {
        return this.excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getRating_image_large_url() {
        return this.rating_image_large_url;
    }

    public void setRating_image_large_url(String rating_image_large_url) {
        this.rating_image_large_url = rating_image_large_url;
    }

    public String getRating_image_small_url() {
        return this.rating_image_small_url;
    }

    public void setRating_image_small_url(String rating_image_small_url) {
        this.rating_image_small_url = rating_image_small_url;
    }

    public String getRating_image_url() {
        return this.rating_image_url;
    }

    public void setRating_image_url(String rating_image_url) {
        this.rating_image_url = rating_image_url;
    }

    public Double getTime_created() {
        return this.time_created;
    }

    public void setTime_created(Double time_created) {
        this.time_created = time_created;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
