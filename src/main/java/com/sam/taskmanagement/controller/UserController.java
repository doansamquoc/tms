package com.sam.taskmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sam.taskmanagement.dto.request.UserCreationRequest;
import com.sam.taskmanagement.dto.response.UserResponse;
import com.sam.taskmanagement.dto.api.ApiResponse;
import com.sam.taskmanagement.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
  UserService service;
  
  @PostMapping("")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<UserResponse> create(@RequestBody UserCreationRequest request) {
    return ApiResponse.of(service.create(request));
  }
}
