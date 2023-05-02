package com.agba.wealth.wrapper.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ElnListRes {
    String isTradeDate;
    List<StructuredProductRes> structuredProductDetailList;
    String returnCode;
    String returnMsg;
    String processTime;
}