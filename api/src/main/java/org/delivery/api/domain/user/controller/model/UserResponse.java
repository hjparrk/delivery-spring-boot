package org.delivery.api.domain.user.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.user.UserStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private UserStatus status;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private LocalDateTime lastLoginAt;
}
