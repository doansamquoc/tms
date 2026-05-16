package com.sam.taskmanagement.entity;

import com.sam.taskmanagement.enums.Priority;
import com.sam.taskmanagement.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task extends BaseEntity {
  @Column(name = "name")
  String name;
  
  @Column(name = "description")
  String description;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  TaskStatus status;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "priority")
  Priority priority;
  
  @Column(name = "deadline_at")
  Instant deadlineAt;
  
  @Column(name = "completed_at")
  Instant completedAt;
  
  @Column(name = "canceled_at")
  Instant canceledAt;
  
  @JoinColumn(name = "assignor_id")
  @ManyToOne(fetch = FetchType.LAZY)
  User assignor;
  
  @JoinColumn(name = "assignee_id")
  @ManyToOne(fetch = FetchType.LAZY)
  User assignee;
}
