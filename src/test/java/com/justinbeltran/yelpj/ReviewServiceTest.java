package com.justinbeltran.yelpj;

import org.junit.Test;

public class ReviewServiceTest {

	@Test
	public void getReview() {
		ReviewService reviewService = new ReviewService();
		Review review = reviewService.getReview("name", "address");
	}

}
