package com.agba.wealth.wrapper.entity.response;

import lombok.Data;

import java.util.List;

import com.agba.wealth.wrapper.entity.record.InvestorCashBalanceDto;

//public record InvestorBalanceStatusRes(
//        boolean validWMS,
//        String camId,
//        String accountId,
//        String clientType,
//        List<InvestorCashBalanceDto> cashBalances
//) {
//}

@Data
public class  InvestorBalanceStatusRes {
    boolean validWMS;
    String camId;
    String accountId;
    String clientType;
    List<InvestorCashBalanceDto> cashBalances;

}
