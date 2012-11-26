package com.justinbeltran.jelp;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * Based after
 * https://github.com/Yelp/yelp-api/blob/master/v2/java/YelpApi2.java
 * 
 * @author justin
 * 
 */
public class YelpV2Provider extends DefaultApi10a {

	@Override
	public String getRequestTokenEndpoint() {
		return null;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return null;
	}

	@Override
	public String getAuthorizationUrl(Token requestToken) {
		return null;
	}

}
