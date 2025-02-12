package com.rashmika.task_master.service.impl;

import com.rashmika.task_master.dto.TaskRequestDto;
import com.rashmika.task_master.dto.TaskResponseDto;
import com.rashmika.task_master.entity.Task;
import com.rashmika.task_master.entity.User;
import com.rashmika.task_master.exception.TaskNotFoundException;
import com.rashmika.task_master.exception.UserNotFoundException;
import com.rashmika.task_master.repository.TaskRepository;
import com.rashmika.task_master.repository.UserRepository;
import com.rashmika.task_master.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {

        User user = userRepository.findById(taskRequestDto.getUserId()).orElseThrow(() -> new UserNotFoundException("User not found for the id: " + 1L));

        Task save = taskRepository.save(
                Task.builder()
                        .title(taskRequestDto.getTitle())
                        .description(taskRequestDto.getDescription())
                        .completed(false)
                        .user(user)
                        .build());

        return TaskResponseDto.builder()
                .id(save.getId())
                .title(save.getTitle())
                .description(save.getDescription())
                .completed(save.isCompleted())
                .build();
    }

    @Override
    public TaskResponseDto completeTask(Long taskId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found for the id: " + userId));
        Task task = taskRepository.findByIdAndUser(taskId, user).orElseThrow(() -> new TaskNotFoundException("Task not found for the id: " + taskId));

        task.setCompleted(true);
        taskRepository.save(task);
        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .build();
    }

    @Override
    public String deleteTask(Long taskId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found for the id: " + userId));
        Task task = taskRepository.findByIdAndUser(taskId, user).orElseThrow(() -> new TaskNotFoundException("Task not found for the id: " + taskId));
        taskRepository.delete(task);
        return "Task deleted successfully";
    }

    @Override
    public List<TaskResponseDto> viewAllTasks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found for the id: " + userId));
        List<Task> tasks = taskRepository.findAllByUser(user);
        return tasks.stream()
                .map(task -> TaskResponseDto.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .completed(task.isCompleted())
                        .build())
                .toList();
    }

    @Override
    public TaskResponseDto viewTask(Long taskId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found for the id: " + userId));
        Task task = taskRepository.findByIdAndUser(taskId, user).orElseThrow(() -> new TaskNotFoundException("Task not found for the id: " + taskId));
        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .build();
    }

    @Override
    public List<TaskResponseDto> viewCompletedTasks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found for the id: " + userId));
        List<Task> tasks = taskRepository.findAllByCompletedEqualsAndUser(true, user);
        return tasks.stream()
                .map(task -> TaskResponseDto.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .completed(task.isCompleted())
                        .build())
                .toList();
    }

    @Override
    public List<TaskResponseDto> viewPendingTasks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found for the id: " + userId));
        List<Task> tasks = taskRepository.findAllByCompletedEqualsAndUser(false, user);
        return tasks.stream()
                .map(task -> TaskResponseDto.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .completed(task.isCompleted())
                        .build())
                .toList();
    }
}
