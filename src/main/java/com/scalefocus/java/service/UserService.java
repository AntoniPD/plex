package com.scalefocus.java.service;

import com.scalefocus.java.domain.local.User;
import com.scalefocus.java.dto.UserDTO;
import com.scalefocus.java.dto.UserResponseDTO;
import com.scalefocus.java.dto.UserUpdateDTO;
import com.scalefocus.java.exception.AlreadyExistException;
import com.scalefocus.java.exception.NotFoundException;
import com.scalefocus.java.mapper.UserMapper;
import com.scalefocus.java.repository.local.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;
  private UserMapper userMapper;

  @Autowired
  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public Long createUser(UserDTO userDTO) throws AlreadyExistException {

    Optional<User> optionalUser = userRepository.getUserByEmail(userDTO.getEmail());
    if (optionalUser.isPresent()) {
      throw new AlreadyExistException("user with this email already exists");
    }

    User user = userMapper.userDTOtoUser(userDTO);
    return userRepository.save(user).getId();
  }

  public UserResponseDTO getUser(Long id) throws NotFoundException {

    Optional<User> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent() && optionalUser.get().isActive()) {
      return userMapper.userToUserResponseDTO(optionalUser.get());
    } else {
      throw new NotFoundException("user with that id not found");
    }
  }

  public List<UserResponseDTO> getUsers() {
    List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> userMapper.userToUserResponseDTO(user))
                .filter(UserResponseDTO::isActive)
                .collect(Collectors.toList());
  }

  public UserResponseDTO updateUser(UserUpdateDTO userUpdateDTO, Long id) throws NotFoundException, AlreadyExistException {

    Optional<User> savedUser = userRepository.findById(id);
    if (!savedUser.isPresent()) {
      throw new NotFoundException("user not found in updateUser");
    }

    // check if email is changed
    if (userUpdateDTO.getEmail() != null && !userUpdateDTO.getEmail().equals(savedUser.get().getEmail())) {

      // check if new email is taken
      Optional<User> optionalUser = userRepository.getUserByEmail(userUpdateDTO.getEmail());
      if (optionalUser.isPresent()) {
        throw new AlreadyExistException("user with that email already exists");
      }
    }

    userMapper.updateUserFromUserUpdateDTO(userUpdateDTO, savedUser.get());
    return userMapper.userToUserResponseDTO(userRepository.save(savedUser.get()));
  }

  public void deactivateUser(Long id) throws NotFoundException {
    Optional<User> optUser = userRepository.findById(id);
    if (!optUser.isPresent()) {
      throw new NotFoundException("user not found in deactivateUser");
    }
    User user = optUser.get();
    user.setActive(false);
    userRepository.save(user);
  }

  public void activateUser(Long id) throws NotFoundException {
    Optional<User> optUser = userRepository.findById(id);
    if (!optUser.isPresent()) {
      throw new NotFoundException("user not found in activateUser");
    }
    User user = optUser.get();
    user.setActive(true);
    userRepository.save(user);
  }
}
