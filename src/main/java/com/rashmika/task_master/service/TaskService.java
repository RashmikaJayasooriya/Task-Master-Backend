package com.rashmika.task_master.service;

import com.rashmika.task_master.dto.TaskRequestDto;
import com.rashmika.task_master.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskResponseDto addTask(TaskRequestDto taskRequestDto);

    TaskResponseDto completeTask(Long taskId, Long userId);

    String deleteTask(Long taskId, Long userId);

    List<TaskResponseDto> viewAllTasks(Long userId);

    TaskResponseDto viewTask(Long taskId, Long userId);

    List<TaskResponseDto> viewCompletedTasks(Long userId);

    List<TaskResponseDto> viewPendingTasks(Long userId);
}
