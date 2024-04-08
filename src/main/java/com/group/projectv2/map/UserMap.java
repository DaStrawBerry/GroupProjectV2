package com.group.projectv2.map;

import com.group.projectv2.dto.SignUpDTO;
import com.group.projectv2.dto.UserDTO;
import com.group.projectv2.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserMap {
    public User dtoToEntity(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }
    public User dtoToEntity(SignUpDTO signUpDTO){
        User user = new User();
        user.setUsername(signUpDTO.getUsername());
        user.setCode(signUpDTO.getCode());
        user.setPassword(signUpDTO.getPassword());
        user.setEmail(signUpDTO.getEmail());
        user.setFullname(signUpDTO.getFullname());
//        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        user.setDate_of_birth(signUpDTO.getDate_of_birth());
        user.setIs_admin(false);
        return user;
    }
    public List<User> dtoToEntity(List<UserDTO> userDTO){
        return userDTO.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }

    public UserDTO entityToDto(User user){
        return new UserDTO(user.getUsername(), user.getPassword());
    }
    public List<UserDTO> entityToDto(List<User> user){
        return user.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }
}
