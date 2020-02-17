package com.scalefocus.java.security;

import com.scalefocus.java.domain.local.User;
import com.scalefocus.java.exception.NotFoundException;
import com.scalefocus.java.repository.local.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailService implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public UserDetailService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String email) {
    User user = userRepository.getUserByEmail(email)
        .orElseThrow(() ->
            new UsernameNotFoundException("User not found with email : " + email)
        );

    return UserPrincipal.create(user);
  }

  @Transactional(rollbackFor = NotFoundException.class)
  public UserDetails loadUserById(Long id) throws NotFoundException {
    User user = userRepository.findById(id).orElseThrow(
        () -> new NotFoundException("User with id: " + id + " not found"));

    return UserPrincipal.create(user);
  }
}
