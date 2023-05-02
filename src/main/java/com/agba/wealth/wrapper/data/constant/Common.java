package com.agba.wealth.wrapper.data.constant;

public class Common {
    //i18n
    public static final String EN = "en";
    public static final String ZH_HK = "zh_HK";
    public static final String ZH_CN = "zh_CN";

    //Common Wordings
    public static final String EMPTY = "";
    public static final String NA = "N/A";
    public static final String DAY = "D";
    public static final String MONTH = "M";
    public static final String DAILY = "Daily";
    public static final String MONTHLY = "Monthly";

    //Access Path
    public static final String ACCESS_GET_INVESTOR_BY_AC_NUMBER = "/get-investor";
    public static final String ACCESS_GET_ADDITIONAL_INFO_BY_AC_NUMBER = "/get-additional-info";
    public static final String ACCESS_GET_CASH_BALANCE = "/cashBalance";
    public static final String ACCESS_ELN_LIST = "/eln/list";
    public static final String ACCESS_ACCOUNT_STATEMENT = "/statement/list";

    //WMS PATH
    public static final String PATH_WMS_GET_ADDITIONAL_INFO_BY_AC_NUMBER = "/wms-rest/get-additional-info-by-account-number";
    public static final String PATH_WMS_GET_INVESTOR_BY_AC_NUMBER = "/wms-rest/get-investor-by-account-number";
    public static final String PATH_CP_GET_CASH_BALANCE = "/cp-rest/investor/cashBalance";
    public static final String PATH_ELN_LIST = "/eln/list?needDetail=1";
    public static final String PATH_ACCOUNT_STATEMENT = "/account/statement/list";
}
