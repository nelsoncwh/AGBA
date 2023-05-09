package com.agba.wealth.wrapper.entity.response;

import com.agba.wealth.wrapper.entity.record.TradablePrdListDto;

import java.util.List;

public record AccountDetailsRes(
        String returnCode,
        String returnMsg,
        String processTime,
        List<TradablePrdListDto> tradablePrdList
) {

}
