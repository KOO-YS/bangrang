package com.yaans.bangrang.user.exception;

import com.yaans.bangrang.common.log.ConsoleLog;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(value = {"com.yaans.bangrang.user"})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionHandler {

    final HttpStatus INTERNAL_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;


    /**
     * JPA
     * ConstraintViolationException : 제약 조건 위배되었을 때 발생
     * DataIntegrityViolationException : 데이터의 삽입/수정이 무결성 제약 조건을 위반할 때 발생
     *
     */
    @ExceptionHandler(value = { ConstraintViolationException.class, DataIntegrityViolationException.class})
    protected ResponseEntity<String> handleDataException(Exception e) {
        ConsoleLog consoleLog = new ConsoleLog()
            .append("UserExceptionHandler "+e.getClass().getSimpleName() + " occurred")
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .append("type", "runtimeException")
            .append("cause", e.getCause());

        log.error(consoleLog.toString(), e);

        return ResponseEntity.status(INTERNAL_ERROR.value()).body(INTERNAL_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(DuplicatedNicknameException.class)
    public ResponseEntity<String> runtimeException(Exception e) {
        ConsoleLog consoleLog = new ConsoleLog()
            .append("UserExceptionHandler DuplicatedNicknameException occurred")
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .append("type", "runtimeException")
            .append("cause", e.getCause());

        log.error(consoleLog.toString(), e);

        return ResponseEntity.status(INTERNAL_ERROR.value()).body(INTERNAL_ERROR.getReasonPhrase());
    }
}
