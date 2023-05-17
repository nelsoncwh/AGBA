package com.agba.wealth.wrapper.entity.response;

import com.agba.wealth.wrapper.entity.record.SettleAccListDto;
import com.agba.wealth.wrapper.utils.serializer.SerializerFloat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Iterator;
import java.util.List;

public record SettleAccountListRes(
        String returnCode,
        String returnMsg,
        String processTime,

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float purchasingPower,

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float totalUnsettleBal,

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float totalHoldingBal,

        @JsonFormat(pattern = "0.00", shape = Shape.STRING)
        @JsonSerialize(using = SerializerFloat.class)
        Float totalCashBal,
        String consolidatedCcy,
        List<SettleAccListDto> settleAccList
) {
    public SettleAccountListRes toRes() {
    	Iterator<SettleAccListDto> it = this.settleAccList.iterator();
    	while(it.hasNext()) {
    		SettleAccListDto settleAcc = it.next();
    		if(!settleAcc.ccy().equalsIgnoreCase("HKD") && !settleAcc.ccy().equalsIgnoreCase("USD") && !settleAcc.ccy().equalsIgnoreCase("CNY")) {
    			if(settleAcc.cashBal()==0) {
        			it.remove();
        		}
    		}
    	}
		return this;
    }
}
