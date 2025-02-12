package com.rashmika.task_master.service.impl;

import com.rashmika.task_master.dto.AuthenticateResponseDto;
import com.rashmika.task_master.entity.User;
import com.rashmika.task_master.exception.AuthenticationException;
import com.rashmika.task_master.exception.UserCreationException;
import com.rashmika.task_master.repository.UserRepository;
import com.rashmika.task_master.service.UserService;
import com.rashmika.task_master.util.KeycloakSecurityUtil;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final KeycloakSecurityUtil keycloak;
    private final UserRepository userRepository;

    private String getCreatedId(Response response) {
        String location = response.getLocation().toString();
        return location.substring(location.lastIndexOf("/") + 1);
    }

    public AuthenticateResponseDto authenticateUser( String username, String currentPassword) {

        User user = userRepository.findByUsername(username).orElseThrow(() -> new AuthenticationException("User not found"));

        AccessTokenResponse accessTokenResponse = keycloak.authenticateUser(username, currentPassword);

        AuthenticateResponseDto authenticateResponseDto = new AuthenticateResponseDto();
        authenticateResponseDto.setUserId(user.getId());
        authenticateResponseDto.setAccessTokenResponse(accessTokenResponse);

        return authenticateResponseDto;
    }


    public String createUser(String username, String email,
                             String firstName, String lastName,
                             String password) {
        try {

            userRepository.findByUsername(username).ifPresent(user -> {
                throw new UserCreationException("User already exists");
            });

            UserRepresentation user = new UserRepresentation();
            user.setUsername(username);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setEnabled(true);
            user.setEmailVerified(true);
            user.setRealmRoles(Collections.singletonList("user"));

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setTemporary(false);
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(password);

            user.setCredentials(Collections.singletonList(credential));

            Response response = keycloak.getKeycloakInstance().realm("task_master").users().create(user);
            String createdUserId = null;
            if (response.getStatus() == 201) {
                createdUserId = getCreatedId(response);

                User user1 = User.builder()
                        .keycloakId(createdUserId)
                        .username(username)
                        .email(email)
                        .firstName(firstName)
                        .lastName(lastName)
                        .build();

                userRepository.save(user1);

            } else {
                throw new UserCreationException("Failed to create user: " + response.getStatusInfo());
            }

            return createdUserId;

        } catch (Exception e) {
            System.err.println("Authentication failed: " + e.getMessage());
            return "Failed";
        }
    }
}

