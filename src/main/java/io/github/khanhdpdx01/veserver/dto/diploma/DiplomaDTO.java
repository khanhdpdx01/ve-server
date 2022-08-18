package io.github.khanhdpdx01.veserver.dto.diploma;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class DiplomaDTO {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String placeOfBirth;
    private String level;
    private String rank;
    private String modeOfStudy;
    private String speciality;
    private String graduation;
    private String yearOfGraduation;
    private String serialNumber;
    private String refNumber;
    private String status;
}
