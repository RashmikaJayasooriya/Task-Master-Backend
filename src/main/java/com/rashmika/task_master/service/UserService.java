package com.rashmika.task_master.service;

import com.rashmika.task_master.dto.AuthenticateResponseDto;
import org.keycloak.representations.AccessTokenResponse;

public interface UserService {
    AuthenticateResponseDto authenticateUser(String username, String currentPassword);

    String createUser(String username, String email,
                             String firstName, String lastName,
                             String password);
}
