Jelp
====

[![Java CI](https://github.com/justinbeltran/jelp/actions/workflows/build.yml/badge.svg)](https://github.com/justinbeltran/jelp/actions/workflows/build.yml)

Jelp is a modern Java wrapper for the **Yelp Fusion API v3**. It provides a simple, type-safe interface for searching businesses and retrieving business details using the latest Yelp API.

## Features

- ✅ **Yelp Fusion API v3** support
- ✅ **Java 21** with modern language features (Records, var, etc.)
- ✅ **Bearer token authentication** (simple API key)
- ✅ **Type-safe models** using Java Records
- ✅ **Built-in HTTP client** (no external HTTP dependencies)
- ✅ **Comprehensive Javadoc** documentation

## Prerequisites

- **Java 21 or higher** (Java 25 LTS recommended)
- **Maven 3.x**
- **Yelp Fusion API Key** - Get yours at [Yelp Developers](https://www.yelp.com/developers/v3/manage_app)

## Installation

### Option 1: Local Build (Current)

Clone the repository and install to your local Maven repository:

```bash
git clone https://github.com/justinbeltran/jelp.git
cd jelp
mvn clean install
```

Then add to your `pom.xml`:

```xml
<dependency>
    <groupId>com.justinbeltran</groupId>
    <artifactId>jelp</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### Option 2: Maven Central (Coming Soon)

Future releases will be available on Maven Central.

## Usage

### Quick Start

```java
import com.justinbeltran.jelp.Jelp;
import com.justinbeltran.jelp.model.Business;
import com.justinbeltran.jelp.model.Results;

public class Example {
    public static void main(String[] args) {
        // Create Jelp instance with your API key
        String apiKey = "YOUR_YELP_API_KEY";
        Jelp jelp = new Jelp(apiKey);

        // Search for businesses
        Results results = jelp.search("sushi", "Irvine, CA");
        System.out.println("Total results: " + results.getTotal());

        // Get first business details
        if (!results.getBusinesses().isEmpty()) {
            String businessId = results.getBusinesses().get(0).getId();
            Business business = jelp.business(businessId);

            System.out.println("Business name: " + business.getName());
            System.out.println("Rating: " + business.getRating());
            System.out.println("Phone: " + business.getDisplayPhone());
            System.out.println("Price: " + business.getPrice());
        }
    }
}
```

### Using Environment Variables

For better security, use environment variables:

```java
String apiKey = System.getenv("YELP_API_KEY");
if (apiKey == null || apiKey.isBlank()) {
    System.err.println("Please set the YELP_API_KEY environment variable");
    System.exit(1);
}

Jelp jelp = new Jelp(apiKey);
```

### API Methods

#### Search for Businesses

```java
Results results = jelp.search("pizza", "New York, NY");

for (Business business : results.getBusinesses()) {
    System.out.println(business.getName() + " - " + business.getRating() + " stars");
}
```

#### Get Business Details

```java
Business business = jelp.business("business-id-here");

System.out.println("Name: " + business.getName());
System.out.println("Rating: " + business.getRating());
System.out.println("Review Count: " + business.getReviewCount());
System.out.println("Price Level: " + business.getPrice());
System.out.println("Categories: " +
    business.getCategories().stream()
        .map(cat -> cat.getTitle())
        .collect(Collectors.joining(", ")));
```

## API Reference

### Main Class: `Jelp`

**Constructor:**
- `Jelp(String apiKey)` - Creates a new Jelp client with your API key

**Methods:**
- `Results search(String searchTerm, String location)` - Search for businesses
- `Business business(String id)` - Get detailed business information

### Models

All models are implemented as Java Records with full backward compatibility:

- **Business** - Comprehensive business information
- **Results** - Search results container
- **Location** - Business location and address
- **Category** - Business category information
- **Coordinate** - Geographic coordinates
- **Review** - Business review
- **User** - Review author
- **Region** - Geographic region (search results)

## Migration from v0.x (Yelp API v2)

Version 1.0 includes breaking changes due to the Yelp API upgrade:

### Authentication Changed

**Old (v0.x - OAuth 1.0a):**
```java
Jelp jelp = new Jelp(consumerKey, consumerSecret, tokenKey, tokenSecret);
```

**New (v1.0 - Bearer Token):**
```java
Jelp jelp = new Jelp(apiKey);
```

### Get Your New API Key

1. Visit [Yelp Developers](https://www.yelp.com/developers/v3/manage_app)
2. Create an app (or use existing)
3. Copy your API Key
4. **Note:** OAuth credentials from v2 API will NOT work

### Other Changes

- **Minimum Java version:** Java 6 → Java 21
- **Categories structure:** Changed from nested arrays to objects with `alias` and `title`
- **Deprecated fields removed:** `mobile_url`, `rating_img_url*`, `snippet_*` fields
- **New fields available:** `price`, `transactions`, `photos`, `coordinates` (at business level)

See [CHANGELOG.md](CHANGELOG.md) for complete migration guide.

## Building from Source

```bash
# Clone the repository
git clone https://github.com/justinbeltran/jelp.git
cd jelp

# Build and run tests
mvn clean verify

# Install to local Maven repository
mvn install
```

## Development

### Running Tests

```bash
mvn test
```

### Running the Example

Set your API key as an environment variable:

```bash
export YELP_API_KEY=your_api_key_here
mvn exec:java -Dexec.mainClass="com.justinbeltran.jelp.Jelp"
```

## Requirements

- Java 21 or higher (Java 25 LTS recommended)
- Maven 3.x
- Yelp Fusion API v3 key

## Dependencies

**Runtime:**
- Gson 2.13.2 (JSON serialization)

**Built-in:**
- Java HttpClient (no external HTTP library needed)

**Test:**
- JUnit Jupiter 5.11.4
- Mockito 5.15.2
- Commons IO 2.18.0
- Commons Lang3 3.17.0

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE.txt](LICENSE.txt) file for details.

## Links

- [Yelp Fusion API Documentation](https://docs.developer.yelp.com/)
- [Yelp Developers Portal](https://www.yelp.com/developers)
- [GitHub Repository](https://github.com/justinbeltran/jelp)

## Acknowledgments

Originally based on the [Java example from Yelp's GitHub](https://github.com/Yelp/yelp-api/blob/master/v2/java/Yelp.java), now fully modernized for Yelp Fusion API v3 and Java 21.

---

**Note:** This is an unofficial library. Yelp and the Yelp logo are trademarks of Yelp Inc.
