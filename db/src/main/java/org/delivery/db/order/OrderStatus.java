package org.delivery.db.order;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    REGISTERED("registered"),
    UNREGISTERED("unregistered"),
    ORDERED("ordered"),
    ACCEPTED("accepted"),
    COOKING("cooking"),
    DELIVERING("delivering"),
    RECEIVED("delivered")
    ;

    private final String description;
}
