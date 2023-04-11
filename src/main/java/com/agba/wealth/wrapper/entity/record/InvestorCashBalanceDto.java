package com.agba.wealth.wrapper.entity.record;

import java.io.Serializable;

public class InvestorCashBalanceDto implements Serializable {
    private static final long serialVersionUID = -7829892508707262210L;
    private String currency;
    private String fundBalance;
    private String wmsBalance;
    private String consolidatedBalance;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFundBalance() {
        return fundBalance;
    }

    public void setFundBalance(String fundBalance) {
        this.fundBalance = fundBalance;
    }

    public String getConsolidatedBalance() {
        return consolidatedBalance;
    }

    public void setConsolidatedBalance(String consolidatedBalance) {
        this.consolidatedBalance = consolidatedBalance;
    }

    public String getWmsBalance() {
        return wmsBalance;
    }

    public void setWmsBalance(String wmsBalance) {
        this.wmsBalance = wmsBalance;
    }
}
