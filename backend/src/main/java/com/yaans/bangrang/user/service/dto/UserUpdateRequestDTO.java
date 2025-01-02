package com.yaans.bangrang.user.service.dto;

import com.yaans.bangrang.user.domain.User;
import com.yaans.bangrang.user.domain.UserStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserUpdateRequestDTO {

    @Size(max = 70)
    private final String password;

    @Size(max = 50)
    private final String nickname;

    private final UserStatus userStatus;

    @Builder
    public UserUpdateRequestDTO(String password, String nickname, UserStatus userStatus) {
        this.password = password;
        this.nickname = nickname;
        this.userStatus = userStatus;
    }

    public User toEntity() {
        return User.builder()
            .password(this.password)
            .nickname(this.nickname)
            .userStatus(this.userStatus)
            .build();
    }
}
