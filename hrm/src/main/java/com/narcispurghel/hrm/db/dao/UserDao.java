package com.narcispurghel.hrm.db.dao;

import com.narcispurghel.hrm.config.auth.PasswordEncoderImpl;
import com.narcispurghel.hrm.db.entity.User;
import com.narcispurghel.hrm.db.repository.UserRepository;
import com.narcispurghel.hrm.domain.dto.UserDto;
import com.narcispurghel.hrm.domain.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Transactional
public class UserDao {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoderImpl passwordEncoder;

    public UserDao(UserRepository userRepository,
                   UserMapper userMapper,
                   PasswordEncoderImpl passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto save(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User entity = userRepository.save(new User(
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getName(),
                userDto.getAge()
        ));

        return userMapper.toDto(entity);
    }

    public UserDto findByUsername(String username) {
        Optional<User> entity = userRepository.findByUsername(username);

        if (entity.isPresent()) {
            return userMapper.toDto(entity.get());
        }

        return null;
    }
}
