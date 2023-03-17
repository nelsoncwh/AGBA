package com.agba.wrapper.entity.response;

import com.agba.wrapper.entity.record.AdditionalInfoDto;
import com.agba.wrapper.entity.record.InvestorBankAccountDto;

import java.util.List;

public record AdditionalRes(
        List<AdditionalInfoDto> additionalInfo,
        List<InvestorBankAccountDto> investorBankAccount
) {
}
