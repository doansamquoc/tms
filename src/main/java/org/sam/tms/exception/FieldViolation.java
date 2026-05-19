package org.sam.tms.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record FieldViolation(String field, String message, Object rejectedValue, String[] args) {
	public FieldViolation(String field, String message) {
		this(field, message, null, null);
	}

	public FieldViolation(String field, String message, Object rejectedValue) {
		this(field, message, rejectedValue, null);
	}
}
