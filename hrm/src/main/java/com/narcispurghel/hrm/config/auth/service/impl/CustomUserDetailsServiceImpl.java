package com.narcispurghel.hrm.config.auth.service.impl;

import com.narcispurghel.hrm.config.auth.UserDetailsImpl;
import com.narcispurghel.hrm.config.auth.service.CustomUserDetailsService;
import com.narcispurghel.hrm.db.dao.UserDao;
import com.narcispurghel.hrm.domain.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private final UserDao userDao;

    public CustomUserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userDao.findByUsername(username);

        if (userDto == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        } else {
            return new UserDetailsImpl(
                    userDto.getUsername(),
                    userDto.getPassword(),
                    "USER"
            );
        }
    }
}
