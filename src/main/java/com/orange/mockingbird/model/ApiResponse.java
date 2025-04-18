package com.orange.mockingbird.model;

public class ApiResponse<T> {
    private final int code;
    private final T data;
    private final String message;

    public ApiResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }


    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(200, data, "success");
    }


    public static <T> ApiResponse<T> failure(int code, String message) {
        return new ApiResponse<T>(code, null, message);
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
