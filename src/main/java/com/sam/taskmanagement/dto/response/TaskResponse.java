package com.sam.taskmanagement.dto.response;

import com.sam.taskmanagement.enums.Priority;
import com.sam.taskmanagement.enums.TaskStatus;

import java.time.Instant;

public record TaskResponse(
    String name,
    String description,
    TaskStatus status,
    Priority priority,
    Instant deadlineAt,
    Instant completedAt,
    Instant canceledAt,
    Long assignorId,
    Long assigneeId,
    Instant createdAt,
    Instant updatedAt
) {}
