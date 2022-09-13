package io.github.khanhdpdx01.veserver.dto.diploma;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.khanhdpdx01.veserver.entity.Diploma;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiplomaDetail {
    private Diploma diploma;
    private String tx;
}