package com.yaans.bangrang.common.log;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public class ConsoleLog {

    private final StringBuilder stringBuilder;

    public ConsoleLog() {
        // TODO : initialCapacity 크기 정하기
        this.stringBuilder = new StringBuilder();
    }

    public ConsoleLog append(String str) {
        stringBuilder.append('\n').append(str).append('\n');
        return this;
    }

    public ConsoleLog append(String key, Object value) {
        stringBuilder.append(key).append(" : ").append(value).append('\n');
        return this;
    }

    public ConsoleLog httpStatus(HttpStatus httpStatus) {
        this.append("statusCode", httpStatus.value());
        return this;
    }

    @Override
    public String toString() {
        return this.stringBuilder.toString();
    }
}
