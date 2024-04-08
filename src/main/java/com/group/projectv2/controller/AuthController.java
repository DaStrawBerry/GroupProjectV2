package com.group.projectv2.controller;

import com.group.projectv2.dto.SignUpDTO;
import com.group.projectv2.dto.UserDTO;
import com.group.projectv2.service.implement.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class AuthController {
    @Autowired
    UserServiceImp userService;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        return userService.userLogin(userDTO);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO signUpDTO){
        return userService.createUser(signUpDTO);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody UserDTO userDTO){
        return userService.adminLogin(userDTO);
    }
}
