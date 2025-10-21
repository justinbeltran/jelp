package com.justinbeltran.jelp;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * Wrapper for Yelp Fusion API v3 that handles HTTP requests with Bearer token authentication
 *
 * @author justin
 */
public class Api {

    private static final String API_BASE_URL = "https://api.yelp.com/v3";
    private static final String SEARCH_ENDPOINT = "/businesses/search";
    private static final String BUSINESS_ENDPOINT = "/businesses";

    private final HttpClient httpClient;
    private final String apiKey;

    /**
     * Creates a new API client with the provided API key
     *
     * @param apiKey Your Yelp Fusion API key
     */
    public Api(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("API key cannot be null or empty");
        }
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    /**
     * Returns search results for a specific search term and location
     *
     * @param searchTerm The search term (e.g., "sushi", "pizza")
     * @param location The location to search in (e.g., "Irvine, CA")
     * @return HttpResponse containing the search results
     * @throws IOException If an I/O error occurs
     * @throws InterruptedException If the request is interrupted
     */
    public HttpResponse<String> search(String searchTerm, String location) throws IOException, InterruptedException {
        var queryParams = String.format("?term=%s&location=%s",
                encodeValue(searchTerm),
                encodeValue(location));

        var uri = URI.create(API_BASE_URL + SEARCH_ENDPOINT + queryParams);

        var request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization", "Bearer " + apiKey)
                .header("Accept", "application/json")
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Returns detailed information for a business with a specific id
     *
     * @param id The business ID
     * @return HttpResponse containing the business details
     * @throws IOException If an I/O error occurs
     * @throws InterruptedException If the request is interrupted
     */
    public HttpResponse<String> business(String id) throws IOException, InterruptedException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Business ID cannot be null or empty");
        }

        var uri = URI.create(API_BASE_URL + BUSINESS_ENDPOINT + "/" + id);

        var request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization", "Bearer " + apiKey)
                .header("Accept", "application/json")
                .GET()
                .build();

        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * URL-encodes a value for use in query parameters
     *
     * @param value The value to encode
     * @return The URL-encoded value
     */
    private String encodeValue(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
