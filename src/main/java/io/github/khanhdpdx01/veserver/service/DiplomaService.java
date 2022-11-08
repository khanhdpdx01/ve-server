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
import io.github.khanhdpdx01.veserver.util.IpfsUtil;
import io.github.khanhdpdx01.veserver.util.PaginationAndSortUtil;
import io.ipfs.api.IPFS;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiplomaService {
    private static String pemFileLocation;
    private static String walletLocation;
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

    public static Gateway connect(String connectionProfile) throws Exception {
        // Load a file system based wallet for managing identities.
        Path walletPath = Paths.get(walletLocation);
        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        // load a CCP
        System.out.println(connectionProfile);
        Path networkConfigPath = Paths.get(connectionProfile);

        Gateway.Builder builder = Gateway.createBuilder();

        builder.identity(wallet, "admin").networkConfig(networkConfigPath).discovery(true);
        return builder.connect();
    }

    @Value("${fabric.pem-file.location}")
    public void setPemFileLocation(String pemFileLocationArg) {
        pemFileLocation = pemFileLocationArg;
    }

    @Value("${storage.wallet-location}")
    public void setWalletLocation(String walletLocationArg) {
        walletLocation = walletLocationArg;
    }

    public List<DiplomaDetail> getAllDiplomasFromNetwork() throws Exception {
        byte[] result;
        List<DiplomaDetail> diplomas;

        Gateway gateway = connect(pemFileLocation);
        Network network = gateway.getNetwork(channel);
        Contract contract = network.getContract(chaincode);


        System.out.println("\n");
        result = contract.evaluateTransaction("GetAllDiplomas");
        diplomas = new ObjectMapper().readValue(new String(result), new TypeReference<List<DiplomaDetail>>() {
        });

        return diplomas;
    }

    public Page<DiplomaDTO> getAllDiplomas(int page, int size, String[] sort, String keyword, Integer majorId, Integer specialityId, Integer levelId, Integer rankId, Integer modeOfStudyId, Integer statusId) {
        Pageable pageable = PaginationAndSortUtil.create(page, size, sort);
        String level = Level.getAllLevels().get(levelId);
        String rank = Ranking.getAllRanks().get(rankId);
        String modeOfStudy = ModeOfStudy.getAllModeOfStudies().get(modeOfStudyId);

        String major = null, speciality = null;
        if (majorId != null) {
            Optional<Major> tmp = majorRepository.findById(majorId);
            if (tmp.isPresent()) major = tmp.get().getName();
        }

        if (specialityId != null) {
            Optional<Speciality> tmp = specialityRepository.findById(specialityId);
            if (tmp.isPresent()) speciality = tmp.get().getName();
        }

        Page<Diploma> pageRoom;

        if (keyword == null || StringUtils.isBlank(keyword)) {
            pageRoom = diplomaRepository.search(null, pageable, major, speciality, level, rank, modeOfStudy, statusId);
        } else {
            pageRoom = diplomaRepository.search(keyword, pageable, major, speciality, level, rank, modeOfStudy, statusId);
        }

        List<DiplomaDTO> diplomaDTOs = mapListDiplomaToDTO(pageRoom.getContent());

        return new PageImpl<>(diplomaDTOs, pageable, pageRoom.getTotalElements());
    }

    public Diploma createDiploma(AddDiplomaForm addDiplomaForm, List<MultipartFile> files) {
        Speciality speciality = specialityRepository.findById(addDiplomaForm.getSpecialityId())
                .orElseThrow(() -> new RuntimeException("Speciality is not found"));

        Major major = majorRepository.findById(addDiplomaForm.getMajorId())
                .orElseThrow(() -> new RuntimeException("Major is not found"));

        diplomaRepository.findById(addDiplomaForm.getSerialNumber())
                .orElseThrow(() -> new RuntimeException("Diploma is existed"));

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
                .setRank(Ranking.getAllRanks().get(addDiplomaForm.getRankId()))
                .setModeOfStudy(ModeOfStudy.getAllModeOfStudies().get(addDiplomaForm.getModeOfStudy()))
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
            Gateway gateway = connect(DiplomaService.pemFileLocation);
            Network network = gateway.getNetwork(channel);
            Contract contract = network.getContract(chaincode);

            IPFS ipfs = new IPFS(ipfsHost, ipfsPort);

            String diplomaLink = IpfsUtil.addContent(ipfs, FileUtil.loading(diploma.getDiplomaLink())).toString();
            String appendixLink = IpfsUtil.addContent(ipfs, FileUtil.loading(diploma.getAppendixLink())).toString();

//            String diplomaLink = diploma.getDiplomaLink();
//            String appendixLink = diploma.getDiplomaLink();

            Transaction transaction = contract.createTransaction("CreateDiploma");
            byte[] res = transaction.submit(diploma.getSerialNumber(),
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
            diploma.setSerialNumber(serialNumber);
            diploma.setTransactionId(transaction.getTransactionId());
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
            Gateway gateway = connect(DiplomaService.pemFileLocation);
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
                diplomaDTOs.addAll(mapListDiplomaDetailToDTO(new ObjectMapper().readValue(new String(result), new TypeReference<List<DiplomaDetail>>() {
                })));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return diplomaDTOs.stream().
                filter(diploma -> diploma.getStatus() == Status.getStatus().get(Status.RECEIVED.ordinal() + 1))
                .collect(Collectors.toList());
    }

    public Diploma getDiploma(String serialNumber) {
        Diploma diplomaResp = null;

        Diploma diploma = diplomaRepository.findById(serialNumber)
                .orElseThrow(() -> new RuntimeException("Diploma is not found"));

        if (diploma.getStatus() == Status.RECEIVED.ordinal() + 1) {
            byte[] result = new byte[0];
            try {
                Gateway gateway = connect(DiplomaService.pemFileLocation);
                Network network = gateway.getNetwork(channel);
                Contract contract = network.getContract(chaincode);

                if (serialNumber != null) {
                    result = contract.evaluateTransaction("SearchBySerialNumber", serialNumber);
                    System.out.println(new String(result));
                    diplomaResp = new ObjectMapper().readValue(new String(result), Diploma.class);
                    diplomaResp.setSerialNumber(serialNumber);
                    diplomaResp.setTransactionId(diploma.getTransactionId());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            diplomaResp = diploma;
        }

        return diplomaResp;
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

    public Diploma mapDiploma(DiplomaDetail diplomaDetail) {
        Diploma diploma = diplomaDetail.getRecord();
        diploma.setSerialNumber(diplomaDetail.getKey());
        return diploma;
    }

    public List<DiplomaDTO> mapListDiplomaToDTO(List<Diploma> diplomas) {
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

    public List<DiplomaDTO> mapListDiplomaDetailToDTO(List<DiplomaDetail> diplomaDetails) {
        List<DiplomaDTO> diplomaDTOS = new ArrayList<>();

        diplomaDetails.forEach(diplomaDetail -> {
            Diploma diploma;
            try {
                diploma = mapDiploma(diplomaDetail);
                diplomaDTOS.add(map(diploma));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        return diplomaDTOS;
    }

    public Page<DiplomaDTO> getAllDiplomasHasPendingStatus(int page, int size, String[] sort, String keyword) {
        Pageable pageable = PaginationAndSortUtil.create(page, size, sort);

        Page<Diploma> pageRoom = diplomaRepository.getAllDiplomasHasPendingStatus(Status.PENDING.ordinal() + 1, pageable);

        List<DiplomaDTO> diplomaDTOs = mapListDiplomaToDTO(pageRoom.getContent());

        return new PageImpl<>(diplomaDTOs, pageable, pageRoom.getTotalElements());
    }

    public boolean verifyDiploma(String serialNumber) {
        Diploma blcDiploma = null;

        Diploma diploma = diplomaRepository.findById(serialNumber)
                .orElseThrow(() -> new RuntimeException("Diploma is not found"));

        if (diploma.getStatus() == Status.RECEIVED.ordinal() + 1) {
            byte[] result = new byte[0];
            try {
                Gateway gateway = connect(DiplomaService.pemFileLocation);
                Network network = gateway.getNetwork(channel);
                Contract contract = network.getContract(chaincode);

                if (serialNumber != null) {
                    result = contract.evaluateTransaction("SearchBySerialNumber", serialNumber);
                    System.out.println(new String(result));
                    blcDiploma = new ObjectMapper().readValue(new String(result), Diploma.class);
                    blcDiploma.setSerialNumber(serialNumber);
                    diploma.setTransactionId(null);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        String hash = CryptoUtil.sha256(SerializationUtils.serialize(diploma));
        String blcHash = CryptoUtil.sha256(SerializationUtils.serialize(blcDiploma));

        return hash.equals(blcHash);
    }
}
