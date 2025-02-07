package com.narcispurghel.hrm.domain.mapper;

import com.narcispurghel.hrm.db.entity.User;
import com.narcispurghel.hrm.domain.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDto userDto) {
        return new User(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getName(),
                userDto.getAge()
        );
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getAge()
        );
    }
}
