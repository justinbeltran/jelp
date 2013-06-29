package com.justinbeltran.jelp;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * Wrapper for Yelp v2 API that does OAuth authentication
 * 
 * Based after Java Yelp example
 * https://github.com/Yelp/yelp-api/blob/master/v2/java/YelpApi2.java
 * 
 * @author justin
 * 
 */
public class Api {

    private static final String SEARCH_URL = "http://api.yelp.com/v2/search";
    private static final String BASE_BUSINESS_URL = "http://api.yelp.com/v2/business";

    private OAuthService oauthService;
    private Token token;

    public Api(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.oauthService = new ServiceBuilder().provider(YelpV2Provider.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
        this.token = new Token(token, tokenSecret);
    }

    /**
     * Returns search results for a specific search term and location
     * 
     * @param searchTerm
     * @param location
     * @return
     */
    public Response search(String searchTerm, String location) {
        OAuthRequest request = new OAuthRequest(Verb.GET, SEARCH_URL);
        request.addQuerystringParameter("term", searchTerm);
        request.addQuerystringParameter("location", location);
        oauthService.signRequest(token, request);
        return request.send();
    }

    /**
     * Returns info for a business with a specific id
     * 
     * 
     * @param id
     * @return
     */
    public Response business(String id) {
        OAuthRequest request = new OAuthRequest(Verb.GET, BASE_BUSINESS_URL + "/" + id);
        oauthService.signRequest(token, request);
        return request.send();
    }

}
