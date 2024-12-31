package com.yaans.bangrang.user.domain;

import com.yaans.bangrang.common.AuditEntity;
import com.yaans.bangrang.travel.domain.Travel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "br_user")
public class User  extends AuditEntity {


    @Id
    @Column(name = "USER_ID")
    private UUID id;

    @NotNull
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @NotNull
    @Column(name = "PASSWORD", nullable = false, length = 70)
    private String password;        // FIXME 암호화

    @NotNull
    @Column(name = "NICKNAME", unique = true, nullable = false, length = 50)
    private String nickname;

    @NotNull
    @Column(name = "USER_TYPE", unique = true, nullable = false, length = 20)
    private String userType;    // FIXME : -> to enum

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Travel> travels = new HashSet<>();

    @Builder
    public User(UUID id, String email, String password, String nickname, String userType, Set<Travel> travels) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.userType = userType;
        this.travels = travels;
    }
}
