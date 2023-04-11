package com.agba.wrapper.entity.record;

import lombok.Data;

@Data
public class InvestorBankAccountDto {
    private Integer investorBankAccountId;
    private String bankCode;
    private String bankName;
    private String bankAccountHolderName;
    private String bankAccountNumber;
    private String currency;
    private String beneficiaryBank;
    private String correspondentBankAndBranch;
    private String correspondentBankSwiftCode;
    private String beneficiaryBankSwiftCode;
    private Long createdDate;
    private String createdBy;
    private Long updatedDate;
    private String updatedBy;
    private String investorBankAccountCode;
    private Boolean isDefault;
    private String status;
    private Boolean isEddaDeposit;
}