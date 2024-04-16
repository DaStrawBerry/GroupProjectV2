package com.group.projectv3.map;

import com.group.projectv3.dto.UserDTO;
import com.group.projectv3.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMap {
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO UserToUserDTO(User user){
        return modelMapper.map(user, UserDTO.class);
    }

    public User UserDTOToUser(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
}
