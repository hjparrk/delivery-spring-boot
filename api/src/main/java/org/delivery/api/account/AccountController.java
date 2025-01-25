package org.delivery.api.account;

import lombok.RequiredArgsConstructor;
import org.delivery.api.account.model.AccountMeResponse;
import org.delivery.api.common.api.ApiResponse;
import org.delivery.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository accountRepository;

    @GetMapping("/me")
    public ApiResponse<AccountMeResponse> save() {
        var response = AccountMeResponse.builder()
                .name("김영희")
                .email("younghee@example.com")
                .createdAt(LocalDateTime.now())
                .build();
        return ApiResponse.OK(response);
    }
}
