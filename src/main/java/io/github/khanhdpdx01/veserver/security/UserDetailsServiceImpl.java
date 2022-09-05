package io.github.khanhdpdx01.veserver.security;

import io.github.khanhdpdx01.veserver.dto.user.UserPrinciple;
import io.github.khanhdpdx01.veserver.entity.User;
import io.github.khanhdpdx01.veserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User is not found"));

        List<GrantedAuthority> grandList = new ArrayList<>();

        grandList.add(new SimpleGrantedAuthority(user.getRole()));

        UserPrinciple userPrinciple = new UserPrinciple(user.getUsername(),
                user.getPassword(), grandList);
        userPrinciple.setUserId(user.getUserId());
        userPrinciple.setRole(user.getRole());

        return userPrinciple;
    }
}
