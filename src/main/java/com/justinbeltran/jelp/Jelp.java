package com.justinbeltran.jelp;

import org.scribe.model.Response;

import com.google.gson.Gson;
import com.justinbeltran.jelp.model.Business;
import com.justinbeltran.jelp.model.Results;

/**
 * This class interacts with Yelp v2 API
 * 
 * @author justin
 * 
 */
public class Jelp {

	private static final Gson GSON = new Gson();

	private Api api;

	public Jelp(String consumerKey, String consumerSecret, String token, String tokenSecret) {
		this.api = new Api(consumerKey, consumerSecret, token, tokenSecret);
	}

	/**
	 * Returns search results for a specific location
	 * 
	 * @param searchTerm
	 * @param location
	 * @return
	 */
	public Results search(String searchTerm, String location) {
		Response response = api.search(searchTerm, location);
		if (response.isSuccessful()) {
			return GSON.fromJson(response.getBody(), Results.class);
		}
		throw new IllegalArgumentException("Search request failed for search term: " + searchTerm + ", location: " + location);
	}

	public Business business(String id) {
		Response response = api.business(id);
		if (response.isSuccessful()) {
			return GSON.fromJson(response.getBody(), Business.class);
		}
		throw new IllegalArgumentException("Business request failed for id: " + id);
	}

	/**
	 * Main method for testing out Jelp class. Prints out results for
	 * "sushi in Irvine, CA" and business info for first returned result.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		// Your Yelp info goes here
		String consumerKey = "";
		String consumerSecret = "";
		String tokenKey = "";
		String tokenSecret = "";
		Jelp jelp = new Jelp(consumerKey, consumerSecret, tokenKey, tokenSecret);

		Results results = jelp.search("sushi", "Irvine, CA");
		System.out.println("Results: " + new Gson().toJson(results));

		String businessId = results.getBusinesses().get(0).getId();
		Business business = jelp.business(businessId);
		System.out.println("Info for business " + businessId + ": " + new Gson().toJson(business));

	}

}
