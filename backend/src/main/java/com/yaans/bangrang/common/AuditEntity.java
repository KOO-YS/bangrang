package com.yaans.bangrang.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@MappedSuperclass
public abstract class AuditEntity {

    @NotNull
    @Column(name = "CREATED_AT", nullable = false)
    private Instant createdAt = Instant.now();

    @NotNull
    @Column(name = "UPDATED_AT", nullable = false)
    private Instant updatedAt = Instant.now();

}
