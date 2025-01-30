package org.delivery.db.storeuser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreUserStatus {
    REGISTERED("registered"),
    UNREGISTERED("unregistered")
    ;

    private final String description;
}
