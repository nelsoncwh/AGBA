package com.agba.wealth.wrapper.entity.response;

import com.agba.wealth.wrapper.entity.record.SettleAccListDto;

import java.util.List;

public record SettleAccountListRes(
        String returnCode,
        String returnMsg,
        String processTime,
        Integer purchasingPower,
        String consolidatedCcy,
        List<SettleAccListDto> settleAccList
) {
}
