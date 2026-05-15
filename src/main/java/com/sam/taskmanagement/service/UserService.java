package com.sam.taskmanagement.service;

import com.sam.taskmanagement.dto.request.UserCreationRequest;
import com.sam.taskmanagement.dto.response.UserResponse;
import com.sam.taskmanagement.entity.User;
import com.sam.taskmanagement.mapper.UserMapper;
import com.sam.taskmanagement.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserMapper mapper;
    UserRepository repository;

    public UserResponse create(UserCreationRequest request) {
        User user = mapper.toEntity(request);
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return mapper.toDto(repository.save(user));
    }

    public boolean existsById(@NonNull Long id) {
        return repository.existsById(id);
    }
}
