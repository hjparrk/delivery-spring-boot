package org.delivery.db.ordermenu;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderMenuStatus {
    REGISTERED("registered"),
    UNREGISTERED("unregistered")
    ;

    private final String description;
}
