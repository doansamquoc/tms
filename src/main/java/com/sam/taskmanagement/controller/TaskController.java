package com.sam.taskmanagement.controller;

import com.sam.taskmanagement.dto.request.TaskCreationRequest;
import com.sam.taskmanagement.dto.response.TaskResponse;
import com.sam.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskController {
    TaskService service;
    
    @PostMapping("")
    public TaskResponse create(@Valid @RequestBody TaskCreationRequest request) {
        return service.create(1L, request);
    }
    
    @GetMapping("")
    public String helloWorld() {
        return "Hello World! Brook! How are u today?";
    }
}
