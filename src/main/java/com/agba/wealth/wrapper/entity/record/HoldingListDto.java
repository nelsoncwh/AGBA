package com.agba.wealth.wrapper.entity.record;
import java.util.List;

import com.agba.wealth.wrapper.entity.response.AccountPositionListRes;
import com.agba.wealth.wrapper.entity.response.StructuredProductRes;
import com.agba.wealth.wrapper.entity.response.UnderlyingListRes;
import com.agba.wealth.wrapper.utils.serializer.SerializerFloat;
import com.agba.wealth.wrapper.utils.serializer.SerializerInteger;
import com.agba.wealth.wrapper.utils.serializer.SerializerStock;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
public class HoldingListDto{
        String accountId;
        String productType;
        String productSubType;

        @JsonFormat(shape = Shape.STRING)
        @JsonSerialize(using = SerializerStock.class) 
        String assetId;
        
        String assetName;
//        @JsonIgnore
        String assetNameEN;
//        @JsonIgnore
        String assetNameZHCN;
//        @JsonIgnore
        String assetNameZHTW;
        String assetNameBrief;
        String ccy;
        Integer qty;
        Integer averageCost;
        Integer unsettledQty;
        String market;
        String maturityDate;

        @JsonFormat(pattern = "0.000", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float price;

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float marketValue;
        
        Integer sellableQty;
        Integer unrealisedPL;
        Integer unrealisedPLPercentage;
        Integer proportionInHoldings;
        Integer accruedIntegererest;
        String isin;
        Integer unitRounding;
        Boolean showUnrealisedPLDetail;
        String clientId;
        String clientNameEN;
        String clientNameZHCN;
        String clientNameZHTW;

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerInteger.class)
        Integer rate;
        Integer holdingQty;
}
