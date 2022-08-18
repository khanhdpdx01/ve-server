package io.github.khanhdpdx01.veserver.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.khanhdpdx01.veserver.dto.diploma.AddDiplomaForm;
import io.github.khanhdpdx01.veserver.dto.diploma.DiplomaDTO;
import io.github.khanhdpdx01.veserver.dto.diploma.LookUpDiplomaDTO;
import io.github.khanhdpdx01.veserver.dto.diploma.VDiploma;
import io.github.khanhdpdx01.veserver.entity.*;
import io.github.khanhdpdx01.veserver.fabric.EnrollAdmin;
import io.github.khanhdpdx01.veserver.fabric.RegisterUser;
import io.github.khanhdpdx01.veserver.repository.MajorRepository;
import io.github.khanhdpdx01.veserver.repository.SpecialityRepository;
import org.hyperledger.fabric.gateway.*;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class DiplomaService {
    private final MajorRepository majorRepository;
    private final SpecialityRepository specialityRepository;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//    private final Genson genson = new Genson();

    public DiplomaService(MajorRepository majorRepository, SpecialityRepository specialityRepository) {
        this.majorRepository = majorRepository;
        this.specialityRepository = specialityRepository;
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
        List<VDiploma> vdiplomas;

        EnrollAdmin.main(null);
        RegisterUser.main(null);

        try (Gateway gateway = connect()) {
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("chaincode");
//
//            System.out.println("Submit Transaction: InitLedger creates the initial set of assets on the ledger.");
//			contract.submitTransaction("InitLedger");

            System.out.println("\n");
            result = contract.evaluateTransaction("getAllDiplomas");

            vdiplomas = new ObjectMapper().readValue(new String(result), new TypeReference<List<VDiploma>>() {
            });
        }

        return mapList(vdiplomas);
    }

    public String createDiploma(AddDiplomaForm addDiplomaForm) {
        byte[] result;

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

        try (Gateway gateway = connect()) {
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("chaincode");
//
//            System.out.println("Submit Transaction: InitLedger creates the initial set of assets on the ledger.");
//			contract.submitTransaction("InitLedger");

            System.out.println("\n");
            result = contract.submitTransaction("createDiploma",
                    addDiplomaForm.getSerialNumber(),
                    addDiplomaForm.getUserId(),
                    addDiplomaForm.getFirstName(),
                    addDiplomaForm.getLastName(),
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
                    "2019-2023");
            System.out.println("Evaluate Transaction: GetAllAssets, result: " + new String(result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new String(result);
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
        try (Gateway gateway = connect()) {
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("chaincode");

            if (lookUpDiplomaDTO.getSerialNumber() != null) {
                result = contract.evaluateTransaction("searchBySerialNumber", lookUpDiplomaDTO.getSerialNumber());

//                VDiploma diploma = genson.deserialize(result, VDiploma.class);
                VDiploma diploma = new ObjectMapper().readValue(new String(result), VDiploma.class);
                diplomaDTOs.add(map(diploma));
            } else {
                result = contract.evaluateTransaction("searchByPersonalInfo", lookUpDiplomaDTO.getFirstName(),
                        lookUpDiplomaDTO.getLastName(),
                        lookUpDiplomaDTO.getDateOfBirth().toString());
//                System.out.println(lookUpDiplomaDTO);
//                List<Diploma> diplomas = diplomaRepository.findByPersonalInfo(lookUpDiplomaDTO.getFirstName(),
//                                lookUpDiplomaDTO.getLastName(),
//                                lookUpDiplomaDTO.getDateOfBirth())
//                        .orElseThrow(() -> new RuntimeException("Diploma not found"));
//
//                if (diplomas != null) {
//                    diplomaDTOs = mapList(diplomas);
//                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return diplomaDTOs;
    }

    public VDiploma getDiploma(String serialNumber) {
        VDiploma diploma = null;

        try {
            EnrollAdmin.main(null);
            RegisterUser.main(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        byte[] result = new byte[0];
        try (Gateway gateway = connect()) {
            Network network = gateway.getNetwork("mychannel");
            Contract contract = network.getContract("chaincode");

            if (serialNumber != null) {
                result = contract.evaluateTransaction("searchBySerialNumber", serialNumber);
                diploma = new ObjectMapper().readValue(new String(result), VDiploma.class);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return diploma;
    }

    public DiplomaDTO map(VDiploma diploma) throws ParseException {
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

    public List<DiplomaDTO> mapList(List<VDiploma> diplomas) {
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
}
