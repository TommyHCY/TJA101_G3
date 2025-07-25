package com.pixeltribe.forumsys.reporttype.model;

import com.pixeltribe.forumsys.exception.ConflictException;
import com.pixeltribe.forumsys.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reportTypeService")
public class ReportTypeService {

    private final ReportTypeRepository reportTypeRepository;

    public ReportTypeService(ReportTypeRepository reportTypeRepository) {
        this.reportTypeRepository = reportTypeRepository;
    }

    @Transactional
    public ReportTypeDTO add(ReportTypeUpdateDTO reportTypeUpdateDTO) {
        reportTypeRepository.findByRpiType(reportTypeUpdateDTO.getRpiType())
                .ifPresent(existingType -> {
                    throw new ConflictException("檢舉類型 '" + reportTypeUpdateDTO.getRpiType() + "' 已經存在");
                });
        ReportType reportType = new ReportType();
        reportType.setRpiType(reportTypeUpdateDTO.getRpiType());
        return ReportTypeDTO.convertToReportTypeDTO(reportTypeRepository.save(reportType));
    }

    @Transactional
    public ReportTypeDTO update(Integer rpiNo, ReportTypeUpdateDTO reportTypeUpdateDTO) {

        ReportType reportType = reportTypeRepository.findById(rpiNo)
                .orElseThrow(() -> new ResourceNotFoundException("找不到檢舉類型，ID: " + rpiNo));

        reportTypeRepository.findByRpiType(reportTypeUpdateDTO.getRpiType())
                .filter(existingType -> !existingType.getId().equals(rpiNo))
                .ifPresent(existingType -> {
                    throw new ConflictException("檢舉類型 '" + reportTypeUpdateDTO.getRpiType() + "' 已經存在");
                });

        reportType.setRpiType(reportTypeUpdateDTO.getRpiType());
        return ReportTypeDTO.convertToReportTypeDTO(reportTypeRepository.save(reportType));
    }

    public ReportType getOneReportType(Integer rpiNo) {

        return reportTypeRepository.findById(rpiNo)
                .orElseThrow(() -> new ResourceNotFoundException("找不到檢舉類型，ID: " + rpiNo));
    }

    public List<ReportTypeDTO> getAllReportTypes() {

        return reportTypeRepository.findAll().stream()
                .map(ReportTypeDTO::convertToReportTypeDTO)
                .toList();
    }

}
