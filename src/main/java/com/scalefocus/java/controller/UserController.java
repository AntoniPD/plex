package com.scalefocus.java.controller;

import com.scalefocus.java.dto.UserDTO;
import com.scalefocus.java.dto.UserResponseDTO;
import com.scalefocus.java.dto.UserUpdateDTO;
import com.scalefocus.java.exception.AlreadyExistException;
import com.scalefocus.java.exception.NotFoundException;
import com.scalefocus.java.exception.ValidationException;
import com.scalefocus.java.payload.ApiResponse;
import com.scalefocus.java.payload.JwtAuthenticationResponse;
import com.scalefocus.java.payload.LoginRequest;
import com.scalefocus.java.service.AuthService;
import com.scalefocus.java.service.UserService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {
  public static final String BASE_URL = "/api/users";

  private final UserService userService;

  private final AuthService authService;

  @Autowired
  public UserController(UserService userService, AuthService authService) {
    this.userService = userService;
    this.authService = authService;
  }

  @PostMapping("/signup")
  public ResponseEntity<ApiResponse> registerUser(
      @RequestBody @Valid UserDTO user,
      Errors errors) throws ValidationException, AlreadyExistException {
    if (errors.hasErrors()) {
      throw new ValidationException(errors.getFieldErrors());
    }

    String userName = authService.registerUser(user);

    return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "User successfully registered with name " + userName), HttpStatus.CREATED);
  }

  @PostMapping("/signin")
  public ResponseEntity<JwtAuthenticationResponse> loginUser(@RequestBody LoginRequest loginRequest) {
    return new ResponseEntity<>(authService.authenticateUser(loginRequest), HttpStatus.OK);

  }

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> getUsers() {
    return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) throws NotFoundException {
    return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponseDTO> updateUser(
      @PathVariable @NotNull Long id, @RequestBody @Valid UserUpdateDTO user,
      Errors errors) throws NotFoundException, AlreadyExistException, ValidationException {
    if (errors.hasErrors()) {
      throw new ValidationException(errors.getFieldErrors());
    }
    UserResponseDTO userResponseDTO = userService.updateUser(user, id);

    return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deactivateUser(@PathVariable @NotNull Long id) throws NotFoundException {
    userService.deactivateUser(id);
    return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "User successfuly deleted"), HttpStatus.OK);
  }

  @PutMapping("/activate/{id}")
  public ResponseEntity<ApiResponse> activateUser(@PathVariable @NotNull Long id) throws NotFoundException {
    userService.activateUser(id);
    return new ResponseEntity<>(new ApiResponse(Boolean.TRUE, "User successfuly restored"), HttpStatus.OK);
  }
}
