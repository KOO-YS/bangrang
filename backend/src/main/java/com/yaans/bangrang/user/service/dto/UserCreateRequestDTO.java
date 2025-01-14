package com.yaans.bangrang.user.service.dto;

import com.yaans.bangrang.user.domain.User;
import com.yaans.bangrang.user.domain.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateRequestDTO {

    // 이메일, 비밀번호, 닉네임, 상태
    @NotBlank(message = "이메일을 입력하세요")
    @Size(max = 100)
    private final String email;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Size(max = 70)
    private final String password;

    @NotBlank(message = "닉네임을 입력하세요")
    @Size(max = 50)
    private final String nickname;

    private final UserStatus userStatus = UserStatus.PENDING;

    @Builder
    public UserCreateRequestDTO(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public User toEntity() {
        return User.builder()
            .email(this.email)
            .password(this.password)
            .nickname(this.nickname)
            .userStatus(this.userStatus)
            .build();
    }
}
