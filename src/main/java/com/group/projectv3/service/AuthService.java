package com.group.projectv3.service;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.SignUpDTO;

public interface AuthService {
    public ReqRes signUp(SignUpDTO signUpDTO);
    public ReqRes signIn(ReqRes signInRequest);
    public ReqRes adminSignIn(ReqRes signInRequest);
    public ReqRes refreshToken(ReqRes refreshTokenRequest);
}
