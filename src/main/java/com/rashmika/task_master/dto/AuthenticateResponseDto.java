package com.rashmika.task_master.dto;

import lombok.Data;
import org.keycloak.representations.AccessTokenResponse;

@Data
public class AuthenticateResponseDto {
    private AccessTokenResponse accessTokenResponse;
    private long userId;
}
