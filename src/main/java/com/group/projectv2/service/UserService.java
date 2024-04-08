package com.group.projectv2.service;

import com.group.projectv2.dto.SignUpDTO;
import com.group.projectv2.dto.UserDTO;
import com.group.projectv2.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> userLogin(UserDTO userDTO);

    //    public ResponseEntity<?> userLogin(UserDTO userDTO);
    public ResponseEntity<?> adminLogin(UserDTO userDTO);
    public ResponseEntity<?> createUser(SignUpDTO signUpDTO);
    public ResponseEntity<?> retrieveAllUser();
    public ResponseEntity<?> retrieveUserById(User user);
    public ResponseEntity<?> retrieveUserByCodeOrName(User user);
    public ResponseEntity<?> updateUser(User user);
    public ResponseEntity<?> deleteUser(User user);
}
