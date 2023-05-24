package com.agba.wealth.wrapper.entity.record;

import com.agba.wealth.wrapper.entity.response.HoldingListRes;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class HoldingListDto {
    String accountId;
    String productType;
    String productSubType;
    String assetId;
    String assetName;
    String assetNameBrief;
    String ccy;
    Float qty;
    Float averageCost;
    Float unsettledQty;
    String market;
    String maturityDate;
    Float price;
    Float marketValue;
    Float sellableQty;
    Float unrealisedPL;
    Float unrealisedPLPercentage;
    Float proportionInHoldings;
    Float accruedIntegererest;
    String isin;
    Integer unitRounding;
    Boolean showUnrealisedPLDetail;
    String clientId;
    String clientName;
    Float rate;
    Integer holdingQty;
    Float marketValueHKD;

    public HoldingListRes toRes() {
        ModelMapper mapper = new ModelMapper();
        HoldingListRes res = mapper.map(this, HoldingListRes.class);
        res.setMarketValueHKD(this.marketValue * this.rate);
        return res;
    }
}
