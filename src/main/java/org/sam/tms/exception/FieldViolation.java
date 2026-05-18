package org.sam.tms.exception;

public record FieldViolation(String field, String message, String rejectedValue, String[] args) {
	public FieldViolation(String field, String message) {
		this(field, message, null, null);
	}
}
