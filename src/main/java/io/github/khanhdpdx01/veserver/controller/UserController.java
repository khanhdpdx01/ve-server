package io.github.khanhdpdx01.veserver.controller;

import io.github.khanhdpdx01.veserver.dto.pagination.PaginationParams;
import io.github.khanhdpdx01.veserver.dto.pagination.PaginationResponse;
import io.github.khanhdpdx01.veserver.dto.user.CreateUserDTO;
import io.github.khanhdpdx01.veserver.dto.user.UpdateUser;
import io.github.khanhdpdx01.veserver.dto.user.UserDTO;
import io.github.khanhdpdx01.veserver.service.UserService;
import io.github.khanhdpdx01.veserver.util.PaginationAndSortUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/vecert/admin")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    public ResponseEntity<?> getUser(@Valid PaginationParams params) {
        Page<UserDTO> usersPage = userService.getAllUsers(params.getPage(), params.getSize(), params.getSort(), params.getKeyword());
        PaginationResponse<UserDTO> response = PaginationAndSortUtil.map(usersPage);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUser updateUser) {
        UserDTO userDTO = userService.updateUser(updateUser);
        return ResponseEntity.status(200).body(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO,
                                        HttpServletRequest request) {
        io.github.khanhdpdx01.veserver.entity.User registered = userService.createUser(createUserDTO);

        return ResponseEntity.status(200).body(registered);
    }
}
