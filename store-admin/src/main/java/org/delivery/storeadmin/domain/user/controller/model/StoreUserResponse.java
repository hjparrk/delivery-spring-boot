package org.delivery.storeadmin.domain.user.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.storeuser.StoreUserRole;
import org.delivery.db.storeuser.StoreUserStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreUserResponse {

    private UserResponse user;
    private StoreResponse store;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserResponse {
        private Long id;
        private String email;
        private StoreUserStatus status;
        private StoreUserRole role;
        private LocalDateTime createdAt;
        private LocalDateTime deletedAt;
        private LocalDateTime lastLoginAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class StoreResponse {
        private Long id;
        private String name;
    }
}
