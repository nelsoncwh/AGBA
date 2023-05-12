package com.agba.wealth.wrapper.entity.record;

public record SettleAccListDto(
        String settleAccId,
        String ccy,
        Integer cashBal,
        Integer unsettleBal,
        Integer ostBuyAmt,
        Integer holdingAmt,
        Integer withdrawableAmt,
        String status
) {
}
