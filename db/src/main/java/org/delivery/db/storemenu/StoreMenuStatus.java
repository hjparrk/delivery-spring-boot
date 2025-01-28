package org.delivery.db.storemenu;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreMenuStatus {

    REGISTERED("registered"),
    UNREGISTERED("unregistered")
    ;

    private final String description;
}
