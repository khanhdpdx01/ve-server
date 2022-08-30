package io.github.khanhdpdx01.veserver.service;

import io.github.khanhdpdx01.veserver.dto.user.CreateUserDTO;
import io.github.khanhdpdx01.veserver.dto.user.UpdateUser;
import io.github.khanhdpdx01.veserver.dto.user.UserDTO;
import io.github.khanhdpdx01.veserver.entity.User;
import io.github.khanhdpdx01.veserver.repository.UserRepository;
import io.github.khanhdpdx01.veserver.util.PaginationAndSortUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Page<UserDTO> getAllUsers(int page, int size, String[] sort, String keyword) {
        Pageable pageable = PaginationAndSortUtil.create(page, size, sort);

        Page<User> pageRoom;

        if (keyword == null || keyword.isBlank()) {
            pageRoom = userRepository.findAll(pageable);
        } else {
            pageRoom = userRepository.search(keyword, pageable);
        }

        List<UserDTO> userDTOs = mapList(pageRoom.getContent());

        return new PageImpl<>(userDTOs, pageable, pageRoom.getTotalElements());
    }

    public UserDTO map(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId())
                .setUsername(user.getUsername())
                .setFullName(user.getFullName())
                .setPhoneNumber(user.getPhoneNumber())
                .setActive(user.isActive())
                .setRole(user.getRole());

        return userDTO;
    }


    public List<UserDTO> mapList(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        users.forEach(user -> userDTOs.add(map(user)));
        return userDTOs;
    }

    public UserDTO updateUser(UpdateUser updateUser) {
        User user = userRepository.findById(updateUser.getUserId())
                .orElseThrow(() -> new RuntimeException("User is not found"));

        user.setActive(updateUser.isActive());
        User updatedUser = userRepository.save(user);
        return map(updatedUser);
    }
}
