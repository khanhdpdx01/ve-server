package io.github.khanhdpdx01.veserver.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    private String fullName;
    private String phoneNumber;
    private String username;
    private String password;
}
