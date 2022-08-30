package io.github.khanhdpdx01.veserver.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors
public class UpdateUser {
    private int userId;
    private boolean isActive;
}
