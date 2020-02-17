package com.scalefocus.java.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data  public class SignUpRequest {
    private @NotBlank @Size(min = 3, max = 15) String firstName;

    private @NotBlank @Size(min = 3, max = 15) String lastName;

    private @NotBlank @Size(max = 40) @Email String email;

    private @NotBlank @Size(min = 6, max = 20) String password;

}
