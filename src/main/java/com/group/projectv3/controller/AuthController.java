package com.group.projectv3.controller;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.SignUpDTO;
import com.group.projectv3.service.implement.AuthServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImp authServiceImp;

    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signUp(@RequestBody SignUpDTO signUpRequest){
        return ResponseEntity.ok(authServiceImp.signUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInRequest){
        return ResponseEntity.ok(authServiceImp.signIn(signInRequest));
    }

    @PostMapping("/adminsignin")
    public ResponseEntity<ReqRes> adminSignIn(@RequestBody ReqRes signInRequest){
        return ResponseEntity.ok(authServiceImp.adminSignIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest){
        return ResponseEntity.ok(authServiceImp.refreshToken(refreshTokenRequest));
    }
}
