package com.sam.taskmanagement.service;

import com.sam.taskmanagement.dto.request.TaskCreationRequest;
import com.sam.taskmanagement.dto.response.TaskResponse;
import com.sam.taskmanagement.entity.Task;
import com.sam.taskmanagement.entity.User;
import com.sam.taskmanagement.event.TaskCreatedEvent;
import com.sam.taskmanagement.mapper.TaskMapper;
import com.sam.taskmanagement.repository.TaskRepository;
import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskService {
	TaskMapper mapper;
	TaskRepository repository;
	EntityManager em;
	ApplicationEventPublisher publisher;
	private final UserService userService;
	
	public TaskResponse create(Long assignorId, TaskCreationRequest request) {
		User assignor = em.getReference(User.class, assignorId);
		User assignee = em.getReference(User.class, request.assigneeId());
		if (!userService.existsById(assignee.getId())) {
			throw new IllegalArgumentException("Assignee not found");
		}
		
		Task task = mapper.toEntity(request);
		task.setAssignor(assignor);
		task.setAssignee(assignee);
		
		Task saved = repository.save(task);
		publisher.publishEvent(new TaskCreatedEvent(saved));
		return mapper.toResponse(saved);
	}
}
