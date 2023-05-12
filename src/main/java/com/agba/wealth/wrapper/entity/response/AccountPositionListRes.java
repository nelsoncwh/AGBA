package com.agba.wealth.wrapper.entity.response;

import com.agba.wealth.wrapper.entity.record.HoldingListDto;

import java.util.List;

public record AccountPositionListRes(
        String returnCode,
        String returnMsg,
        String processTime,
        List<HoldingListDto> holdingList,
        Integer totalMarketValue
) {
}
