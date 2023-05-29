package com.agba.wealth.wrapper.entity.response;

import com.agba.wealth.wrapper.utils.serializer.SerializerBigDecimal;
import com.agba.wealth.wrapper.utils.serializer.SerializerFloat;
import com.agba.wealth.wrapper.utils.serializer.SerializerStock;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class HoldingListRes {
    String accountId;
    String productType;
    String productSubType;
    @JsonFormat(shape = Shape.STRING)
    @JsonSerialize(using = SerializerStock.class)
    String assetId;
    String assetName;
    String assetNameBrief;
    String ccy;
    @JsonFormat(pattern = "0.00", shape = Shape.NUMBER_FLOAT)
    @JsonSerialize(using = SerializerFloat.class)
    Float qty;
    @JsonFormat(pattern = "0.00", shape = Shape.NUMBER_FLOAT)
    @JsonSerialize(using = SerializerFloat.class)
    Float averageCost;
    @JsonFormat(pattern = "0.00", shape = Shape.NUMBER_FLOAT)
    @JsonSerialize(using = SerializerFloat.class)
    Float unsettledQty;
    String market;
    String maturityDate;
    @JsonFormat(pattern = "0.000", shape = Shape.NUMBER_FLOAT)
    @JsonSerialize(using = SerializerFloat.class)
    Float price;
    @JsonFormat(pattern = "0.00")
    @JsonSerialize(using = SerializerBigDecimal.class)
    BigDecimal marketValue;
    @JsonFormat(pattern = "0.00", shape = Shape.NUMBER_FLOAT)
    @JsonSerialize(using = SerializerFloat.class)
    Float sellableQty;
    @JsonFormat(pattern = "0.00", shape = Shape.NUMBER_FLOAT)
    @JsonSerialize(using = SerializerFloat.class)
    Float unrealisedPL;
    @JsonFormat(pattern = "0.00", shape = Shape.NUMBER_FLOAT)
    @JsonSerialize(using = SerializerFloat.class)
    Float unrealisedPLPercentage;
    @JsonFormat(pattern = "0.00", shape = Shape.NUMBER_FLOAT)
    @JsonSerialize(using = SerializerFloat.class)
    Float proportionInHoldings;
    @JsonFormat(pattern = "0.00", shape = Shape.NUMBER_FLOAT)
    @JsonSerialize(using = SerializerFloat.class)
    Float accruedIntegererest;
    String isin;
    Integer unitRounding;
    Boolean showUnrealisedPLDetail;
    String clientId;
    String clientName;
    @JsonFormat(pattern = "0.00")
    @JsonSerialize(using = SerializerBigDecimal.class)
    BigDecimal rate;
    Integer holdingQty;
    @JsonFormat(pattern = "0.00")
    @JsonSerialize(using = SerializerBigDecimal.class)
    BigDecimal marketValueHKD;
}
