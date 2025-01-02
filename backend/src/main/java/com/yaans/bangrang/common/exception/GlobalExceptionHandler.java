package com.yaans.bangrang.common.exception;

import com.yaans.bangrang.common.log.ConsoleLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(value = {"com.yaans.bangrang"})
public class GlobalExceptionHandler {

    final HttpStatus INTERNAL_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeException(Exception e) {
        ConsoleLog consoleLog = new ConsoleLog()
            .append("GlobalExceptionHandler RuntimeException occurred")
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .append("type", "runtimeException")
            .append("cause", e.getCause());

        log.error(consoleLog.toString(), e);

        return ResponseEntity.status(INTERNAL_ERROR.value()).body(INTERNAL_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e) {
        ConsoleLog consoleLog = new ConsoleLog()
            .append("GlobalExceptionHandler Exception occurred")
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
            .append("type", "exception")
            .append("cause", e.getCause());

        log.error(consoleLog.toString(), e);

        return ResponseEntity.status(INTERNAL_ERROR.value()).body(INTERNAL_ERROR.getReasonPhrase());
    }
}
