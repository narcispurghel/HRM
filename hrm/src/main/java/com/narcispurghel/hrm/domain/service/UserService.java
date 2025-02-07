package com.narcispurghel.hrm.domain.service;

import com.narcispurghel.hrm.domain.dto.RegisterDto;
import com.narcispurghel.hrm.domain.dto.UserDto;

public interface UserService {
    UserDto register(RegisterDto registerDto);
}
