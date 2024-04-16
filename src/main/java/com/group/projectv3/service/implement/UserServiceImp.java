package com.group.projectv3.service.implement;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.SignUpDTO;
import com.group.projectv3.dto.UserDTO;
import com.group.projectv3.map.UserDTOMap;
import com.group.projectv3.model.User;
import com.group.projectv3.repository.UserRepository;
import com.group.projectv3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDTOMap userDTOMap;
    @Autowired
    private AuthServiceImp authService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ReqRes allUser() {
        ReqRes resp = new ReqRes();
        try{
            List<User> userList = userRepository.findAll();
//            List<User> userList = new ArrayList<>();
            resp.setStatusCode(200);
            resp.setData(userList);
            resp.setMessage("Found " + userList.size() + " accounts");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes searchUser(UserDTO userDTO) {
        ReqRes resp = new ReqRes();
        try{
            List<User> userList = userList = userRepository.findAll();
            if(userDTO.getName() != null && !userDTO.getName().trim().isEmpty()){
                userList = userRepository.findAllByNameLike(userDTO.getName());
            }else if(userDTO.getCode() != null && !userDTO.getCode().trim().isEmpty()){
                userList = userRepository.findAllByCodeLike(userDTO.getCode());
            }

            resp.setStatusCode(200);
            resp.setData(userList);
            resp.setMessage("Found " + userList.size() + " accounts");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes addUser(SignUpDTO signUpDTO) {
        return authService.signUp(signUpDTO);
    }

    @Override
    public ReqRes editUser(UserDTO userDTO) {
        ReqRes resp = new ReqRes();
        try{
            User user = userDTOMap.UserDTOToUser(userDTO);
            User preu = userRepository.findByUsername(user.getUsername()).orElseThrow();
            user.setId(preu.getId());
            user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
            resp.setStatusCode(200);
            resp.setUser(userRepository.save(user));
            resp.setMessage("Updated");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public ReqRes deleteUser(UserDTO userDTO) {
        ReqRes resp = new ReqRes();
        try{
            User user =  userRepository.findByUsername(userDTO.getUsername()).orElseThrow();
            resp.setStatusCode(200);
            userRepository.delete(user);
            resp.setMessage("Deleted");
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }
}
