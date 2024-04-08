package com.group.projectv2.service.implement;

import com.group.projectv2.dto.SignUpDTO;
import com.group.projectv2.dto.UserDTO;
import com.group.projectv2.entity.ResponseObject;
import com.group.projectv2.entity.User;
import com.group.projectv2.map.UserMap;
import com.group.projectv2.repository.UserRepository;
import com.group.projectv2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    UserMap userMap;
    @Override
    public ResponseEntity<?> userLogin(UserDTO userDTO){
        List<User> userList = repository.findByUsername(userDTO.getUsername().trim());
        if(!userList.isEmpty() && userDTO.getPassword().compareTo(userList.get(0).getPassword())==0){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "SUCCESS",
                            "Login succes",
                            userList.get(0)
                    ));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject(
                        "FAILED",
                        "Wrong user name or password",
                        userDTO
                ));
    }
    @Override
    public ResponseEntity<?> adminLogin(UserDTO userDTO) {
        ResponseObject responseObject = (ResponseObject) userLogin(userDTO).getBody();
        if (responseObject.getStatus().compareTo("SUCCESS") == 0 && ((User) responseObject.getData()).getIs_admin()) {
            User admin = (User) responseObject.getData();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "SUCCESS",
                            "Login succes",
                            admin
                    ));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject(
                        "FAILED",
                        "Wrong user name or password",
                        userDTO
                ));
    }

    @Override
    public ResponseEntity<?> createUser(SignUpDTO signUpDTO) {
        //Check username if contains only alphanumeric characters and is at least 8 characters long
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{8,}$");
        Matcher mat = pattern.matcher(signUpDTO.getUsername());
        if (!mat.matches()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new ResponseObject(
                            "not_accept",
                            "Username must contains only alphanumeric characters and is at least 8 characters long",
                            signUpDTO)
                    );
        }

        //Check if username has already exist
        List<User> userList = repository.findByUsername(signUpDTO.getUsername());
        if (!userList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.IM_USED)
                    .body(new ResponseObject(
                            "USED",
                            "Username existed",
                            signUpDTO
                    ));
        }

        //Check password if  at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one of the special characters "@$!%*?&#"
        pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$");
        mat = pattern.matcher(signUpDTO.getPassword());
        if (!mat.matches()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new ResponseObject(
                            "not_accept",
                            "Password must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit and one special characters",
                            signUpDTO)
                    );
        }

        //Check if the password is match with repasswd
        if (signUpDTO.getPassword().trim().compareTo(signUpDTO.getRepasswd()) != 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new ResponseObject(
                            "not_accept",
                            "Confirm password does not match",
                            signUpDTO)
                    );
        }

        //Check if the email is in valid form
        pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        mat = pattern.matcher(signUpDTO.getEmail().trim());
        if (!mat.matches()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new ResponseObject(
                            "not_accept",
                            "invalid email address",
                            signUpDTO)
                    );
        }

        //Check if email has already exist
        userList = repository.findByEmail(signUpDTO.getEmail().trim());
        if (!userList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.IM_USED)
                    .body(new ResponseObject(
                            "Used",
                            "User email already in used",
                            signUpDTO)
                    );
        }

        //Check if the full name is in valid form
        pattern = Pattern.compile("^[a-zA-ZÀ-ÿ]+(?:[' -][a-zA-ZÀ-ÿ]+)*$");
        mat = pattern.matcher(signUpDTO.getFullname().trim());
        if (!mat.matches()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new ResponseObject(
                            "not_accept",
                            "Invalid name",
                            signUpDTO)
                    );
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(
                        "ok",
                        "Sign up successfully",
                        repository.save(userMap.dtoToEntity(signUpDTO))));
    }

    @Override
    public ResponseEntity<?> retrieveAllUser() {
        List<User> userList = repository.findAll();
        if (userList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            "EMPTY",
                            "No user found",
                            ""));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(
                        "OK",
                        "Found " + userList.size() + " user",
                        userList));
    }

    @Override
    public ResponseEntity<?> retrieveUserById(User user) {
        List<User> userList = repository.findAllById(user.getId());
        if (userList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            "EMPTY",
                            "No user found",
                            ""));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(
                        "OK",
                        "Found user",
                        userList.get(0)));
    }

    @Override
    public ResponseEntity<?> retrieveUserByCodeOrName(User user) {
        List<User> userList = repository.findAllByFullnameLike(user.getFullname());
        if (userList.isEmpty()) {
            userList = repository.findAllByCodeLike(user.getCode());
        }
        if (userList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new ResponseObject(
                            "EMPTY",
                            "No user found",
                            ""));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseObject(
                        "OK",
                        "Found " + userList.size() + " user",
                        userList));
    }

    @Override
    public ResponseEntity<?> updateUser(User user) {
        if (!repository.findAllById(user.getId()).isEmpty()) {
            try {
                repository.save(user);
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject(
                                "OK",
                                "User " + user.getId() + " updated",
                                user));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                        .body(new ResponseObject(
                                "FAILED",
                                "Unable to update user: " + user.getId(),
                                user));
            }
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new ResponseObject(
                        "EMPTY",
                        "No user found",
                        user));
    }

    @Override
    public ResponseEntity<?> deleteUser(User user) {
        try {
            repository.deleteById(user.getId());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject(
                            "OK",
                            "User " + user.getId() + " deleted",
                            ""));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(
                            "EMPTY",
                            "No user found",
                            user));
        }
    }
}
