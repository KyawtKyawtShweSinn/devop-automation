package com.lattmat.devop.mapper;

import com.lattmat.devop.dto.UserDto;
import com.lattmat.devop.entity.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto convertUserDto(Users user) {
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> convertUserDtoList(List<Users> user) {
        return user.stream()
        .map(this::convertUserDto).toList();
    }

    public Users convertUser(UserDto userDto) {
        return modelMapper.map(userDto, Users.class);
    }
}
