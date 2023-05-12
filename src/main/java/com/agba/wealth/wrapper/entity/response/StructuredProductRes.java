package com.agba.wealth.wrapper.entity.response;

import com.agba.wealth.wrapper.entity.record.DocumentDto;
import com.agba.wealth.wrapper.utils.serializer.SerializerFloat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StructuredProductRes implements Serializable {

    String assetId;
    String productSubType;
    String productSubTypeName;
    String nameBrief;
    String status;
    //Handled by common wrapper
    String name;
    String ccy;
    Integer unitRounding;
    Integer riskLevel;
    //Handled by common wrapper
    String riskLevelDesc;
    String issuerId;
    //Handled by common wrapper
    String issuer;
    Boolean proInvestorOnly;
    Boolean coolingOffChecking;
//    Boolean complexProduct;
    String cutoffRule;
    Boolean specialCutoffTime;
    String settlementDate;
    String expiryDate;
    String displayFrom;
    String displayTo;
    String redemptionPayment;
    String redemptionMethod;
    String remark;
    
//    @JsonProperty("ChargeRate")
    
    @JsonFormat(pattern = "0.##", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float chargeRate;
//    String chargeRate;
    String tenorFreq;
    Integer tenor;
    //Handled by common wrapper
    String tenorPeriod;

    @JsonFormat(pattern = "0.##", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float issuePrice;
    String principalGuaranteed;

    @JsonFormat(pattern = "0.####", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float flatCouponRate;
    Boolean fixedStrike;

    @JsonFormat(pattern = "0.##", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float strikeLevel;
    Boolean callable;
    String callableFreq;
    String callableFrom;
    String callableTo;

    @JsonFormat(pattern = "0.##", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float callableDay;

    @JsonFormat(pattern = "0.##", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float autocallLevel;
    String autocallLevelFreq;
    Boolean couponBarrier;
    String couponBarrierFrom;

    @JsonFormat(pattern = "0.##", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float couponBarrierLevel;
    String couponBarrierTo;
    Boolean airBag;

    @JsonFormat(pattern = "0.##", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float airBagLevel;
    //i18n convert
    String airBagLevelFreq;
    String airBagFrom;
    String airBagTo;
//    Integer airBagDay;
    String accruedCoupon;
//    Float guaranteePrincipalAmt;

    @JsonFormat(pattern = "0.##", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float participationRate;

    @JsonFormat(pattern = "0.##", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float capLevel;
    List<UnderlyingListRes> underlyingList;
    String seriesNumber;
    Integer minFaceValue;
    Integer unitValue;
    String isin;

    @JsonFormat(pattern = "0.##", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float annualizedCoupon;

    @JsonFormat(pattern = "0.##", shape = Shape.STRING)
    @JsonSerialize(using = SerializerFloat.class)
    Float guaranteePrincipalAmt;
    String maturityDate;
    List<DocumentDto> documents;
}
