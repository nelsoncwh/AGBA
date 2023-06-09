package com.agba.wealth.wrapper.entity.record;

import com.agba.wealth.wrapper.entity.response.AccountPositionListRes;
import com.agba.wealth.wrapper.entity.response.HoldingListRes;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public record AccountPositionListDto(

        String returnCode,
        String returnMsg,
        String processTime,
        List<HoldingListDto> holdingList
) {
    public AccountPositionListRes toRes() {
        if (holdingList.isEmpty())
            return new AccountPositionListRes(returnCode, returnMsg, processTime, new ArrayList<>());
        ArrayList<HoldingListRes> listRes = new ArrayList<>();
        for (HoldingListDto dto : holdingList) {
            listRes.add(dto.toRes());
        }
        return new AccountPositionListRes(returnCode, returnMsg, processTime, listRes);
    }
}