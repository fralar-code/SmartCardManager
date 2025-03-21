package com.wsda.CreditCard.service;

import com.wsda.CreditCard.dto.ReportDto;

import java.util.List;

public interface ReportService {
    void saveReport(ReportDto reportDto);

    List<ReportDto> findAllReport();
}
