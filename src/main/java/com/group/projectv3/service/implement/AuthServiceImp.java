package com.group.projectv3.service.implement;

import com.group.projectv3.dto.ReqRes;
import com.group.projectv3.dto.SignUpDTO;
import com.group.projectv3.model.User;
import com.group.projectv3.repository.UserRepository;
import com.group.projectv3.service.AuthService;
import com.group.projectv3.service.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ReqRes signUp(SignUpDTO signUpDTO){
        ReqRes resp = new ReqRes();

        //Check username if contains only alphanumeric characters and is at least 8 characters long
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{8,}$");
        Matcher mat = pattern.matcher(signUpDTO.getUsername());
        if (!mat.matches()) {
            resp.setStatusCode(406);
            resp.setMessage("Username must contains only alphanumeric characters and is at least 8 characters long");
            return resp;
        }

        //Check if username has already exist
        Optional<User> existing = userRepository.findByUsername(resp.getUsername());
        if(existing.isPresent()){
            resp.setStatusCode(226);
            resp.setMessage("Username existed");
            return resp;
        }

        //Check password if  at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one of the special characters "@$!%*?&#"
        pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$");
        mat = pattern.matcher(signUpDTO.getPassword());
        if (!mat.matches()) {
            resp.setStatusCode(406);
            resp.setMessage("Password must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit and one special characters");
            return resp;
        }
        //Check if the password is match with repasswd
        if(signUpDTO.getRepasswd() == null){
            resp.setStatusCode(406);
            resp.setMessage("Confirm password is missing");
            return resp;
        }
        String password = signUpDTO.getPassword().trim();
        String repasswd = signUpDTO.getRepasswd().trim();
        if ( !password.equals(repasswd) ) {
            resp.setStatusCode(406);
            resp.setMessage("Confirm password does not match");
            return resp;
        }

        if(signUpDTO.getCode() == null){
            resp.setStatusCode(406);
            resp.setMessage("Code is missing");
            return resp;
        }
        if(signUpDTO.getName() == null){
            resp.setStatusCode(406);
            resp.setMessage("Name is missing");
            return resp;
        }

        //Check if the email is in valid form
        if(signUpDTO.getEmail() == null){
            resp.setStatusCode(406);
            resp.setMessage("Email is missing");
            return resp;
        }
        pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        mat = pattern.matcher(signUpDTO.getEmail().trim());
        if (!mat.matches()) {
            resp.setStatusCode(406);
            resp.setMessage("Invalid email address");
            return resp;
        }
        //Check if email has already exist
        existing = userRepository.findByEmail(signUpDTO.getEmail().trim());
        if(existing.isPresent()){
            resp.setStatusCode(226);
            resp.setMessage("Email already in used");
            return resp;
        }

        try {
            User user = new User();
            user.setUsername(signUpDTO.getUsername());
            user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
            user.setCode(signUpDTO.getCode());
            user.setName(signUpDTO.getName());
            user.setEmail(signUpDTO.getEmail());
            user.setRole(signUpDTO.getRole());
            User userResult = userRepository.save(user);
            if(userResult != null && !userResult.getId().isBlank()){
                resp.setUser(userResult);
                resp.setMessage("Sign Up Successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public ReqRes signIn(ReqRes signInRequest){
        ReqRes response = new ReqRes();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
            var user = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow();
            System.out.println("USER IS: " + user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("6Hr");
            response.setMessage("Sign In Successfully");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Wrong user name or password");
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes adminSignIn(ReqRes signInRequest){
        ReqRes response = new ReqRes();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
            var user = userRepository.findByUsername(signInRequest.getUsername()).orElseThrow();
            if(user.getRole().compareTo("ADMIN")!=0){
                throw new Exception("This is an USER account");
            }
            System.out.println("USER IS: " + user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("6Hr");
            response.setMessage("Sign In Successfully");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest){
        ReqRes response = new ReqRes();
        String ourUsername = jwtUtils.extractUsername(refreshTokenRequest.getUsername());
        User user = userRepository.findByUsername(ourUsername).orElseThrow();
        if(jwtUtils.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtUtils.generateToken(user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("6Hr");
            response.setMessage("Refresh Token Successfully");
        }
        response.setStatusCode(500);
        return response;
    }
}
