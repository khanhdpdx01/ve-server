package io.github.khanhdpdx01.veserver.dto.training_program;

import io.github.khanhdpdx01.veserver.entity.Major;
import io.github.khanhdpdx01.veserver.entity.Speciality;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ChoiceTrainningProgramFeild {
    private Map<Integer, String> ranks;
    private Map<Integer, String> levels;
    private Map<Integer, String> modeOfStudies;
    private Map<Integer, String> languages;
    private Map<Integer, String> status;
    private List<Major> majors;
    private List<Speciality> specialities;
}
