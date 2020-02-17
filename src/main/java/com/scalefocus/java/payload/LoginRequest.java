package com.scalefocus.java.payload;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    private @NotBlank String email;

    private @NotBlank String password;

}