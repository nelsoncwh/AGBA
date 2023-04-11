package com.agba.wealth.wrapper.entity.response;

import java.util.List;

import com.agba.wealth.wrapper.entity.record.AdditionalInfoDto;
import com.agba.wealth.wrapper.entity.record.InvestorBankAccountDto;

public record AdditionalRes(
        List<AdditionalInfoDto> additionalInfo,
        List<InvestorBankAccountDto> investorBankAccount
) {
}
