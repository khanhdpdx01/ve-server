package io.github.khanhdpdx01.veserver.controller;

import io.github.khanhdpdx01.veserver.dto.diploma.AddDiplomaForm;
import io.github.khanhdpdx01.veserver.dto.diploma.DiplomaDTO;
import io.github.khanhdpdx01.veserver.dto.diploma.LookUpDiplomaDTO;
import io.github.khanhdpdx01.veserver.dto.diploma.VDiploma;
import io.github.khanhdpdx01.veserver.dto.training_program.ChoiceTrainningProgramFeild;
import io.github.khanhdpdx01.veserver.entity.*;
import io.github.khanhdpdx01.veserver.repository.MajorRepository;
import io.github.khanhdpdx01.veserver.repository.SpecialityRepository;
import io.github.khanhdpdx01.veserver.service.DiplomaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/diplomas")
public class DiplomaController {
    private final MajorRepository majorRepository;
    private final SpecialityRepository specialityRepository;
    private final DiplomaService diplomaService;

    public DiplomaController(MajorRepository majorRepository, SpecialityRepository specialityRepository, DiplomaService diplomaService) {
        this.majorRepository = majorRepository;
        this.specialityRepository = specialityRepository;
        this.diplomaService = diplomaService;
    }

    @GetMapping("/get-choice-training-program-feild")
    public ChoiceTrainningProgramFeild getChoiceTrainingProgramFeild() {
        ChoiceTrainningProgramFeild feild = new ChoiceTrainningProgramFeild();
        feild.setRanks(Ranking.getAllRanks());
        feild.setLevels(Level.getAllLevels());
        feild.setModeOfStudies(ModeOfStudy.getAllModeOfStudies());
        feild.setLanguages(TrainingLanguage.getAllLanguages());
        feild.setStatus(Status.getStatus());
        feild.setMajors(majorRepository.findAll());
        feild.setSpecialities(specialityRepository.findAll());
        return feild;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createDiploma(@RequestPart("diploma") AddDiplomaForm addDiplomaForm,
                                           @RequestPart("files") List<MultipartFile> files) {
        String res = diplomaService.createDiploma(addDiplomaForm);
        return ResponseEntity.status(200).body(res);
    }

//    @GetMapping
//    public ResponseEntity<?> getAllDiplomas(@RequestParam(required = false) String keyword,
//                                            @RequestParam(required = false) int levelId,
//                                            @RequestParam(required = false) int rankingId,
//                                            @RequestParam(required = false) int modeOfStudyId,
//                                            @RequestParam(required = false) int majorId) {
//        List<DiplomaDTO> diplomas = diplomaService.getAllDiplomas(keyword, levelId, rankingId, modeOfStudyId, majorId);
//        return ResponseEntity.status(200).body(diplomas);
//    }

    @GetMapping("/{serial-number}")
    public ResponseEntity<?> getDiploma(@PathVariable("serial-number") String serialNumber) {
        VDiploma diploma = diplomaService.getDiploma(serialNumber);
        return ResponseEntity.status(200).body(diploma);
    }

    @GetMapping
    public ResponseEntity<?> getAllDiplomaFromNetwork() throws Exception {
        List<DiplomaDTO> diplomas = diplomaService.getAllDiplomasFromNetwork();
        return ResponseEntity.status(200).body(diplomas);
    }

    @GetMapping("/search")
    public ResponseEntity<?> lookUp(LookUpDiplomaDTO params) {
        List<DiplomaDTO> diplomas = diplomaService.lookUpDiploma(params);
        System.out.println(params.getSerialNumber());
        return ResponseEntity.status(200).body(diplomas);
    }

}
