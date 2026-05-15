package com.sam.taskmanagement.mapper;

import com.sam.taskmanagement.dto.request.UserCreationRequest;
import com.sam.taskmanagement.dto.response.UserResponse;
import com.sam.taskmanagement.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	User toEntity(UserCreationRequest request);

	UserResponse toDto(User user);
}
