package com.agba.wealth.wrapper.entity.record;

import lombok.Data;

@Data
public class IfaCompanyDto {
    private Integer ifaCompanyId;
    private String code;
    private String alternativePrefix;
    private String zip;
    private String country;
    private Long createdDate;
    private Long updatedDate;
    private String email;
    private String website;
    private String telephone;
    private String responsibleOfficer1;
    private String responsibleOfficer2;
    private String updatedBy;
}
