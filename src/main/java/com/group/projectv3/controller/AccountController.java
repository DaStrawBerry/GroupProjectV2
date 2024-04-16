package com.group.projectv3.controller;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.SignUpDTO;
import com.group.projectv3.dto.UserDTO;
import com.group.projectv3.service.implement.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AccountController {

    @Autowired
    private UserServiceImp userService;

    @GetMapping("/accounts")
    public ResponseEntity<ReqRes> allAccount(){
        return ResponseEntity.ok(userService.allUser());
    }
    @PostMapping("/accounts/search")
    public ResponseEntity<ReqRes> searchAccount(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.searchUser(userDTO));
    }
    @PostMapping("/accounts/create")
    public ResponseEntity<ReqRes> createAccount(@RequestBody SignUpDTO signUpDTO){
        return ResponseEntity.ok(userService.addUser(signUpDTO));
    }
    @PutMapping("/accounts/edit")
    public ResponseEntity<ReqRes> editAccount(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.editUser(userDTO));
    }
    @DeleteMapping("/accounts/delete")
    public ResponseEntity<ReqRes> deleteAccount(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.deleteUser(userDTO));
    }
}
