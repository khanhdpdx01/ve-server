package io.github.khanhdpdx01.veserver.dto.diploma;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class VDiploma implements Serializable {
    private String serialNumber;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String placeOfBirth;
    private String grade;
    private String level;
    private String rank;
    private String modeOfStudy;
    private String speciality;
    private String graduation;
    private String dateOfGraduation;
    private String refNumber;
    private String status;
    private String gpa;
    private String totalCredits;
    private String trainingLanguage;
    private String time;
    private String dateOfEnrollment;
    private String major;
    private String userId;
    private String session;
}
