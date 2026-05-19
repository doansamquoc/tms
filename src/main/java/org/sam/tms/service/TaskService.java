package org.sam.tms.service;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.sam.tms.dto.request.TaskCreationRequest;
import org.sam.tms.dto.response.TaskResponse;
import org.sam.tms.entity.Task;
import org.sam.tms.entity.User;
import org.sam.tms.enums.ErrorCode;
import org.sam.tms.event.TaskCreatedEvent;
import org.sam.tms.exception.BusinessException;
import org.sam.tms.mapper.TaskMapper;
import org.sam.tms.repository.TaskRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskService {
	EntityManager em;
	TaskMapper mapper;
	UserService userService;
	TaskRepository repository;
	ApplicationEventPublisher publisher;

	public TaskResponse create(Long assignorId, TaskCreationRequest request) {
		User assignor = em.getReference(User.class, assignorId);
		User assignee = em.getReference(User.class, request.assigneeId());

		if (!userService.existsById(assignee.getId())) {
			throw BusinessException.of(ErrorCode.ASSIGNEE_NOT_FOUND);
		}

		Task task = mapper.toEntity(request);
		task.setAssignor(assignor);
		task.setAssignee(assignee);

		Task saved = repository.save(task);
		publisher.publishEvent(new TaskCreatedEvent(saved));
		return mapper.toResponse(saved);
	}
}
