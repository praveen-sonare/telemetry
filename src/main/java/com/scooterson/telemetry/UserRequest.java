package com.scooterson.telemetry;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRequest {
    private @NotNull String email;
    private @NotNull String password;
}
