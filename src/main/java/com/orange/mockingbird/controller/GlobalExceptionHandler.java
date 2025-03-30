package com.orange.mockingbird.controller;

import com.orange.mockingbird.model.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseBody
    public ApiResponse<String> onIllegalArgumentException(IllegalArgumentException e) {
        return ApiResponse.failure(400, e.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ApiResponse<String> onBizException(Exception e) {
        return ApiResponse.failure(500001, "get error");
    }
}