package io.github.khanhdpdx01.veserver.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table
public class Diploma {
    @Id
    private String serialNumber;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String placeOfBirth;
    private String grade;
    private String level;
    @Column(name = "ranking")
    private String rank;
    private String modeOfStudy;
    private String speciality;
    private String graduation;
    private String dateOfGraduation;
    private String refNumber;
    private int status;
    private String gpa;
    private String totalCredits;
    private String trainingLanguage;
    private String time;
    private String dateOfEnrollment;
    private String major;
    private String userId;
    private String session;
    private String diplomaLink;
    private String appendixLink;
}
