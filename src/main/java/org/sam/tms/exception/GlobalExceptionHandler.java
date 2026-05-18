package org.sam.tms.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.sam.tms.enums.ErrorCode;
import org.sam.tms.i18n.MessageTranslator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GlobalExceptionHandler {
	MessageTranslator translator;

	@ExceptionHandler(BusinessException.class)
	ResponseEntity<ErrorResponse> handleBusiness(BusinessException e, HttpServletRequest request) {
		List<FieldViolation> violations = violationsTranslate(e.getViolations());

		ErrorCode errorCode = e.getErrorCode();
		String message = translator.of(errorCode.getKey());
		ErrorResponse response = new ErrorResponse(errorCode.getCode(), message, violations, request.getRequestURI());
		return ResponseEntity.status(errorCode.getStatus()).body(response);
	}

	@ExceptionHandler(Exception.class)
	ResponseEntity<ErrorResponse> handleGeneric(Exception e, HttpServletRequest request) {
		log.info("Exception Class: {}", e.getClass());

		ErrorCode errorCode = ErrorCode.SERVER_INTERNAL;
		String message = translator.of(errorCode.getKey());
		ErrorResponse response = new ErrorResponse(errorCode.getCode(), message, request.getRequestURI());
		return ResponseEntity.status(errorCode.getStatus()).body(response);
	}

	private List<FieldViolation> violationsTranslate(List<FieldViolation> violations) {
		if (violations == null || violations.isEmpty()) return List.of();
		return violations.stream().map(v -> {
			String message = translator.of(v.message());
			return new FieldViolation(v.field(), message, v.rejectedValue(), v.args());
		}).toList();
	}
}
