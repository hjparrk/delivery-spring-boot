package org.delivery.api.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.user.User;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.UserStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(User user) {
        if (user == null) throw new ApiException(ErrorCode.NULL_POINT, "User entity cannot be null");

        user.setStatus(UserStatus.REGISTERED);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

}
