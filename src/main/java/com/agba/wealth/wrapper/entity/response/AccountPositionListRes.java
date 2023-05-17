package com.agba.wealth.wrapper.entity.response;

import com.agba.wealth.wrapper.entity.record.HoldingListDto;
import com.agba.wealth.wrapper.utils.serializer.SerializerFloat;
import com.agba.wealth.wrapper.utils.serializer.SerializerInteger;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

public record AccountPositionListRes(
        String returnCode,
        String returnMsg,
        String processTime,
        List<HoldingListDto> holdingList,

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerInteger.class)
        Integer totalMarketValue
) {
}
