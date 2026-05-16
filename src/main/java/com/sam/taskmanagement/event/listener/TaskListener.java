package com.sam.taskmanagement.event.listener;

import com.sam.taskmanagement.dto.response.NotificationResponse;
import com.sam.taskmanagement.entity.Task;
import com.sam.taskmanagement.event.TaskCreatedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskListener {
    SimpMessagingTemplate template;
    
    @EventListener
    public void handlerTaskCreated(TaskCreatedEvent event) {
        Task task = event.task();
        
        NotificationResponse notification = new NotificationResponse(
            "You have a new task",
            task.getDescription()
        );
        
        String assigneeId = String.valueOf(task.getAssignee().getId());
        if (assigneeId != null && !assigneeId.isBlank()) {
            template.convertAndSendToUser(assigneeId, "/queue/notifications", notification);
        }
    }
}
