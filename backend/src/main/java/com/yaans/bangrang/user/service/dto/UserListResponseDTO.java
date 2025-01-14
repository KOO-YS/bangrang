package com.yaans.bangrang.user.service.dto;

import com.yaans.bangrang.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class UserListResponseDTO {

    private final List<User> userList;
    private final int totalPages;
    private final long totalSize;
    private final int currentPage;
    private final int currentSize;

    @Builder
    public UserListResponseDTO(List<User> userList, int totalPages, long totalSize, int currentPage, int currentSize) {
        this.userList = userList;
        this.totalPages = totalPages;
        this.totalSize = totalSize;
        this.currentPage = currentPage;
        this.currentSize = currentSize;
    }
}
