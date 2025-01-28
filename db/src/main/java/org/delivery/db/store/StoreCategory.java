package org.delivery.db.store;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreCategory {
    KOREAN("Korean", "Korean"),
    CHINESE("Chinese", "Chinese"),
    JAPANESE("Japanese", "Japanese"),
    AMERICAN("American", "American"),
    ITALIAN("Italian", "Italian"),
    FRENCH("French", "French")
    ;

    private final String display;
    private final String description;
}
