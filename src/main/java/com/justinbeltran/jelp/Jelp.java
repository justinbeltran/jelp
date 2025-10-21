package com.justinbeltran.jelp;

import com.google.gson.Gson;
import com.justinbeltran.jelp.model.Business;
import com.justinbeltran.jelp.model.Results;

import java.io.IOException;
import java.net.http.HttpResponse;

/**
 * Main interface for interacting with the Yelp Fusion API v3
 *
 * @author justin
 */
public class Jelp {

    private static final Gson GSON = new Gson();

    private final Api api;

    /**
     * Creates a new Jelp client with the provided API key
     *
     * @param apiKey Your Yelp Fusion API key (get it from https://www.yelp.com/developers)
     */
    public Jelp(String apiKey) {
        this.api = new Api(apiKey);
    }

    /**
     * Searches for businesses matching the given term and location
     *
     * @param searchTerm The search term (e.g., "sushi", "pizza")
     * @param location The location to search in (e.g., "Irvine, CA")
     * @return Results object containing matching businesses
     * @throws IllegalArgumentException If the request fails
     */
    public Results search(String searchTerm, String location) {
        try {
            HttpResponse<String> response = api.search(searchTerm, location);
            if (response.statusCode() == 200) {
                return GSON.fromJson(response.body(), Results.class);
            }
            throw new IllegalArgumentException(
                    String.format("Search request failed with status %d for search term: %s, location: %s. Response: %s",
                            response.statusCode(), searchTerm, location, response.body()));
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalArgumentException("Search request failed for search term: " + searchTerm + ", location: " + location, e);
        }
    }

    /**
     * Retrieves detailed information for a specific business
     *
     * @param id The business ID (e.g., "rolling-sushi-van-irvine")
     * @return Business object with detailed information
     * @throws IllegalArgumentException If the request fails
     */
    public Business business(String id) {
        try {
            HttpResponse<String> response = api.business(id);
            if (response.statusCode() == 200) {
                return GSON.fromJson(response.body(), Business.class);
            }
            throw new IllegalArgumentException(
                    String.format("Business request failed with status %d for id: %s. Response: %s",
                            response.statusCode(), id, response.body()));
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalArgumentException("Business request failed for id: " + id, e);
        }
    }

    /**
     * Main method for testing Jelp functionality
     * Prints search results for "sushi in Irvine, CA" and details for the first result
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Get your API key from: https://www.yelp.com/developers/v3/manage_app
        String apiKey = System.getenv("YELP_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("Please set the YELP_API_KEY environment variable");
            System.err.println("Get your API key from: https://www.yelp.com/developers/v3/manage_app");
            System.exit(1);
        }

        Jelp jelp = new Jelp(apiKey);

        // Search for sushi in Irvine, CA
        Results results = jelp.search("sushi", "Irvine, CA");
        System.out.println("Search Results:");
        System.out.println("Total results: " + results.getTotal());
        System.out.println("Results returned: " + results.getBusinesses().size());
        System.out.println();

        // Get details for the first business
        if (!results.getBusinesses().isEmpty()) {
            String businessId = results.getBusinesses().get(0).getId();
            Business business = jelp.business(businessId);
            System.out.println("Business Details for: " + businessId);
            System.out.println("Name: " + business.getName());
            System.out.println("Phone: " + business.getDisplayPhone());
            System.out.println("Rating: " + business.getRating());
            System.out.println("Review Count: " + business.getReviewCount());
        }
    }
}
