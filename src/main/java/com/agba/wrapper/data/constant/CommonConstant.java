package com.agba.wrapper.data.constant;

public class CommonConstant {
  // Path Variable
  public static final String PATH_VARIABLE_ID = "id";
  public static final String PATH_VARIABLE_USERNAME = "username";
  public static final String PATH_VARIABLE_AGE = "age";

  // Path
  public static final String ACCESS_GET_INVESTOR_BY_AC_NUMBER = "/get-investor";
//  public static final String ACCESS_GET_ADDITIONAL_INFO_BY_AC_NUMBER_RAW = "/get-additional-info-raw";
  public static final String ACCESS_GET_ADDITIONAL_INFO_BY_AC_NUMBER = "/get-additional-info";
  public static final String ACCESS_GET_CASH_BALANCE = "/cashBalance";

  //WMS PATH
  public static final String PATH_WMS_GET_ADDITIONAL_INFO_BY_AC_NUMBER = "/wms-rest/get-additional-info-by-account-number";
  public static final String PATH_WMS_GET_INVESTOR_BY_AC_NUMBER = "/wms-rest/get-investor-by-account-number";
  public static final String PATH_CP_GET_CASH_BALANCE = "/cp-rest/investor/cashBalance";
}
