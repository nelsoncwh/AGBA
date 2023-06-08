package com.agba.wealth.wrapper.entity.record;

import com.agba.wealth.wrapper.entity.response.AccountPositionListRes;
import com.agba.wealth.wrapper.entity.response.HoldingListRes;
import com.agba.wealth.wrapper.utils.serializer.SerializerFloat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
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
        return new AccountPositionListRes(returnCode, returnMsg, processTime, getHoldingList(), totalMarketValue);
    }

    private ArrayList<HoldingListRes> getHoldingList() {
        if (holdingList.isEmpty())
            return new ArrayList<>();
        ArrayList<HoldingListRes> list = new ArrayList<>();
        for (HoldingListDto dto : holdingList) {
            list.add(dto.toRes());
        }
        return list;
    }
}