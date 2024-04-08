package com.group.projectv2.controller;

import com.group.projectv2.entity.User;
import com.group.projectv2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService service;

    @GetMapping("/accounts")
    public ResponseEntity<?> allAccount(){
        return service.retrieveAllUser();
    }

    @PostMapping("/accounts/search")
    public ResponseEntity<?> findAccount(@RequestParam(required = false, defaultValue = "") String v){
        User user = new User();
        user.setFullname(v);
        user.setCode(v);
        return service.retrieveUserByCodeOrName(user);
    }

    @PutMapping("/accounts/update")
    public ResponseEntity<?> updateAccount(@RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("/accounts/delete")
    public ResponseEntity<?> deleteAccount(@RequestBody User user){
        return service.updateUser(user);
    }

}
