package io.github.khanhdpdx01.veserver.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public static void main(String[] args) {
        String name = "VBCC_Phu_luc_Nguyen_Van_A.pdf";
        if(name.toLowerCase().indexOf("vbcc_phu_luc") != -1) {
            System.out.println("True");
        }
    }

}
