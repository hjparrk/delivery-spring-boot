package org.delivery.db.storeuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "store_users")
public class StoreUser extends BaseEntity {

    @Column(nullable = false)
    private Long storeId;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreUserRole role;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreUserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime deletedAt;

    private LocalDateTime lastLoginAt;
}
