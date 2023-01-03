package io.github.khanhdpdx01.veserver.controller;

import io.github.khanhdpdx01.veserver.dto.diploma.*;
import io.github.khanhdpdx01.veserver.dto.pagination.PaginationParams;
import io.github.khanhdpdx01.veserver.dto.pagination.PaginationResponse;
import io.github.khanhdpdx01.veserver.dto.training_program.ChoiceTrainningProgramFeild;
import io.github.khanhdpdx01.veserver.entity.*;
import io.github.khanhdpdx01.veserver.repository.MajorRepository;
import io.github.khanhdpdx01.veserver.repository.SpecialityRepository;
import io.github.khanhdpdx01.veserver.service.DiplomaService;
import io.github.khanhdpdx01.veserver.util.FileUtil;
import io.github.khanhdpdx01.veserver.util.PaginationAndSortUtil;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/vecert/diplomas")
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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    public ResponseEntity<?> createDiploma(@RequestPart("diploma") AddDiplomaForm addDiplomaForm, @RequestPart("files") List<MultipartFile> files) {
        Diploma res = diplomaService.createDiploma(addDiplomaForm, files);
        return ResponseEntity.status(200).body(res);
    }

    @PostMapping("/sign")
    @PreAuthorize("hasAnyRole('PRINCIPAL')")
    public ResponseEntity<?> signDiploma(@RequestBody SignDiplomaDTO signDiplomaDTO) {
        Diploma res = diplomaService.storeDiplomaToBlockchain(signDiplomaDTO.getSerialNumber());
        return ResponseEntity.status(200).body(res);
    }

    @PostMapping(value = "/test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createDiploma(@RequestPart("file") MultipartFile file) {
        String res;
        try {
            InputStream inputStream = file.getInputStream();
            FileUtil.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(200).body(file.getOriginalFilename());
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    public ResponseEntity<?> getAllDiplomas(@Valid PaginationParams params, @RequestParam(required = false) Integer majorId, @RequestParam(required = false) Integer specialityId, @RequestParam(required = false) Integer levelId, @RequestParam(required = false) Integer rankId, @RequestParam(required = false) Integer modeOfStudyId, @RequestParam(required = false) Integer statusId) {
        Page<DiplomaDTO> pageDiplomaDTO = diplomaService.getAllDiplomas(params.getPage(), params.getSize(), params.getSort(), params.getKeyword(), majorId, specialityId, levelId, rankId, modeOfStudyId, statusId);

        PaginationResponse<DiplomaDTO> response = PaginationAndSortUtil.map(pageDiplomaDTO);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{serial-number}")
    public ResponseEntity<?> getDiploma(@PathVariable("serial-number") String serialNumber) {
        Diploma diploma = diplomaService.getDiploma(serialNumber);
        return ResponseEntity.status(200).body(diploma);
    }

    @GetMapping
    public ResponseEntity<?> getAllDiplomaFromNetwork() throws Exception {
        List<DiplomaDetail> diplomas = diplomaService.getAllDiplomasFromNetwork();
        return ResponseEntity.status(200).body(diplomas);
    }

    @GetMapping("/search")
    public ResponseEntity<?> lookUp(LookUpDiplomaDTO params) {
        List<DiplomaDTO> diplomas = diplomaService.lookUpDiploma(params);
        return ResponseEntity.status(200).body(diplomas);
    }

    @GetMapping(value = "/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = FileUtil.load(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"").contentType(MediaType.APPLICATION_PDF).body(file);
    }

    @GetMapping("/pending-status")
    @PreAuthorize("hasAnyRole('PRINCIPAL')")
    public ResponseEntity<?> getAllDiplomasHasPendingStatus(@Valid PaginationParams params) {
        Page<DiplomaDTO> pageDiplomaDTO = diplomaService.getAllDiplomasHasPendingStatus(params.getPage(), params.getSize(), params.getSort(), params.getKeyword());

        PaginationResponse<DiplomaDTO> response = PaginationAndSortUtil.map(pageDiplomaDTO);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/verify/{serial-number}")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    public ResponseEntity<?> verityDiploma(@PathVariable("serial-number") String serialNumber) {
        boolean res = diplomaService.verifyDiploma(serialNumber);
        return ResponseEntity.status(200).body(res);
    }
}
