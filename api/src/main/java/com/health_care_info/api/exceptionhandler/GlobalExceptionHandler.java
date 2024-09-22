package com.health_care_info.api.exceptionhandler;

import com.health_care_info.api.common.api.Api;
import com.health_care_info.api.common.error.CommonErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(
            Exception exception
    ){
        log.error("",exception);

        return ResponseEntity
                .status(500)
                .body(
                        Api.ERROR(CommonErrorCode.SERVER_ERROR)
                );
    }
}
