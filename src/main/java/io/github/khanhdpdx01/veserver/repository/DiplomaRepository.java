package io.github.khanhdpdx01.veserver.repository;

import io.github.khanhdpdx01.veserver.entity.Diploma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiplomaRepository extends JpaRepository<Diploma, String> {
    @Query("SELECT d FROM Diploma d " +
            "WHERE (:keyword is null or d.serialNumber LIKE %:keyword% " +
            "OR d.firstName LIKE %:keyword% " +
            "OR d.lastName LIKE %:keyword%) " +
            "AND (:major is null or d.major=:major) " +
            "AND (:speciality is null or d.speciality=:speciality) " +
            "AND (:rank is null or d.rank=:rank) " +
            "AND (:level is null or d.level=:level) " +
            "AND (:modeOfStudy is null or d.modeOfStudy=:modeOfStudy) " +
            "AND (:status is null or d.status=:status)")
    Page<Diploma> search(@Param("keyword") String keyword,
                         Pageable pageable,
                         @Param("major") String major,
                         @Param("speciality") String speciality,
                         @Param("level") String level,
                         @Param("rank") String rank,
                         @Param("modeOfStudy") String modeOfStudy,
                         @Param("status") Integer status);

    @Query("SELECT d FROM Diploma d WHERE d.status = :status")
    Page<Diploma> getAllDiplomasHasPendingStatus(int status, Pageable pageable);
}
