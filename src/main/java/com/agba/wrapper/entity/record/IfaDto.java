package com.agba.wrapper.entity.record;

import lombok.Data;

import java.time.Instant;

@Data
public class IfaDto {
    private Integer ifaId;
    private String firstName;
    private String lastName;
    private String chineseName;
    private Long approvedDate;
    private String gender;
    private String officeAddress;
    private String email;
    private String status;
    private String languagePreference;
    private String telMobile;
    private String telOffice;
    private String grade;
    private String remark;
    private String tree;
    private String team;
    private String department;
    private IfaCompanyDto ifaCompany;
    private String accountNumber;
    private String createdBy;
    private Long createdDate;
    private Long updatedDate;
    private Long voidedDate;
    private String updatedBy;
    private String voidedBy;
    private String license1;
    private String license4;
    private String license9;

    public void setApprovedDate(Instant approvedDate) {
        if (approvedDate != null)
            this.approvedDate = approvedDate.toEpochMilli();
    }

    public void setCreatedDate(Instant createdDate) {
        if (createdDate != null)
            this.createdDate = createdDate.toEpochMilli();
    }

    public void setUpdatedDate(Instant updatedDate) {
        if (updatedDate != null)
            this.updatedDate = updatedDate.toEpochMilli();
    }

    public void setVoidedDate(Instant voidedDate) {
        if (voidedDate != null)
            this.voidedDate = voidedDate.toEpochMilli();
    }
}
