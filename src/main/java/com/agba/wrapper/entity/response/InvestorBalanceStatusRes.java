package com.agba.wrapper.entity.response;

import com.agba.wrapper.entity.record.InvestorCashBalanceDto;
import lombok.Data;

import java.util.List;

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
