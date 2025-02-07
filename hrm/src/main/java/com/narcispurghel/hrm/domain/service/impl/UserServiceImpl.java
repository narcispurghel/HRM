package com.narcispurghel.hrm.domain.service.impl;

import com.narcispurghel.hrm.db.dao.UserDao;
import com.narcispurghel.hrm.domain.dto.LoginDto;
import com.narcispurghel.hrm.domain.dto.RegisterDto;
import com.narcispurghel.hrm.domain.dto.UserDto;
import com.narcispurghel.hrm.domain.exception.DuplicateUsernameException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    private final UserDao userDao;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserDao userDao,
                           AuthenticationManager authenticationManager) {
        this.userDao = userDao;
        this.authenticationManager = authenticationManager;
    }

    public UserDto register(RegisterDto registerDto) {
        if (registerDto == null) {
            return null;
        }

        if (assertUniqueUsername(registerDto.username())) {
            return userDao.save(
                    new UserDto(
                            registerDto.username(),
                            registerDto.password(),
                            registerDto.name(),
                            registerDto.age()
                    ));
        } else {
            throw new DuplicateUsernameException("Username " + registerDto.username() + " is not available");
        }
    }

    private boolean assertUniqueUsername(String username) {
        return userDao.findByUsername(username) == null;
    }

    public UserDto authenticate(HttpServletRequest request,
                                HttpServletResponse response,
                                LoginDto loginDto) throws AuthenticationException {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(
                loginDto.username(),
                loginDto.password()
        );

        Authentication auth = authenticationManager.authenticate(authenticationRequest);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
        sessionCookie.setHttpOnly(true);
        sessionCookie.setPath("/");

        response.addCookie(sessionCookie);

        return userDao.findByUsername(loginDto.username());
    }
}
