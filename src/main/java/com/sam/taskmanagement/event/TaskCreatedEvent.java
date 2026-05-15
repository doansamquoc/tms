package com.sam.taskmanagement.event;

import com.sam.taskmanagement.entity.Task;

public record TaskCreatedEvent(Task task) {}