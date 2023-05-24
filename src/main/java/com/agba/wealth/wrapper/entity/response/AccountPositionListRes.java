package com.agba.wealth.wrapper.entity.response;

import com.agba.wealth.wrapper.utils.serializer.SerializerFloat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountPositionListRes {
    String returnCode;
    String returnMsg;
    String processTime;
    List<HoldingListRes> holdingList;
    @JsonFormat(pattern = "0.00", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float totalMarketValue;
}
