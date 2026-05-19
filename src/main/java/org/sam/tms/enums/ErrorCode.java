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
    SERVER_INTERNAL(5000, HttpStatus.INTERNAL_SERVER_ERROR, "server.internal"),

    // Assignee
    ASSIGNEE_NOT_FOUND(4000, HttpStatus.NOT_FOUND, "assignee.not_found"),

    NO_RESOURCE_FOUND(4400, HttpStatus.NOT_FOUND, "resource.not_found"),
    VALIDATION_FAILED(4401, HttpStatus.BAD_REQUEST, "validation.failed"),
    JSON_PARSER_ERROR(4402, HttpStatus.BAD_REQUEST, "json_parser_error"),
    // More...
    ;

    int code;
    HttpStatus status;
    String key;
}
