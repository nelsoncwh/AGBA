package com.agba.wrapper.entity.record;

import com.agba.wrapper.utils.BuildFundWealth;
import lombok.Data;

import java.time.Instant;
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

//    public void setPiApplicationDate(Instant piApplicationDate) {
//        if (piApplicationDate != null)
//            this.piApplicationDate = piApplicationDate.toEpochMilli();
//    }

//    public void setSourceFund(String sourceFund) {
//        if (sourceFund != null)
//            this.sourceFund = BuildFundWealth.buildFundWealth(sourceFund);
//    }

//    public void setSourceWealth(String sourceWealth) {
//        if (sourceWealth != null)
//            this.sourceWealth = BuildFundWealth.buildFundWealth(sourceWealth);
//    }

//    public void setRiskValuationDate(Instant riskValuationDate) {
//        if (riskValuationDate != null)
//            this.riskValuationDate = riskValuationDate.toEpochMilli();
//    }
}
