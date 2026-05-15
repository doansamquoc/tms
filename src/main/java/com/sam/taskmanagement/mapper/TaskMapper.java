package com.sam.taskmanagement.mapper;

import com.sam.taskmanagement.dto.request.TaskCreationRequest;
import com.sam.taskmanagement.dto.response.TaskResponse;
import com.sam.taskmanagement.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TaskMapper {
	Task toEntity(TaskCreationRequest request);
	
	TaskResponse toResponse(Task task);
}
