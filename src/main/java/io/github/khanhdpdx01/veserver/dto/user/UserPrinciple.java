package io.github.khanhdpdx01.veserver.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class UserPrinciple extends User {
    private int userId;
    private String role;
    private boolean active;

    public UserPrinciple(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
