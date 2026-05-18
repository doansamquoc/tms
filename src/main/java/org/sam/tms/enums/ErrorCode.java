package org.sam.tms.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
	SERVER_INTERNAL(5000, HttpStatus.INTERNAL_SERVER_ERROR, "server.internal");

	int code;
	HttpStatus status;
	String key;
}
