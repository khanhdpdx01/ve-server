package io.github.khanhdpdx01.veserver.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class UserDTO {
    private int userId;
    private String fullName;
    private String username;
    private String phoneNumber;
    private String role;
    private boolean isActive;
}
