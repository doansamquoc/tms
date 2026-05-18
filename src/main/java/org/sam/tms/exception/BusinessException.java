package org.sam.tms.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.sam.tms.enums.ErrorCode;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusinessException extends RuntimeException {
	ErrorCode errorCode;
	List<FieldViolation> violations;
	String[] args;

	public static BusinessException of(ErrorCode errorCode) {
		return new BusinessException(errorCode, List.of(), null);
	}

	public static BusinessException of(ErrorCode errorCode, List<FieldViolation> violations) {
		return new BusinessException(errorCode, violations, null);
	}

	public static BusinessException of(ErrorCode errorCode, FieldViolation violation) {
		return new BusinessException(errorCode, List.of(violation), null);
	}

	public static BusinessException of(ErrorCode errorCode, List<FieldViolation> violations, String... args) {
		return new BusinessException(errorCode, violations, args);
	}
}
