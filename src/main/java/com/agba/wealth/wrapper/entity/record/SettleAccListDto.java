package com.agba.wealth.wrapper.entity.record;

import com.agba.wealth.wrapper.utils.serializer.SerializerBigDecimalNoRounding;
import com.agba.wealth.wrapper.utils.serializer.SerializerFloat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

public record SettleAccListDto(
        String externalNo,
        String settleAccId,
        String ccy,

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float cashBal,

        @JsonFormat(pattern = "0.##", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float weightPercentage,

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float unsettleBal,

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float ostBuyAmt,

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float holdingAmt,

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float withdrawableAmt,

        @JsonFormat(pattern = "0.00", shape = Shape.NUMBER_FLOAT)
        @JsonSerialize(using = SerializerBigDecimalNoRounding.class)
        BigDecimal rate,
        String status
) {

}
