package com.rashmika.task_master.controller;

import com.rashmika.task_master.dto.AuthenticateResponseDto;
import com.rashmika.task_master.dto.UserDto;
import com.rashmika.task_master.service.UserService;
import com.rashmika.task_master.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        String taskMaster = userService.createUser(
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPassword()
        );

        return ResponseEntity.ok("User created in Keycloak with ID: " + taskMaster);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<StandardResponse> authenticateUser(@RequestBody UserDto userDto) {
        AuthenticateResponseDto authenticateResponseDto = userService.authenticateUser(
                userDto.getUsername(),
                userDto.getPassword()
        );

        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "User authenticated successfully", authenticateResponseDto), HttpStatus.OK);
    }

}
