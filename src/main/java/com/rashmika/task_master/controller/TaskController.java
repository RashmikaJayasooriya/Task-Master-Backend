package com.rashmika.task_master.controller;

import com.rashmika.task_master.dto.TaskRequestDto;
import com.rashmika.task_master.dto.TaskResponseDto;
import com.rashmika.task_master.service.TaskService;
import com.rashmika.task_master.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@PreAuthorize("hasRole('user')")
public class TaskController {

    private final TaskService taskService;

    //    Add a task.
    @PostMapping("/add")
    public ResponseEntity<StandardResponse> addTask(@RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto data = taskService.addTask(taskRequestDto);
        return new ResponseEntity<StandardResponse>(new StandardResponse(202, "Task added successfully", data), HttpStatus.CREATED);
    }

    //    Mark a task as completed
    @PutMapping("/complete")
    public ResponseEntity<StandardResponse> completeTask(@RequestParam Long taskId, @RequestParam Long userId) {
        TaskResponseDto data = taskService.completeTask(taskId, userId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(202, "Task completed", data), HttpStatus.OK);
    }

    //    Delete a task
    @DeleteMapping("/delete")
    public ResponseEntity<StandardResponse> deleteTask(@RequestParam Long taskId, @RequestParam Long userId) {
        String data = taskService.deleteTask(taskId, userId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(202, "Task deleted", data), HttpStatus.OK);
    }

    //    View the list of tasks
    @GetMapping("/view-all")
    public ResponseEntity<StandardResponse> viewTasks(@RequestParam Long userId) {
        List<TaskResponseDto> data = taskService.viewAllTasks(userId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(202, "Tasks retrieved", data), HttpStatus.OK);
    }

    //    View specific task
    @GetMapping("/view")
    public ResponseEntity<StandardResponse> viewTask(@RequestParam Long taskId, @RequestParam Long userId) {
        TaskResponseDto data = taskService.viewTask(taskId, userId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(202, "Task retrieved", data), HttpStatus.OK);
    }

    //    View the list of completed tasks
    @GetMapping("/view-completed")
    public ResponseEntity<StandardResponse> viewCompletedTasks(@RequestParam Long userId) {
        List<TaskResponseDto> data = taskService.viewCompletedTasks(userId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(202, "Completed tasks retrieved", data), HttpStatus.OK);
    }

    //    View the list of pending tasks
    @GetMapping("/view-pending")
    public ResponseEntity<StandardResponse> viewPendingTasks(@RequestParam Long userId) {
        List<TaskResponseDto> data = taskService.viewPendingTasks(userId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(202, "Pending tasks retrieved", data), HttpStatus.OK);
    }

}
