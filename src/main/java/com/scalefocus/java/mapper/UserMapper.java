package com.scalefocus.java.mapper;


import com.scalefocus.java.domain.local.User;
import com.scalefocus.java.dto.UserDTO;
import com.scalefocus.java.dto.UserResponseDTO;
import com.scalefocus.java.dto.UserUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface UserMapper {
  User userDTOtoUser(UserDTO userDTO);

  void updateUserFromUserUpdateDTO(UserUpdateDTO userUpdateDTO, @MappingTarget User user);

  UserResponseDTO userDTOToUserResponseDTO(UserDTO userDTO);

  UserResponseDTO userToUserResponseDTO(User user);

  User userResponseDTOtoUser(UserResponseDTO userResponseDTO);
}
