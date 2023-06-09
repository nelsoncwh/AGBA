package com.agba.wealth.wrapper.entity.response;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Data
public class AccountPositionListRes {
    String returnCode;
    String returnMsg;
    String processTime;
    List<HoldingListRes> holdingList;
    BigDecimal totalMarketValue;
    BigDecimal totalMarketValueSP;
    BigDecimal totalMarketValueStock;

    public AccountPositionListRes(String returnCode, String returnMsg, String processTime, List<HoldingListRes> holdingList) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
        this.processTime = processTime;
        this.holdingList = holdingList;

        totalMarketValue = new BigDecimal(0);
        totalMarketValueSP = new BigDecimal(0);
        totalMarketValueStock = new BigDecimal(0);

        for (HoldingListRes hList : holdingList) {
            totalMarketValue = totalMarketValue.add(hList.marketValueHKD);
            if (hList.getProductType().equalsIgnoreCase("STOCK"))
                totalMarketValueStock = totalMarketValueStock.add(hList.marketValueHKD);
            if (hList.getProductType().equalsIgnoreCase("SP"))
                totalMarketValueSP = totalMarketValueSP.add(hList.marketValueHKD);
        }
        totalMarketValue = totalMarketValue.setScale(2, RoundingMode.DOWN);
        totalMarketValueSP = totalMarketValueSP.setScale(2, RoundingMode.DOWN);
        totalMarketValueStock = totalMarketValueStock.setScale(2, RoundingMode.DOWN);
    }
}
