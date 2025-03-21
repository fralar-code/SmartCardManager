package com.wsda.CreditCard.service.impl;

import com.wsda.CreditCard.dto.ReportDto;
import com.wsda.CreditCard.entity.Report;
import com.wsda.CreditCard.repository.ReportRepository;
import com.wsda.CreditCard.service.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveReport(ReportDto reportDto) {
        Report report = modelMapper.map(reportDto, Report.class);
        reportRepository.save(report);
    }

    @Override
    public List<ReportDto> findAllReport() {
        List<Report> reports = reportRepository.findAllReport();
        return reports.stream()
                .map((report) -> convertToReportDto(report))
                .collect(Collectors.toList());
    }

    private ReportDto convertToReportDto(Report report) {
        ReportDto reportDto = null;
        if (report != null) {
            reportDto = modelMapper.map(report, ReportDto.class);
        }
        return reportDto;
    }
}
