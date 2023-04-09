package com.agba.wrapper.entity.record;

import lombok.Data;

import java.time.Instant;

@Data
public class InvestorTaxResidencyDto {
    private String country;
    private String taxIdentificationNumber;
    private Boolean tinAvailable;
    private String tinUnavailableReason;
    private String tinUnavailableOtherReason;
    private Long createdDate;
    private Long updatedDate;
    private String status;

}
