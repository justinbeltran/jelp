package com.justinbeltran.jelp.model;

import java.util.List;

/**
 * Represents search results from the Yelp API
 *
 * @param businesses List of businesses matching the search criteria
 * @param region Geographic region of search results (may be null in v3)
 * @param total Total number of businesses matching the search criteria
 */
public record Results(
        List<Business> businesses,
        Region region,
        Integer total) {

    // Backward compatibility getters
    public List<Business> getBusinesses() {
        return businesses;
    }

    public Region getRegion() {
        return region;
    }

    public Integer getTotal() {
        return total;
    }
}
