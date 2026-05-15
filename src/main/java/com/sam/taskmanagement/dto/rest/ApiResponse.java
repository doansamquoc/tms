package com.sam.taskmanagement.dto.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public record ApiResponse<T>(T data, String message) {
  public static <T> ApiResponse<T> of(T data, String message) {
    return new ApiResponse<T>(data, message);
  }

  public static <T> ApiResponse<T> of(T data) {
    return new ApiResponse<T>(data, "Successfully");
  }

  public static <T> ApiResponse<T> of(String message) {
    return new ApiResponse<T>(null, message);
  }

  public static <T> ApiResponse<T> of() {
    return new ApiResponse<T>(null, null);
  }
}
