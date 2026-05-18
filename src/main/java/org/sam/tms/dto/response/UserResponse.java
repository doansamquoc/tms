package org.sam.tms.dto.response;

import org.sam.tms.enums.Gender;

import java.time.Instant;
import java.time.LocalDate;

public record UserResponse(
	Long id,
	String username,
	String email,
	String displayName,
	LocalDate dob,
	Gender gender,
	Instant createdAt,
	Instant updatedAt
) {}
