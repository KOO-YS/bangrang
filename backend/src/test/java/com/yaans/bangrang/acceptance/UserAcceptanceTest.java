package com.yaans.bangrang.acceptance;

import com.yaans.bangrang.user.domain.User;
import com.yaans.bangrang.user.domain.UserStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class UserAcceptanceTest {

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)   // method의 _를 공백으로 변경
    class 회원_생성 {

        @Nested
        @DisplayName("회원가입")
        class signUp {

//            private User 회원 = fixture.엔티티생성();

            @Test
            @DisplayName("유효한 회원 정보로 회원가입")
            void isPending() {
//                assertThat(회원.getUserStatus()).isEqualTo(UserStatus.PENDING);
            }

            @Test
            @DisplayName("이미 존재하는 정보로 회원가입")
            void isActive () {

            }

        }
    }
}
