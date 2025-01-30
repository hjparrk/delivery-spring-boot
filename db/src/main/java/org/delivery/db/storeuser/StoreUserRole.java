package org.delivery.db.storeuser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreUserRole {
    MASTER("master"),
    ADMIN("admin"),
    USER("user")
    ;

    private final String description;
}
