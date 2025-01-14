package com.yaans.bangrang.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PageableDTO {

    private int page = 10;
    private int size = 10;

    @Builder
    public PageableDTO(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
