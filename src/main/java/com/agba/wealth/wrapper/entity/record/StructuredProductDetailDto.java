package com.agba.wealth.wrapper.entity.record;

import com.agba.wealth.wrapper.utils.deserializer.YN2BoolDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StructuredProductDetailDto implements Serializable {

    String assetId;
    String productSubType;
    String nameBrief;
    String status;
    String nameEN;
    String nameZHTW;
    String nameZHCN;
    String ccy;
    Integer unitRounding;
    Integer riskLevel;
    String riskLevelDescEN;
    String issuerId;
    String issuerEN;
    String issuerZHTW;
    String issuerZHCN;
    Boolean proInvestorOnly;
    Boolean coolingOffChecking;
    Boolean complexProduct;
    String cutoffRule;
    Boolean specialCutoffTime;
    String settlementDate;
    String expiryDate;
    String displayFrom;
    String displayTo;
    String redemptionPayment;
    String remark;
    Float chargeRate;
    String tenorFreq;
    Integer tenor;
    Float issuePrice;
    @JsonDeserialize(using = YN2BoolDeserializer.class)
    Boolean principalGuaranteed;
    Boolean fixedStrike;
    Float strikeLevel;
    Boolean callable;
    String callableFreq;
    String callableFrom;
    String callableTo;
    Float callableDay;
    Float autocallLevel;
    Float couponBarrierLevel;
    Boolean couponBarrier;
    Boolean airBag;
    Float airBagLevel;
    String airBagLevelFreq;
    String airBagFrom;
    String airBagTo;
    Integer airBagDay;
    Boolean accruedCoupon;
    Float guaranteePrincipalAmt;
    Float participationRate;
    Float capLevel;
    List<UnderlyingListDto> underlyingList;
    String seriesNumber;
    Integer minFaceValue;
    Integer unitValue;
    String isin;
    Float annualizedCoupon;
    String maturityDate;
    List<DocumentDto> documents;
}
