package org.sam.tms.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorResponse(
	int code,
	String message,
	List<FieldViolation> violations,
	String path,
	LocalDateTime timestamp,
	String trace
) {
	public ErrorResponse(int code, String message, List<FieldViolation> violations, String path) {
		this(code, message, violations, path, LocalDateTime.now(), null);
	}

	public ErrorResponse(int code, String message, String path) {
		this(code, message, List.of(), path, LocalDateTime.now(), null);
	}

	public ErrorResponse(int code, String message, List<FieldViolation> violations, String path, String trace) {
		this(code, message, violations, path, LocalDateTime.now(), trace);
	}

	public ErrorResponse(int code, String message, String path, String trace) {
		this(code, message, List.of(), path, LocalDateTime.now(), trace);
	}
}
