package com.scalefocus.java.service;

import com.scalefocus.java.domain.local.Role;
import com.scalefocus.java.domain.local.RoleName;
import com.scalefocus.java.domain.local.User;
import com.scalefocus.java.dto.UserDTO;
import com.scalefocus.java.exception.AlreadyExistException;
import com.scalefocus.java.exception.GeneralException;
import com.scalefocus.java.mapper.UserMapper;
import com.scalefocus.java.payload.JwtAuthenticationResponse;
import com.scalefocus.java.payload.LoginRequest;
import com.scalefocus.java.repository.local.RoleRepository;
import com.scalefocus.java.repository.local.UserRepository;
import com.scalefocus.java.security.JwtTokenProvider;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private AuthenticationManager authenticationManager;

  private UserRepository userRepository;

  private RoleRepository roleRepository;

  private PasswordEncoder passwordEncoder;

  private JwtTokenProvider tokenProvider;

  private UserMapper userMapper;

  @Autowired
  public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,
      JwtTokenProvider tokenProvider, UserMapper userMapper) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.tokenProvider = tokenProvider;
    this.userMapper = userMapper;
  }

  public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getEmail(),
            loginRequest.getPassword()
        )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.generateToken(authentication);
    return new JwtAuthenticationResponse(jwt);
  }

  public String registerUser(UserDTO signUpRequest) throws AlreadyExistException {

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      throw new AlreadyExistException("User with this email already exists");
    }
    // Creating user's account
    User user = userMapper.userDTOtoUser(signUpRequest);

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
        .orElseThrow(() -> new GeneralException("User Role not set."));

    user.setRoles(Collections.singleton(userRole));

    User result = userRepository.save(user);

    return result.getFirstName();
  }
}