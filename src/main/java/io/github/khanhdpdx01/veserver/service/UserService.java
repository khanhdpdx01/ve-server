package io.github.khanhdpdx01.veserver.service;

import io.github.khanhdpdx01.veserver.dto.user.CreateUserDTO;
import io.github.khanhdpdx01.veserver.entity.User;
import io.github.khanhdpdx01.veserver.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static io.github.khanhdpdx01.veserver.constant.AppConstant.ADMIN;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserDTO createUserDTO) {
        User user = new User();
        user.setUsername(createUserDTO.getUsername())
                .setPassword(passwordEncoder.encode(createUserDTO.getPassword()))
                .setRole(ADMIN)
                .setFullName(createUserDTO.getFullName())
                .setPhoneNumber(createUserDTO.getPhoneNumber())
                .setActive(true);

        User newUser = userRepository.save(user);
        return newUser;
    }
}
