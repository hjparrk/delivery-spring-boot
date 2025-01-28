package org.delivery.db.store;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreStatus {
    REGISTERED("registered"),
    UNREGISTERED("unregistered")
    ;

    private final String description;
}
