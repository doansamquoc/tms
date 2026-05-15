package com.sam.taskmanagement.dto.request;

import com.sam.taskmanagement.enums.Priority;
import com.sam.taskmanagement.enums.TaskStatus;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record TaskCreationRequest(
    String name,
    
    String description,
    
    TaskStatus status,
    
    Priority priority,
    
    Instant deadlineAt,
    
    @NotNull(message = "task.assginee_id.required") Long assigneeId
) {}
