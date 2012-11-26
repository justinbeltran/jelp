Jelp
====

Jelp is a Yelp v2 API Wrapper written in Java. It's mostly based on the already existing example on Yelp's github page [https://github.com/Yelp/yelp-api/blob/master/v2/java/Yelp.java]([https://github.com/Yelp/yelp-api/blob/master/v2/java/Yelp.java]).

Prereqs
-------

* Maven 2.x/3.x
* Signed up API access on [Yelp's Developers Page](http://www.yelp.com/developers)

Haven't put this on any central repos, so if you want to use this, you'll have to clone the code and run a "mvn install" to include this in your library.

Usage
-----
```java

// Replace with you OAuth credentials
String consumerKey = "YOUR_CONSUMER_KEY_GOES_HERE";
String consumerSecret = "YOUR_CONSUMER_SECRET_GOES_HERE";
String tokenKey = "YOUR_TOKEN_KEY_GOES_HERE";
String tokenSecret = "YOUR_TOKEN_SECRET_GOES_HERE";

Jelp jelp = new Jelp(consumerKey, consumerSecret, tokenKey, tokenSecret);

Results results = jelp.search("sushi", "Irvine, CA");
System.out.println("Total results: " + results.getTotal());
```
