package io.github.khanhdpdx01.veserver.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.khanhdpdx01.veserver.dto.diploma.AddDiplomaForm;
import io.github.khanhdpdx01.veserver.dto.diploma.DiplomaDTO;
import io.github.khanhdpdx01.veserver.dto.diploma.DiplomaDetail;
import io.github.khanhdpdx01.veserver.dto.diploma.LookUpDiplomaDTO;
import io.github.khanhdpdx01.veserver.entity.*;
import io.github.khanhdpdx01.veserver.repository.DiplomaRepository;
import io.github.khanhdpdx01.veserver.repository.MajorRepository;
import io.github.khanhdpdx01.veserver.repository.SpecialityRepository;
import io.github.khanhdpdx01.veserver.util.CryptoUtil;
import io.github.khanhdpdx01.veserver.util.FileUtil;
import io.github.khanhdpdx01.veserver.util.PaginationAndSortUtil;
import org.hyperledger.fabric.gateway.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
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
import java.util.stream.Collectors;

@Service
public class DiplomaService {
    private final MajorRepository majorRepository;
    private final SpecialityRepository specialityRepository;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private final DiplomaRepository diplomaRepository;
    @Value("${ipfs.host}")
    private String ipfsHost;
    @Value("${ipfs.port}")
    private int ipfsPort;
    @Value("${fabric.channel-name}")
    private String channel;
    @Value("${fabric.chaincode-name}")
    private String chaincode;

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
//        Path networkConfigPath = walletPath.resolve("connection.json");
        Gateway.Builder builder = Gateway.createBuilder();

        builder.identity(wallet, "admin").networkConfig(networkConfigPath).discovery(true);
        return builder.connect();
    }

    public List<DiplomaDTO> getAllDiplomasFromNetwork() throws Exception {
        byte[] result;
        List<Diploma> diplomas;

        Gateway gateway = connect();
        Network network = gateway.getNetwork(channel);
        Contract contract = network.getContract(chaincode);

        System.out.println("\n");
        result = contract.evaluateTransaction("GetAllDiplomas");
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
        Speciality speciality = specialityRepository.findById(addDiplomaForm.getSpecialityId())
                .orElseThrow(() -> new RuntimeException("Speciality is not found"));

        Major major = majorRepository.findById(addDiplomaForm.getMajorId())
                .orElseThrow(() -> new RuntimeException("Major is not found"));

        FileUtil.save(files.get(0));
        FileUtil.save(files.get(1));

        Diploma diploma = new Diploma();
        diploma.setSerialNumber(addDiplomaForm.getSerialNumber())
                .setUserId(addDiplomaForm.getUserId().trim())
                .setFirstName(addDiplomaForm.getFirstName().trim())
                .setLastName(addDiplomaForm.getLastName().trim())
                .setDateOfBirth(formatter.format(addDiplomaForm.getDateOfBirth()))
                .setGender(addDiplomaForm.isGender() ? "Nam" : "Nữ")
                .setPlaceOfBirth(addDiplomaForm.getPlaceOfBirth())
                .setGrade(addDiplomaForm.getGrade())
                .setLevel(Level.getAllLevels().get(addDiplomaForm.getLevelId()))
                .setRank(Ranking.getAllRanks().get(addDiplomaForm.getRankId())).
                setModeOfStudy(ModeOfStudy.getAllModeOfStudies().get(addDiplomaForm.getModeOfStudy()))
                .setSpeciality(speciality.getName())
                .setGraduation(addDiplomaForm.getGraduation())
                .setDateOfGraduation(formatter.format(addDiplomaForm.getDateOfGraduate()))
                .setRefNumber(addDiplomaForm.getRefNumber())
                .setStatus(Status.PENDING.ordinal() + 1) // +1 because index of enum start from 0.
                .setGpa(Double.toString(addDiplomaForm.getGpa()))
                .setTotalCredits(Integer.toString(addDiplomaForm.getTotalCredits()))
                .setTrainingLanguage(TrainingLanguage.getAllLanguages().get(addDiplomaForm.getTrainingLanguage()))
                .setTime(Double.toString(addDiplomaForm.getTrainingTime()))
                .setDateOfEnrollment(formatter.format(addDiplomaForm.getDateOfEnrollment()))
                .setMajor(major.getName())
                .setSession("2019-2023")
                .setDiplomaLink(files.get(0).getOriginalFilename())
                .setAppendixLink(files.get(1).getOriginalFilename());
        Diploma createdDiploma = diplomaRepository.save(diploma);

        return createdDiploma;
    }

    public Diploma storeDiplomaToBlockchain(String serialNumber) {
        Diploma diploma = diplomaRepository.findById(serialNumber)
                .orElseThrow(() -> new RuntimeException("Diploma is not found"));

        try {
            Gateway gateway = connect();
            Network network = gateway.getNetwork(channel);
            Contract contract = network.getContract(chaincode);

//            IPFS ipfs = new IPFS(ipfsHost, ipfsPort);
//
//            String diplomaLink = IpfsUtil.addContent(ipfs, FileUtil.loading(diploma.getDiplomaLink())).toString();
//            String appendixLink = IpfsUtil.addContent(ipfs, FileUtil.loading(diploma.getAppendixLink())).toString();

            String diplomaLink = diploma.getDiplomaLink();
            String appendixLink = diploma.getDiplomaLink();

            byte[] res = contract.submitTransaction("CreateDiploma",
                    diploma.getSerialNumber(),
                    diploma.getUserId().trim(),
                    diploma.getFirstName().trim(),
                    diploma.getLastName().trim(),
                    diploma.getDateOfBirth(),
                    diploma.getGender(),
                    diploma.getPlaceOfBirth(),
                    diploma.getGrade(),
                    diploma.getLevel(),
                    diploma.getRank(),
                    diploma.getModeOfStudy(),
                    diploma.getSpeciality(),
                    diploma.getGraduation(),
                    diploma.getDateOfGraduation(),
                    diploma.getRefNumber(),
                    Integer.toString(Status.RECEIVED.ordinal() + 1),
                    diploma.getGpa(),
                    diploma.getTotalCredits(),
                    diploma.getTrainingLanguage(),
                    diploma.getTime(),
                    diploma.getDateOfEnrollment(),
                    diploma.getMajor(),
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

        byte[] result = new byte[0];
        try {
            Gateway gateway = connect();
            Network network = gateway.getNetwork(channel);
            Contract contract = network.getContract(chaincode);

            if (lookUpDiplomaDTO.getSerialNumber() != null && !lookUpDiplomaDTO.getSerialNumber().isEmpty()) {
                result = contract.evaluateTransaction("SearchBySerialNumber", lookUpDiplomaDTO.getSerialNumber());

//                VDiploma diploma = genson.deserialize(result, VDiploma.class);
                Diploma diploma = diplomaRepository.findById(lookUpDiplomaDTO.getSerialNumber())
                        .orElseThrow(() -> new RuntimeException("Diploma is not found"));
                diplomaDTOs.add(map(diploma));
            } else {
                LocalDate localDate = LocalDate.parse(lookUpDiplomaDTO.getDateOfBirth());
                result = contract.evaluateTransaction("SearchByPersonalInfo", lookUpDiplomaDTO.getFirstName(),
                        lookUpDiplomaDTO.getLastName(),
                        localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

                System.out.println(new String((result)));
                diplomaDTOs.addAll(mapList(new ObjectMapper().readValue(new String(result), new TypeReference<List<Diploma>>() {
                })));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return diplomaDTOs.stream().
                filter(diploma -> diploma.getStatus() == Status.getStatus().get(Status.RECEIVED.ordinal() + 1))
                .collect(Collectors.toList());
    }

    public DiplomaDetail getDiploma(String serialNumber) {
        DiplomaDetail diplomaDetail = null;

        Diploma diploma = diplomaRepository.findById(serialNumber)
                .orElseThrow(() -> new RuntimeException("Diploma is not found"));

        if (diploma.getStatus() == Status.RECEIVED.ordinal() + 1) {
            byte[] result = new byte[0];
            try {
                Gateway gateway = connect();
                Network network = gateway.getNetwork(channel);
                Contract contract = network.getContract(chaincode);

                if (serialNumber != null) {
                    result = contract.evaluateTransaction("SearchBySerialNumber", serialNumber);
                    System.out.println(new String(result));
                    diplomaDetail = new ObjectMapper().readValue(new String(result), DiplomaDetail.class);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            diplomaDetail = new DiplomaDetail();
            diplomaDetail.setDiploma(diploma);
        }

        return diplomaDetail;
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
                .setStatus(Status.getStatus().get(diploma.getStatus()))
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
        byte[] result = new byte[0];
        try {
            Gateway gateway = connect();
            Network network = gateway.getNetwork(channel);
            Contract contract = network.getContract(chaincode);

            result = contract.evaluateTransaction("Test");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new String(result);
    }

    public Page<DiplomaDTO> getAllDiplomasHasPendingStatus(int page, int size, String[] sort, String keyword) {
        Pageable pageable = PaginationAndSortUtil.create(page, size, sort);

        Page<Diploma> pageRoom = diplomaRepository.getAllDiplomasHasPendingStatus(Status.PENDING.ordinal() + 1, pageable);

        List<DiplomaDTO> diplomaDTOs = mapList(pageRoom.getContent());

        return new PageImpl<>(diplomaDTOs, pageable, pageRoom.getTotalElements());
    }

    public boolean verifyDiploma(String serialNumber) {
        Diploma blcDiploma = null;

        Diploma diploma = diplomaRepository.findById(serialNumber)
                .orElseThrow(() -> new RuntimeException("Diploma is not found"));

        if (diploma.getStatus() == Status.RECEIVED.ordinal() + 1) {
            byte[] result = new byte[0];
            try {
                Gateway gateway = connect();
                Network network = gateway.getNetwork(channel);
                Contract contract = network.getContract(chaincode);

                if (serialNumber != null) {
                    result = contract.evaluateTransaction("SearchBySerialNumber", serialNumber);
                    System.out.println(new String(result));
                    DiplomaDetail diplomaDetail = new ObjectMapper().readValue(new String(result), DiplomaDetail.class);
                    blcDiploma = diplomaDetail.getDiploma();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(diploma);
        System.out.println(blcDiploma);

        String hash = CryptoUtil.sha256(SerializationUtils.serialize(diploma));
        String blcHash = CryptoUtil.sha256(SerializationUtils.serialize(blcDiploma));

        return hash.equals(blcHash);
    }
}
