package com.justinbeltran.jelp.model;

/**
 * Represents a business category
 * Yelp v3 uses objects with alias and title instead of nested arrays
 *
 * @param alias The category identifier/alias (e.g., "sushi", "japanese")
 * @param title The human-readable category name (e.g., "Sushi Bars", "Japanese")
 */
public record Category(String alias, String title) {

    // Backward compatibility getters
    public String getAlias() {
        return alias;
    }

    public String getTitle() {
        return title;
    }
}
