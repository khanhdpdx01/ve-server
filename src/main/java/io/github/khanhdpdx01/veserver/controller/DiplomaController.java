package io.github.khanhdpdx01.veserver.controller;

import io.github.khanhdpdx01.veserver.dto.diploma.AddDiplomaForm;
import io.github.khanhdpdx01.veserver.dto.diploma.DiplomaDTO;
import io.github.khanhdpdx01.veserver.dto.diploma.DiplomaDetail;
import io.github.khanhdpdx01.veserver.dto.diploma.LookUpDiplomaDTO;
import io.github.khanhdpdx01.veserver.dto.pagination.PaginationParams;
import io.github.khanhdpdx01.veserver.dto.pagination.PaginationResponse;
import io.github.khanhdpdx01.veserver.dto.training_program.ChoiceTrainningProgramFeild;
import io.github.khanhdpdx01.veserver.entity.*;
import io.github.khanhdpdx01.veserver.repository.MajorRepository;
import io.github.khanhdpdx01.veserver.repository.SpecialityRepository;
import io.github.khanhdpdx01.veserver.service.DiplomaService;
import io.github.khanhdpdx01.veserver.util.IpfsUtil;
import io.github.khanhdpdx01.veserver.util.PaginationAndSortUtil;
import io.ipfs.api.IPFS;
import io.ipfs.multihash.Multihash;
import org.springframework.data.domain.Page;
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
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    public ResponseEntity<?> createDiploma(@RequestPart("diploma") AddDiplomaForm addDiplomaForm,
                                           @RequestPart("files") List<MultipartFile> files) {
        Diploma res = diplomaService.createDiploma(addDiplomaForm, files);
        return ResponseEntity.status(200).body(res);
    }

    @PostMapping(value = "/test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createDiploma(@RequestPart("file") MultipartFile file) {
        Multihash multihash;
        try {
            InputStream inputStream = file.getInputStream();
            IPFS ipfs = new IPFS("localhost", 5002);
            multihash = IpfsUtil.addContent(ipfs, inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(200).body(multihash.toString());
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','SUPERADMIN')")
    public ResponseEntity<?> getAllDiplomas(@Valid PaginationParams params) {
        Page<DiplomaDTO> pageDiplomaDTO = diplomaService.getAllDiplomas(params.getPage(),
                params.getSize(), params.getSort(), params.getKeyword());
        System.out.println(params);
        PaginationResponse<DiplomaDTO> response = PaginationAndSortUtil.map(pageDiplomaDTO);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{serial-number}")
    public ResponseEntity<?> getDiploma(@PathVariable("serial-number") String serialNumber) {
        DiplomaDetail diploma = diplomaService.getDiploma(serialNumber);
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
        return ResponseEntity.status(200).body(diplomas);
    }
}
