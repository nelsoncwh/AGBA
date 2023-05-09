package com.agba.wealth.wrapper.entity.record;

import com.agba.wealth.wrapper.entity.response.UnderlyingListRes;

import java.io.Serializable;
import java.util.Locale;

public record UnderlyingListDto(
        String underlyingId,

        String underlyingName,
//        String underlyingNameZHTW,
//        String underlyingNameZHCN,
        String underlyingMarket,
        String underlyingType
) implements Serializable {

    public UnderlyingListRes toRes(Locale locale) {
//        return new UnderlyingListRes(underlyingId, getUnderlyingName(locale), underlyingMarket, underlyingType);
    	String underlyingCode = underlyingId;
    	if(underlyingId!=null){
    		underlyingCode = underlyingId.toUpperCase().replace(".HK", "").replace(".US", "");
    	}
        return new UnderlyingListRes(underlyingCode, underlyingName, underlyingMarket, underlyingType);
    }

//    public String getUnderlyingName(Locale locale) {
//        return ElnListDto.stringLocaleConvert(locale, underlyingNameEN, underlyingNameZHTW, underlyingNameZHCN);
//    }
}

