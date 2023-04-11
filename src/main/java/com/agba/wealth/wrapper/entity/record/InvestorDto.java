package com.agba.wealth.wrapper.entity.record;


import lombok.Data;
import java.util.List;

import com.agba.wealth.wrapper.utils.BuildFundWealth;

@Data
public class InvestorDto {
    private Integer investorId;
    private String accountNumber;
    private String clientNumber;
    private String accountType;
    private String firstName;
    private String lastName;
    private String chineseName;
    private String idName;
    private String idType;
    private String idNumber;
    private Long idExpiryDate;
    private String idIssueCountry;
    private String gender;
    private String placeOfBirth;
    private Long dateOfBirth;
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
    private Boolean directMarketing;
    private String deliveryMethod;
    private String languagePreference;
    private String telHome;
    private String telMobile;
    private String telOffice;
    private String jobPosition;
    private Boolean requireConsentLetter;
    private String consentAddress1;
    private String consentAddress2;
    private String consentAddress3;
    private String consentCity;
    private String consentCountry;
    private String consentAttention;
    private Long riskValuationDate;
    private Integer riskLevel;
    private Boolean hasDerivativesKnowledge;
    private String tradeApprovalCondition;
    private Boolean tradeApprovalRight;
    private Boolean dividendStandingInstruction;
    private String preferredDividendCurrency;
    private String orderAuthorization;
    private Long orderAuthorizationApplicationDate;
    private String maritalStatus;
    private String piType;
    private Long piApplicationDate;
    private IfaDto primaryIfa;
    private IfaDto secondaryIfa;
    private String telFax;
    private String educationLevel;
    private String status;
    private Boolean fatca;
    private Long fatcaRenewedDate;
    private Boolean isPoliticalExposedPerson;
    private Long riskAssessmentDate;
    private Long riskAssessmentRenewalDate;
    private String riskAssessmentLevel;
    private String remark;
    private String nationality;
    private Long createdDate;
    private String createdBy;
    private Long modifiedDate;
    private String modifiedBy;
    private Long approvedDate;
    private String approvedBy;
    private Long voidedDate;
    private String voidedBy;
    private Long updatedDate;
    private String updatedBy;
    private Boolean isPrimaryMailingAddress;
    private Long dividendStandingInstructionApplicationDate;
    private Long dividendStandingInstructionRenewalDate;
    private Long rejectedDate;
    private String rejectedBy;
    private String rejectedReason;
    private Long processedDate;
    private String processedBy;
    private String approvalMethod;
    private String pendingReason;
    private String onboardingPaymentReceiptAttachment1;
    private String onboardingPaymentReceiptAttachment2;
    private String onboardingPaymentReceiptAttachment3;
    private String employerName;
    private String jobIndustry;
    private String jobIndustryOther;
    private String employmentStatus;
    private String onboardingConsentAttachment1;
    private String onboardingConsentAttachment2;
    private String onboardingConsentAttachment3;
    private String onboardingConsentRegisteredName;
    private String onboardingConsentCeNumber;
    private String onboardingEsignatureAttachment;
    private String onboardingEsignatureAttachment2;
    private String onboardingEsignatureAttachment3;
    private String homeAddress4;
    private String homeAddress5;
    private String homeAddress6;
    private String mailAddress4;
    private String mailAddress5;
    private String mailAddress6;
    private String onboardingHkidAttachment;
    private String onboardingHkidAttachment2;
    private String onboardingHkidAttachment3;
    private Integer annualIncome;
    private String chineseFirstName;
    private String chineseLastName;
    private String monitor;
    private Long monitorStartedDate;
    private Long monitorEndedDate;
    private List<String> sourceFund;
    private String otherSourceFund;
    private List<String> sourceWealth;
    private String otherSourceWealth;
    private String staffType;
    private Integer netWorth;
    private String officeAddress;
    private String corporateSourceFund;
    private String corporateSourceWealth;
    private String corporateOtherSourceFund;
    private String corporateOtherSourceWealth;
    private String companyNumber;
    private String businessTurnover;
    private Long draftedDate;
    private String overridableWarnings;
    private String internalRemarks;
    private String referSignature;
    private String invitationCode;
    private Long invitationCodeUpdatedDate;
    private String promotionCode;
    private String overrideVsmartRemarks;
    private String onboardingEddaProposalReferenceNumber;
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