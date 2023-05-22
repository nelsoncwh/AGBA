package com.agba.wealth.wrapper.entity.record;

import java.util.List;

import com.agba.wealth.wrapper.entity.response.AccountPositionListRes;
import com.agba.wealth.wrapper.utils.serializer.SerializerFloat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public record AccountPositionListDto(

        String returnCode,
        String returnMsg,
        String processTime,
        List<HoldingListDto> holdingList,

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float totalMarketValue
) {
    public AccountPositionListRes toRes() {
        AccountPositionListRes res = new AccountPositionListRes(returnCode, returnMsg, processTime, holdingList, totalMarketValue);
        for (HoldingListDto holding : res.getHoldingList()) {
            holding.setAssetName(holding.getAssetName());
        }
        return res;
    }
}
