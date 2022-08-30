package io.github.khanhdpdx01.veserver.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.khanhdpdx01.veserver.dto.diploma.AddDiplomaForm;
import io.github.khanhdpdx01.veserver.dto.diploma.DiplomaDTO;
import io.github.khanhdpdx01.veserver.dto.diploma.DiplomaDetail;
import io.github.khanhdpdx01.veserver.dto.diploma.LookUpDiplomaDTO;
import io.github.khanhdpdx01.veserver.entity.*;
import io.github.khanhdpdx01.veserver.identity.EnrollAdmin;
import io.github.khanhdpdx01.veserver.identity.RegisterUser;
import io.github.khanhdpdx01.veserver.repository.DiplomaRepository;
import io.github.khanhdpdx01.veserver.repository.MajorRepository;
import io.github.khanhdpdx01.veserver.repository.SpecialityRepository;
import io.github.khanhdpdx01.veserver.util.IpfsUtil;
import io.github.khanhdpdx01.veserver.util.PaginationAndSortUtil;
import io.ipfs.api.IPFS;
import org.hyperledger.fabric.gateway.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class DiplomaService {
    private final MajorRepository majorRepository;
    private final SpecialityRepository specialityRepository;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private final DiplomaRepository diplomaRepository;
//    private final Genson genson = new Genson();


    public DiplomaService(MajorRepository majorRepository, SpecialityRepository specialityRepository, DiplomaRepository diplomaRepository) {
        this.majorRepository = majorRepository;
        this.specialityRepository = specialityRepository;
        this.diplomaRepository = diplomaRepository;
    }

    public static Gateway connect() throws Exception {
        // Load a file system based wallet for managing identities.
        Path walletPath = Paths.get("wallet");
        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        // load a CCP
        Path networkConfigPath = Paths.get("/home/khanh/VTS/vecert-network/organizations/peerOrganizations/issuer.com/connection-org1.yaml");

        Gateway.Builder builder = Gateway.createBuilder();

        builder.identity(wallet, "userB").networkConfig(networkConfigPath).discovery(true);
        return builder.connect();
    }

    public List<DiplomaDTO> getAllDiplomasFromNetwork() throws Exception {
        byte[] result;
        List<Diploma> diplomas;

        EnrollAdmin.main(null);
        RegisterUser.main(null);

        Gateway gateway = connect();
        Network network = gateway.getNetwork("mychannel");
        Contract contract = network.getContract("chaincode");

        System.out.println("\n");
        result = contract.evaluateTransaction("getAllDiplomas");

        diplomas = new ObjectMapper().readValue(new String(result), new TypeReference<List<Diploma>>() {
        });

        return mapList(diplomas);
    }

    public Page<DiplomaDTO> getAllDiplomas(int page, int size, String[] sort, String keyword) {
        Pageable pageable = PaginationAndSortUtil.create(page, size, sort);

        Page<Diploma> pageRoom;

        if (keyword == null || keyword.isBlank()) {
            pageRoom = diplomaRepository.findAll(pageable);
        } else {
            pageRoom = diplomaRepository.search(keyword, pageable);
        }

        List<DiplomaDTO> diplomaDTOs = mapList(pageRoom.getContent());

        return new PageImpl<>(diplomaDTOs, pageable, pageRoom.getTotalElements());
    }

    public Diploma createDiploma(AddDiplomaForm addDiplomaForm, List<MultipartFile> files) {
        Diploma diploma;

        Speciality speciality = specialityRepository.findById(addDiplomaForm.getSpecialityId())
                .orElseThrow(() -> new RuntimeException("Speciality is not found"));

        Major major = majorRepository.findById(addDiplomaForm.getMajorId())
                .orElseThrow(() -> new RuntimeException("Major is not found"));

        try {
            EnrollAdmin.main(null);
            RegisterUser.main(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            Gateway gateway = connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("chaincode");
//
//            System.out.println("Submit Transaction: InitLedger creates the initial set of assets on the ledger.");
//			contract.submitTransaction("InitLedger");
            IPFS ipfs = new IPFS("localhost", 5002);

            String diplomaLink = IpfsUtil.addContent(ipfs, files.get(0).getInputStream()).toString();
            String appendixLink = IpfsUtil.addContent(ipfs, files.get(1).getInputStream()).toString();

            byte[] res = contract.submitTransaction("createDiploma",
                    addDiplomaForm.getSerialNumber(),
                    addDiplomaForm.getUserId().trim(),
                    addDiplomaForm.getFirstName().trim(),
                    addDiplomaForm.getLastName().trim(),
                    formatter.format(addDiplomaForm.getDateOfBirth()),
                    addDiplomaForm.isGender() ? "Nam" : "Nữ",
                    addDiplomaForm.getPlaceOfBirth(),
                    addDiplomaForm.getGrade(),
                    Level.getAllLevels().get(addDiplomaForm.getLevelId()),
                    Ranking.getAllRanks().get(addDiplomaForm.getRankId()),
                    ModeOfStudy.getAllModeOfStudies().get(addDiplomaForm.getModeOfStudy()),
                    speciality.getName(),
                    addDiplomaForm.getGraduation(),
                    formatter.format(addDiplomaForm.getDateOfGraduate()),
                    addDiplomaForm.getRefNumber(),
                    Status.getStatus().get(addDiplomaForm.getStatus()),
                    Double.toString(addDiplomaForm.getGpa()),
                    Integer.toString(addDiplomaForm.getTotalCredits()),
                    TrainingLanguage.getAllLanguages().get(addDiplomaForm.getTrainingLanguage()),
                    Double.toString(addDiplomaForm.getTrainingTime()),
                    formatter.format(addDiplomaForm.getDateOfEnrollment()),
                    major.getName(),
                    "2019-2023",
                    diplomaLink,
                    appendixLink);

            diploma = new ObjectMapper().readValue(new String(res), Diploma.class);
            diplomaRepository.save(diploma);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return diploma;
    }

    public List<DiplomaDTO> lookUpDiploma(LookUpDiplomaDTO lookUpDiplomaDTO) {
        List<DiplomaDTO> diplomaDTOs = new ArrayList<>();

        try {
            EnrollAdmin.main(null);
            RegisterUser.main(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        byte[] result = new byte[0];
        try {
            Gateway gateway = connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("chaincode");

            if (lookUpDiplomaDTO.getSerialNumber() != null && !lookUpDiplomaDTO.getSerialNumber().isEmpty()) {
                result = contract.evaluateTransaction("searchBySerialNumber", lookUpDiplomaDTO.getSerialNumber());

//                VDiploma diploma = genson.deserialize(result, VDiploma.class);
                Diploma diploma = diplomaRepository.findById(lookUpDiplomaDTO.getSerialNumber())
                        .orElseThrow(() -> new RuntimeException("Diploma is not found"));
                diplomaDTOs.add(map(diploma));
            } else {
                LocalDate localDate = LocalDate.parse(lookUpDiplomaDTO.getDateOfBirth());
                result = contract.evaluateTransaction("searchByPersonalInfo", lookUpDiplomaDTO.getFirstName(),
                        lookUpDiplomaDTO.getLastName(),
                        localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

                System.out.println(new String((result)));
                diplomaDTOs.addAll(mapList(new ObjectMapper().readValue(new String(result), new TypeReference<List<Diploma>>() {
                })));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return diplomaDTOs;
    }

    public DiplomaDetail getDiploma(String serialNumber) {
        DiplomaDetail diploma = null;

        try {
            EnrollAdmin.main(null);
            RegisterUser.main(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        byte[] result = new byte[0];
        try {
            Gateway gateway = connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("chaincode");

            if (serialNumber != null) {
                result = contract.evaluateTransaction("searchBySerialNumber", serialNumber);
                System.out.println(new String(result));
                diploma = new ObjectMapper().readValue(new String(result), DiplomaDetail.class);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return diploma;
    }

    public DiplomaDTO map(Diploma diploma) throws ParseException {
        DiplomaDTO diplomaDTO = new DiplomaDTO();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatter.parse(diploma.getDateOfGraduation()));

        diplomaDTO.setLastName(diploma.getLastName())
                .setFirstName(diploma.getFirstName())
                .setDateOfBirth(diploma.getDateOfBirth())
                .setGender(diploma.getGender())
                .setPlaceOfBirth(diploma.getPlaceOfBirth())
                .setLevel(diploma.getLevel())
                .setRank(diploma.getRank())
                .setModeOfStudy(diploma.getModeOfStudy())
                .setSpeciality(diploma.getSpeciality())
                .setYearOfGraduation("" + calendar.get(Calendar.YEAR))
                .setSerialNumber(diploma.getSerialNumber())
                .setStatus(diploma.getStatus())
                .setRefNumber(diploma.getRefNumber())
                .setGraduation(diploma.getGraduation());

        return diplomaDTO;
    }

    public List<DiplomaDTO> mapList(List<Diploma> diplomas) {
        List<DiplomaDTO> diplomaDTOS = new ArrayList<>();
        diplomas.forEach(diploma -> {
            try {
                diplomaDTOS.add(map(diploma));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
        return diplomaDTOS;
    }

    public String test() {
        try {
            EnrollAdmin.main(null);
            RegisterUser.main(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        byte[] result = new byte[0];
        try {
            Gateway gateway = connect();
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("chaincode");

            result = contract.evaluateTransaction("getString");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new String(result);
    }
}
