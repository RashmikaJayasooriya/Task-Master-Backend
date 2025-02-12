package com.rashmika.task_master.util;

import com.rashmika.task_master.dto.TaskResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse {
    private int code;
    private String message;
    private Object data;

    public StandardResponse(int i, String tasksRetrieved, List<TaskResponseDto> data, HttpStatus httpStatus) {
    }
}
