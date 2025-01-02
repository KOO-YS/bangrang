package com.yaans.bangrang.user.domain;

import com.yaans.bangrang.common.AuditEntity;
import com.yaans.bangrang.travel.domain.Travel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "br_user")
public class User  extends AuditEntity {


    @Id
    @UuidGenerator
    @Column(name = "USER_ID")
    private UUID id;

    @NotNull
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @NotNull
    @Column(name = "PASSWORD", nullable = false, length = 70)
    private String password;        // FIXME : 추후 SpringSecurity를 이용한 암호화

    @NotNull
    @Column(name = "NICKNAME", unique = true, nullable = false, length = 50)
    private String nickname;

    @NotNull
    @Column(name = "USER_STATUS", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserStatus userStatus = UserStatus.INACTIVE;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Travel> travels = new HashSet<>();

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
