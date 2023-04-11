package com.agba.wrapper.entity.record;

import lombok.Data;
import java.util.List;


@Data
public class AdditionalInfoDto {
    private String clientNumber;
    private String employmentStatus;
    private String employerName;
    private String officeAddress;
    private String jobIndustry;
    private String jobIndustryOther;
    private String jobPosition;
    private Integer annualIncome;
    private Integer netWorth;
    private List<String> sourceFund;
    private String otherSourceFund;
    private List<String> sourceWealth;
    private String otherSourceWealth;
    private Integer riskLevel;
    private Long riskValuationDate;
    private String educationLevel;
    private String nationality;
    private Boolean hasDerivativesKnowledge;
    private String piType;
    private Long piApplicationDate;
}
