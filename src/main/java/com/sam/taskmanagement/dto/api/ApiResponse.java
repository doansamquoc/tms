package com.sam.taskmanagement.dto.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public record ApiResponse<T>(T data, String message) {
	public static <T> ApiResponse<T> of(T data, String message) {
		return new ApiResponse<>(data, message);
	}

	public static <T> ApiResponse<T> of(T data) {
		return new ApiResponse<>(data, "Successfully");
	}

	public static <T> ApiResponse<T> of(String message) {
		return new ApiResponse<>(null, message);
	}

	public static <T> ApiResponse<T> of() {
		return new ApiResponse<>(null, null);
	}
}
