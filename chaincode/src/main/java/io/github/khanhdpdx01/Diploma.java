package io.github.khanhdpdx01;

import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.Objects;

@DataType
public class Diploma {
    @Property
    private final String firstName;
    @Property
    private final String lastName;
    @Property
    private final String dateOfBirth;
    @Property
    private final String gender;
    @Property
    private final String placeOfBirth;
    @Property
    private final String grade;
    @Property
    private final String level;
    @Property
    private final String rank;
    @Property
    private final String modeOfStudy;
    @Property
    private final String speciality;
    @Property
    private final String graduation;
    @Property
    private final String dateOfGraduation;
    @Property
    private final String refNumber;
    @Property
    private final String status;
    @Property
    private final String gpa;
    @Property
    private final String totalCredits;
    @Property
    private final String trainingLanguage;
    @Property
    private final String time;
    @Property
    private final String dateOfEnrollment;
    @Property
    private final String major;
    @Property
    private final String userId;
    @Property
    private final String session;
    @Property
    private final String diplomaLink;
    @Property
    private final String appendixLink;

    public Diploma(@JsonProperty("userId") String userId,
                   @JsonProperty("firstName") String firstName,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("dateOfBirth") String dateOfBirth,
                   @JsonProperty("gender") String gender,
                   @JsonProperty("placeOfBirth") String placeOfBirth,
                   @JsonProperty("grade") String grade,
                   @JsonProperty("level") String level,
                   @JsonProperty("rank") String rank,
                   @JsonProperty("modeOfStudy") String modeOfStudy,
                   @JsonProperty("speciality") String speciality,
                   @JsonProperty("graduation") String graduation,
                   @JsonProperty("dateOfGraduation") String dateOfGraduation,
                   @JsonProperty("refNumber") String refNumber,
                   @JsonProperty("status") String status,
                   @JsonProperty("gpa") String gpa,
                   @JsonProperty("totalCredits") String totalCredits,
                   @JsonProperty("trainingLanguage") String trainingLanguage,
                   @JsonProperty("time") String time,
                   @JsonProperty("dateOfEnrollment") String dateOfEnrollment,
                   @JsonProperty("major") String major,
                   @JsonProperty("session") String session,
                   @JsonProperty("diplomaLink") String diplomaLink,
                   @JsonProperty("appendixLink") String appendixLink) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.placeOfBirth = placeOfBirth;
        this.grade = grade;
        this.level = level;
        this.rank = rank;
        this.modeOfStudy = modeOfStudy;
        this.speciality = speciality;
        this.graduation = graduation;
        this.dateOfGraduation = dateOfGraduation;
        this.refNumber = refNumber;
        this.status = status;
        this.gpa = gpa;
        this.totalCredits = totalCredits;
        this.trainingLanguage = trainingLanguage;
        this.time = time;
        this.dateOfEnrollment = dateOfEnrollment;
        this.major = major;
        this.session = session;
        this.diplomaLink = diplomaLink;
        this.appendixLink = appendixLink;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getGrade() {
        return grade;
    }

    public String getLevel() {
        return level;
    }

    public String getRank() {
        return rank;
    }

    public String getModeOfStudy() {
        return modeOfStudy;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getGraduation() {
        return graduation;
    }

    public String getDateOfGraduation() {
        return dateOfGraduation;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getGpa() {
        return gpa;
    }

    public String getTotalCredits() {
        return totalCredits;
    }

    public String getTrainingLanguage() {
        return trainingLanguage;
    }

    public String getTime() {
        return time;
    }

    public String getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public String getMajor() {
        return major;
    }

    public String getUserId() {
        return userId;
    }

    public String getSession() {
        return session;
    }

    public String getDiplomaLink() {
        return diplomaLink;
    }

    public String getAppendixLink() {
        return appendixLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diploma diploma = (Diploma) o;
        return Objects.equals(firstName, diploma.firstName) && Objects.equals(lastName, diploma.lastName) && Objects.equals(dateOfBirth, diploma.dateOfBirth) && Objects.equals(gender, diploma.gender) && Objects.equals(placeOfBirth, diploma.placeOfBirth) && Objects.equals(grade, diploma.grade) && Objects.equals(level, diploma.level) && Objects.equals(rank, diploma.rank) && Objects.equals(modeOfStudy, diploma.modeOfStudy) && Objects.equals(speciality, diploma.speciality) && Objects.equals(graduation, diploma.graduation) && Objects.equals(dateOfGraduation, diploma.dateOfGraduation) && Objects.equals(refNumber, diploma.refNumber) && Objects.equals(status, diploma.status) && Objects.equals(gpa, diploma.gpa) && Objects.equals(totalCredits, diploma.totalCredits) && Objects.equals(trainingLanguage, diploma.trainingLanguage) && Objects.equals(time, diploma.time) && Objects.equals(dateOfEnrollment, diploma.dateOfEnrollment) && Objects.equals(major, diploma.major) && Objects.equals(userId, diploma.userId) && Objects.equals(session, diploma.session) && Objects.equals(diplomaLink, diploma.diplomaLink) && Objects.equals(appendixLink, diploma.appendixLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, gender, placeOfBirth, grade, level, rank, modeOfStudy, speciality, graduation, dateOfGraduation, refNumber, status, gpa, totalCredits, trainingLanguage, time, dateOfEnrollment, major, userId, session, diplomaLink, appendixLink);
    }

    @Override
    public String toString() {
        return "Diploma{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", grade='" + grade + '\'' +
                ", level='" + level + '\'' +
                ", rank='" + rank + '\'' +
                ", modeOfStudy='" + modeOfStudy + '\'' +
                ", speciality='" + speciality + '\'' +
                ", graduation='" + graduation + '\'' +
                ", dateOfGraduation='" + dateOfGraduation + '\'' +
                ", refNumber='" + refNumber + '\'' +
                ", status='" + status + '\'' +
                ", gpa='" + gpa + '\'' +
                ", totalCredits='" + totalCredits + '\'' +
                ", trainingLanguage='" + trainingLanguage + '\'' +
                ", time='" + time + '\'' +
                ", dateOfEnrollment='" + dateOfEnrollment + '\'' +
                ", major='" + major + '\'' +
                ", userId='" + userId + '\'' +
                ", session='" + session + '\'' +
                ", diplomaLink='" + diplomaLink + '\'' +
                ", appendixLink='" + appendixLink + '\'' +
                '}';
    }
}

