package io.github.khanhdpdx01.veserver.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "v_user")
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String fullName;
    private String username;
    private String password;
    private String phoneNumber;
    private String role;
    private boolean isActive;
}
