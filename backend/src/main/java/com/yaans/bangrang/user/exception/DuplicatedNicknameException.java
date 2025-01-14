package com.yaans.bangrang.user.exception;

public class DuplicatedNicknameException extends RuntimeException {
    private static final String message = "사용할 수 없는 닉네임입니다.";

    public DuplicatedNicknameException() {
        super(message);
    }
}
