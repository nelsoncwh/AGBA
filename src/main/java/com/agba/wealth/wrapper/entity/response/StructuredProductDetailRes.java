package com.agba.wealth.wrapper.entity.response;

import com.agba.wealth.wrapper.entity.record.DocumentDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StructuredProductDetailRes implements Serializable {

    String assetId;
    String productSubType;
    //String productSubType;
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
    //Handled by common wrapper
    String tenorPeriod;
    Float issuePrice;
    String principalGuaranteed;
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
    //i18n convert
    String airBagLevelFreq;
    String airBagFrom;
    String airBagTo;
    Integer airBagDay;
    String accruedCoupon;
    Float guaranteePrincipalAmt;
    Float participationRate;
    Float capLevel;
    List<UnderlyingListRes> underlyingList;
    String seriesNumber;
    Integer minFaceValue;
    Integer unitValue;
    String isin;
    Float annualizedCoupon;
    String maturityDate;
    List<DocumentDto> documents;
}
