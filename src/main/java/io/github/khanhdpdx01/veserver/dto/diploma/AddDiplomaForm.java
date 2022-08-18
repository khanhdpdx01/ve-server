package io.github.khanhdpdx01.veserver.dto.diploma;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AddDiplomaForm implements Serializable {
    @NotNull
    @NotEmpty(message = "Không được bỏ trống mã sinh viên")
    @Pattern(regexp = "^[\\d]{9}", message = "Mã sinh viên không hợp leek")
    private String userId;

    @NotNull
    @NotEmpty(message = "Không được bỏ trống họ đệm")
    private String lastName;

    @NotNull
    @NotEmpty(message = "Không được bỏ trống tên")
    private String firstName;

    @NotNull(message = "Không được bỏ trống ngày sinh")
    private Date dateOfBirth;

    private boolean gender;

    @NotNull
    @NotEmpty(message = "Không được bỏ trống nơi sinh")
    private String placeOfBirth;

    @NotNull
    @NotEmpty(message = "Không được bỏ trống lớp")
    private String grade;

    @NotNull
    @NotEmpty
    private String serialNumber;

    @NotNull
    @NotEmpty
    private String refNumber;

    @NotNull
    @NotEmpty
    private String graduation;

    @NotNull
    private Date dateOfGraduate;

    private int status;

    private int majorId;

    private int specialityId;

    @NotNull
    private double gpa;

    private int totalCredits;

    private int rankId;

    private int levelId;

    @NotNull
    private double trainingTime;

    @NotNull
    private Date dateOfEnrollment;

    @NotNull
    @NotEmpty
    private String session;

    private int modeOfStudy;

    private int trainingLanguage;
}
