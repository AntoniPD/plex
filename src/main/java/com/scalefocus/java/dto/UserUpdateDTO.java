package com.scalefocus.java.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDTO {

  private @Size(min = 3, max = 15) String firstName;

  private @Size(min = 3, max = 15) String lastName;

  private @Size(max = 40) @Email String email;

  private @Size(min = 6, max = 20) String password;
}
