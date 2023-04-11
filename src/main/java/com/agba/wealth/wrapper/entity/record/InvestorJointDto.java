package com.agba.wealth.wrapper.entity.record;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

import com.agba.wealth.wrapper.utils.BuildFundWealth;

@Data
public class InvestorJointDto {
    private Integer investorJointId;
    private Integer investorId;
    private String firstName;
    private String lastName;
    private String chineseName;
    private String idName;
    private String idType;
    private String idNumber;
    private LocalDate idExpiryDate;
    private String idIssueCountry;
    private String gender;
    private String maritalStatus;
    private String placeOfBirth;
    private LocalDate dateOfBirth;
    private String homeCountry;
    private String homeAddress1;
    private String homeAddress2;
    private String homeAddress3;
    private String homeCity;
    private String mailCountry;
    private String mailAddress1;
    private String mailAddress2;
    private String mailAddress3;
    private String mailCity;
    private String email;
    private String telMobile;
    private String telHome;
    private String telOffice;
    private String telFax;
    private String educationLevel;
    private String languagePreference;
    private String nationality;
    private Boolean tradeApprovalRight;
    private String jobPosition;
    private Integer riskLevel;
    private Long riskValuationDate;
    private Integer identityId;
    private Long createdDate;
    private String createdBy;
    private Long updatedDate;
    private String updatedBy;
    private Boolean isPrimaryMailingAddress;
    private String homeAddress4;
    private String homeAddress5;
    private String mailAddress4;
    private String mailAddress5;
    private String clientNumber;
    private Integer netWorth;
    private List<String> sourceFund;
    private String otherSourceFund;
    private List<String> sourceWealth;
    private String otherSourceWealth;
    private Boolean fatca;
    private Long fatcaRenewedDate;
    private String jobIndustry;
    private String jobIndustryOther;
    private String employmentStatus;
    private String officeAddress;
    private Integer annualIncome;
    private Boolean hasDerivativesKnowledge;
    private String piType;
    private Long piApplicationDate;
    private String employerName;
    private List<InvestorTaxResidencyDto> taxResidency;

    public void setSourceFund(String sourceFund) {
        if (sourceFund != null)
            this.sourceFund = BuildFundWealth.buildFundWealth(sourceFund);
    }

    public void setSourceWealth(String sourceWealth) {
        if (sourceWealth != null)
            this.sourceWealth = BuildFundWealth.buildFundWealth(sourceWealth);
    }
}