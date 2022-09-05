package io.github.khanhdpdx01.veserver.controller;

import io.github.khanhdpdx01.veserver.dto.user.CreateUserDTO;
import io.github.khanhdpdx01.veserver.dto.user.UserPrinciple;
import io.github.khanhdpdx01.veserver.security.JwtRequest;
import io.github.khanhdpdx01.veserver.security.JwtResponse;
import io.github.khanhdpdx01.veserver.security.JwtTokenUtil;
import io.github.khanhdpdx01.veserver.service.UserService;
import io.github.khanhdpdx01.veserver.util.CookieFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static io.github.khanhdpdx01.veserver.constant.AppConstant.ACCESS_TOKEN_COOKIE;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest,
                                   HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
        System.out.println(userDetails.getAuthorities());
        final String accessToken = jwtTokenUtil.generateToken(userDetails);

        Cookie accessTokenCookie = CookieFactory.create(ACCESS_TOKEN_COOKIE, accessToken);

        response.addCookie(accessTokenCookie);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setRole(userDetails.getRole());
        jwtResponse.setAccessToken(accessToken);

        return ResponseEntity.status(200).body(jwtResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody CreateUserDTO createUserDTO,
                                      HttpServletRequest request) {
        io.github.khanhdpdx01.veserver.entity.User registered = userService.createUser(createUserDTO);

        return ResponseEntity.status(200).body(registered);
    }
}
