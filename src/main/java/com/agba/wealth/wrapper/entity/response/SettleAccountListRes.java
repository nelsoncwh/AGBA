package com.agba.wealth.wrapper.entity.response;

import com.agba.wealth.wrapper.entity.record.SettleAccListDto;
import com.agba.wealth.wrapper.utils.serializer.SerializerFloat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Arrays;
import java.util.Comparator;
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
    static class CustomComparator implements Comparator<SettleAccListDto> {
        private static final List<String> definedOrder = Arrays.asList("HKD", "USD", "CNY");
        private static final int EXCEPTION_INDEX = 999;

        @Override
        public int compare(SettleAccListDto o1, SettleAccListDto o2) {
            return compareIndex(o1, o2);
        }

        private int compareIndex(SettleAccListDto o1, SettleAccListDto o2) {
            int i1 = orderIndex(definedOrder.indexOf(o1.ccy().toUpperCase()));
            int i2 = orderIndex(definedOrder.indexOf(o2.ccy().toUpperCase()));
            if (i1 != EXCEPTION_INDEX || i2 != EXCEPTION_INDEX) {
                return Integer.compare(i1, i2);
            }
            return o1.ccy().toUpperCase().compareTo(o2.ccy().toUpperCase());
        }

        private int orderIndex(int i) {
            return i < 0 ? EXCEPTION_INDEX : i;
        }
    }

    public SettleAccountListRes toRes() {
        Iterator<SettleAccListDto> it = this.settleAccList.iterator();
        while (it.hasNext()) {
            SettleAccListDto settleAcc = it.next();
            if (settleAcc.ccy().equalsIgnoreCase("HKD") || settleAcc.ccy().equalsIgnoreCase("USD") || settleAcc.ccy().equalsIgnoreCase("CNY")) {
                continue;
            }
            if (settleAcc.cashBal() == 0) {
                it.remove();
            }
        }
        settleAccList.sort(new CustomComparator());
        return this;
    }
}
