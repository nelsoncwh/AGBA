package com.agba.wrapper.entity.response;

import com.agba.wrapper.entity.record.InvestorBankAccountDto;
import com.agba.wrapper.entity.record.InvestorDto;
import com.agba.wrapper.entity.record.InvestorJointDto;

import java.util.List;

public record InvestorRes(
        InvestorDto investor,
        List<InvestorBankAccountDto> investorBankAccount,
        List<InvestorJointDto> jointInvestor
) {
}
