package com.group.projectv3.service;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.SignUpDTO;
import com.group.projectv3.dto.UserDTO;

public interface UserService {
    public ReqRes allUser();
    public ReqRes searchUser(UserDTO userDTO);
    public ReqRes addUser(SignUpDTO signUpDTO);
    public ReqRes editUser(UserDTO userDTO);
    public ReqRes deleteUser(UserDTO userDTO);
}
