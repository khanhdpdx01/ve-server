package io.github.khanhdpdx01.veserver.repository;

import io.github.khanhdpdx01.veserver.entity.Diploma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DiplomaRepository extends JpaRepository<Diploma, String> {
    @Query("SELECT d FROM Diploma d WHERE d.serialNumber LIKE %:keyword%")
    Page<Diploma> search(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT d FROM Diploma d WHERE d.status = :status")
    Page<Diploma> getAllDiplomasHasPendingStatus(int status, Pageable pageable);
}
