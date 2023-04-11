package com.agba.wealth.wrapper.entity.response;

import java.util.List;

import com.agba.wealth.wrapper.entity.record.InvestorBankAccountDto;
import com.agba.wealth.wrapper.entity.record.InvestorDto;
import com.agba.wealth.wrapper.entity.record.InvestorJointDto;

public record InvestorRes(
        InvestorDto investor,
        List<InvestorBankAccountDto> investorBankAccount,
        List<InvestorJointDto> jointInvestor
) {
}
