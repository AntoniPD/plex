package com.scalefocus.java.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

  private @NotNull(message = "firstName field is required") @Size(min = 3, max = 15) String firstName;

  private @NotNull(message = "lastName field is required") @Size(min = 3, max = 15) String lastName;

  private @NotNull(message = "email field is required") @Size(max = 40) @Email String email;

  private @NotNull(message = "password should not be null") @Size(min = 6, max = 20) String password;

}
