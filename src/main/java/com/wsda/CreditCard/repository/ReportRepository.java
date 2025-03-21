package com.wsda.CreditCard.repository;

import com.wsda.CreditCard.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("SELECT r FROM Report r")
    List<Report> findAllReport();
}
