package io.github.khanhdpdx01.veserver.dto.diploma;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class LookUpDiplomaDTO {
    private String lastName;
    private String firstName;
    private String dateOfBirth;
    private String serialNumber;
}
