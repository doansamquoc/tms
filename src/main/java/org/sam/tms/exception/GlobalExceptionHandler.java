package org.sam.tms.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.sam.tms.enums.ErrorCode;
import org.sam.tms.i18n.MessageTranslator;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

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

    @ExceptionHandler(NoResourceFoundException.class)
    ResponseEntity<ErrorResponse> handleNoResourceFound(NoResourceFoundException e, HttpServletRequest request) {
        log.error("CATCH {}: {}", e.getClass(), e.getMessage());

        ErrorCode errorCode = ErrorCode.NO_RESOURCE_FOUND;
        String message = translator.of(errorCode.getKey());
        ErrorResponse response = new ErrorResponse(
            errorCode.getCode(),
            message,
            request.getRequestURI(),
            e.getMessage()
        );
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("CATCH {}: {}", e.getClass(), e.getMessage());

        List<FieldViolation> violations = e.getFieldErrors().stream().map(f -> {
            String message = translator.of(f.getDefaultMessage());
            Object rejectedValue = f.getRejectedValue();
            return new FieldViolation(f.getField(), message, rejectedValue);
        }).toList();

        ErrorCode errorCode = ErrorCode.VALIDATION_FAILED;
        String message = translator.of(errorCode.getKey());
        ErrorResponse response = new ErrorResponse(errorCode.getCode(), message, violations, request.getRequestURI());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("CATCH {}: {}", e.getClass(), e.getMessage());
        ErrorCode errorCode = ErrorCode.JSON_PARSER_ERROR;
        String message = translator.of(errorCode.getKey());
        ErrorResponse response = new ErrorResponse(errorCode.getCode(), message, request.getRequestURI(), e.getMessage());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleGeneric(Exception e, HttpServletRequest request) {
        log.info("CATCH {}", e.getClass());

        ErrorCode errorCode = ErrorCode.SERVER_INTERNAL;
        String message = translator.of(errorCode.getKey());
        ErrorResponse response = new ErrorResponse(errorCode.getCode(), message, request.getRequestURI());
        return ResponseEntity.status(errorCode.getStatus()).body(response);
    }

    private List<FieldViolation> violationsTranslate(List<FieldViolation> violations) {
        if (violations == null || violations.isEmpty()) return List.of();
        return violations.stream().map(v -> {
            String message = translator.of(v.message(), v.args());
            return new FieldViolation(v.field(), message, v.rejectedValue(), v.args());
        }).toList();
    }
}
