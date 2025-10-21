# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0-SNAPSHOT] - 2025-10-21

### 🚀 Major Modernization Release

This is a complete rewrite and modernization of Jelp, upgrading from Yelp API v2 to Yelp Fusion API v3 and bringing the codebase to modern Java standards.

### Added

- ✅ **Yelp Fusion API v3 support** - Migrated from deprecated v2 API to current v3
- ✅ **Java 21 support** - Leveraging modern Java features including:
  - Records for immutable data models
  - `var` for local variable type inference
  - Text blocks for readable strings
  - Enhanced switch expressions
  - Pattern matching capabilities
- ✅ **Java HttpClient** - Using built-in `java.net.http.HttpClient` (Java 11+)
- ✅ **Bearer token authentication** - Simple API key instead of OAuth 1.0a
- ✅ **Type-safe models** - All model classes converted to Java Records
- ✅ **New v3 API fields**:
  - `price` - Price level indicator ("$", "$$", "$$$", "$$$$")
  - `transactions` - Supported transaction types (pickup, delivery, etc.)
  - `photos` - Array of photo URLs
  - `coordinates` - Geographic coordinates at business level
  - `alias` - Business alias field
  - Enhanced location information
- ✅ **GitHub Actions CI** - Replaced Travis CI with modern GitHub Actions
- ✅ **Comprehensive Javadoc** - Full documentation for all public APIs
- ✅ **Modern test framework** - JUnit 5 (Jupiter) with Mockito 5

### Changed

- ⚠️ **BREAKING:** Authentication completely redesigned
  - **Old:** `new Jelp(consumerKey, consumerSecret, tokenKey, tokenSecret)`
  - **New:** `new Jelp(apiKey)`
- ⚠️ **BREAKING:** Minimum Java version increased from Java 6 to Java 21
- ⚠️ **BREAKING:** Category structure changed
  - **Old:** Nested arrays `List<List<String>>`
  - **New:** Category objects with `alias` and `title` fields
- **Updated:** All model classes converted from POJOs to Records
  - Backward-compatible getter methods maintained
  - Added modern accessor methods (e.g., `getDisplayPhone()` in addition to `getDisplay_phone()`)
- **Updated:** API endpoints
  - **Old:** `http://api.yelp.com/v2/*`
  - **New:** `https://api.yelp.com/v3/*`
- **Updated:** All dependencies to latest versions:
  - Gson: 2.2.2 → 2.13.2
  - JUnit: 4.8.1 → 5.11.4 (Jupiter)
  - Mockito: 1.9.5-rc1 → 5.15.2
  - Commons IO: 2.4 → 2.18.0
  - Commons Lang3: 3.1 → 3.17.0
  - Maven Compiler Plugin: 3.0 → 3.13.0
  - Added Maven Surefire Plugin 3.5.2 for JUnit 5 support
- **Updated:** Project version from 0.0.1-SNAPSHOT to 1.0.0-SNAPSHOT

### Removed

- ❌ **BREAKING:** Removed OAuth 1.0a authentication (Scribe library)
- ❌ **BREAKING:** Removed deprecated v2-only fields:
  - `mobile_url` - Mobile website URLs no longer provided
  - `rating_img_url`, `rating_img_url_large`, `rating_img_url_small` - Rating images deprecated
  - `snippet_text`, `snippet_image_url` - Review snippets no longer in API
  - `geo_accuracy` - Geographic accuracy field removed
- ❌ Removed `YelpV2Provider.java` OAuth provider class
- ❌ Removed Travis CI configuration
- ❌ Removed Java 6/7 compatibility code

### Deprecated

The following fields are marked as `@Deprecated` but still present for backward compatibility with v2 response formats:
- `mobile_url`
- `rating_img_url*` fields
- `snippet_*` fields

## Migration Guide from 0.x to 1.0

### Step 1: Update Java Version

Ensure you have Java 21 or higher installed:

```bash
java -version  # Should show 21 or higher
```

### Step 2: Get Yelp Fusion API v3 Credentials

1. Visit [Yelp Developers](https://www.yelp.com/developers/v3/manage_app)
2. Create a new app or select existing app
3. Copy your **API Key** (single key, not OAuth credentials)
4. **Important:** Your old OAuth v2 credentials will NOT work with v3

### Step 3: Update Your Code

**Authentication - Before:**
```java
String consumerKey = "...";
String consumerSecret = "...";
String tokenKey = "...";
String tokenSecret = "...";
Jelp jelp = new Jelp(consumerKey, consumerSecret, tokenKey, tokenSecret);
```

**Authentication - After:**
```java
String apiKey = "YOUR_API_KEY_HERE";
Jelp jelp = new Jelp(apiKey);
```

**Category Handling - Before:**
```java
List<List<String>> categories = business.getCategories();
for (List<String> category : categories) {
    String name = category.get(0);  // "Sushi Bars"
    String alias = category.get(1); // "sushi"
}
```

**Category Handling - After:**
```java
List<Category> categories = business.getCategories();
for (Category category : categories) {
    String name = category.getTitle();  // "Sushi Bars"
    String alias = category.getAlias(); // "sushi"
}
```

### Step 4: Update pom.xml

```xml
<dependency>
    <groupId>com.justinbeltran</groupId>
    <artifactId>jelp</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>

<properties>
    <maven.compiler.source>21</maven.compiler.source>
    <maven.compiler.target>21</maven.compiler.target>
</properties>
```

### Step 5: Handle New Fields

Take advantage of new v3 fields:

```java
Business business = jelp.business("some-business-id");

// New fields available
String priceLevel = business.getPrice();  // "$", "$$", "$$$", "$$$$"
List<String> transactions = business.getTransactions();  // ["pickup", "delivery"]
List<String> photos = business.getPhotos();  // Array of photo URLs
Coordinate coords = business.getCoordinates();  // Direct coordinates
```

### Step 6: Handle Removed Fields

If you were using deprecated fields, update your code:

```java
// These fields are no longer available in v3:
// ❌ business.getMobile_url()
// ❌ business.getRating_img_url()
// ❌ business.getSnippet_text()

// Use these instead:
// ✅ business.getUrl() - Main business URL (works on mobile too)
// ✅ business.getRating() - Numeric rating value
// ✅ business.getReviews() - Full review objects (if available)
```

### API Response Differences

| Field | v2 | v3 | Notes |
|-------|----|----|-------|
| Authentication | OAuth 1.0a (4 keys) | Bearer Token (1 key) | Get new API key |
| Base URL | `http://api.yelp.com/v2` | `https://api.yelp.com/v3` | Automatic |
| Categories | `List<List<String>>` | `List<Category>` | Use `getAlias()` and `getTitle()` |
| Mobile URL | `mobile_url` | Removed | Use `url` instead |
| Rating Images | `rating_img_url*` | Removed | Use numeric `rating` |
| Snippets | `snippet_text`, `snippet_image_url` | Removed | Use `reviews` |
| Price | Not available | `price` | New field: "$" to "$$$$" |
| Transactions | Not available | `transactions` | New field |
| Photos | Single `image_url` | `photos` array | New field |
| Coordinates | Nested in `location` | Also at business level | New location |

### Testing Your Migration

1. Update to Java 21
2. Get new API key from Yelp Developers
3. Update authentication code
4. Run your application
5. Test search and business detail lookups
6. Verify new fields are accessible

### Need Help?

- Check the [README.md](README.md) for usage examples
- Review [Yelp Fusion API Documentation](https://docs.developer.yelp.com/)
- Open an issue on [GitHub](https://github.com/justinbeltran/jelp/issues)

---

## [0.0.1-SNAPSHOT] - 2013 (Legacy)

### Initial Release
- Yelp API v2 support
- OAuth 1.0a authentication
- Java 6+ compatibility
- Basic search and business lookup functionality

---

**Legend:**
- ✅ Added
- 🔄 Changed
- ❌ Removed
- ⚠️ Breaking Change
- 🐛 Bug Fix
- 🔒 Security
- 📝 Documentation
