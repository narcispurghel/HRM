package com.narcispurghel.hrm.domain.controller;

import com.narcispurghel.hrm.domain.dto.LoginDto;
import com.narcispurghel.hrm.domain.dto.RegisterDto;
import com.narcispurghel.hrm.domain.dto.UserDto;
import com.narcispurghel.hrm.domain.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "")
public class AuthController {
    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/api/v1/auth/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(registerDto));
    }

    @PostMapping(path = "/api/v1/auth/login")
    public ResponseEntity<UserDto> login(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @RequestBody LoginDto loginDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                userService.authenticate(request, response, loginDto));
    }

    @GetMapping(path = "/api/v1/authenticated")
    public ResponseEntity<String> authenticatedPath() {
        return ResponseEntity.ok("Authenticated");
    }

}
