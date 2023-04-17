package com.agba.wealth.wrapper.entity.record;


import com.agba.wealth.wrapper.data.constant.CommonConstant;
import com.agba.wealth.wrapper.entity.response.ElnListRes;
import com.agba.wealth.wrapper.entity.response.StructuredProductDetailRes;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;


public record ElnListDto(
        String isTradeDate,
        List<StructuredProductDetailDto> structuredProductDetailList,
        Integer returnCode,
        String returnMsg,
        String processTime
) {

    static MessageSource messgeSource;
    static Locale locale;
    static Logger logger = LoggerFactory.getLogger(ElnListDto.class);

    public ElnListRes toRes(Locale locale, MessageSource msgSource) {
        ElnListDto.messgeSource = msgSource;
        return new ElnListRes(isTradeDate, convert2StructuredProductDetailListRes(locale), returnCode, returnMsg, processTime);
    }

    public List<StructuredProductDetailRes> convert2StructuredProductDetailListRes(Locale locale) {
        ElnListDto.locale = locale;
        return structuredProductDetailList.stream()
                .map(dto ->
                {
                    ModelMapper mapper = new ModelMapper();
                    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                    StructuredProductDetailRes res = mapper.map(dto, StructuredProductDetailRes.class);

                    //ProductSubTypeName
                    res.setProductSubTypeName(dto.getProductSubType());
                    //Name
                    res.setName(stringLocaleConvert(locale, dto.getNameEN(), dto.getNameZHTW(), dto.getNameZHCN()));
                    //RiskLevel
                    if (!Objects.isNull(dto.getRiskLevel())) {
                        String s;
                        switch (dto.getRiskLevel()) {
                            case 1 -> s = i18n("structuredProductDetailList.riskLevelDesc.low");
                            case 2 -> s = i18n("structuredProductDetailList.riskLevelDesc.low2medium");
                            case 3 -> s = i18n("structuredProductDetailList.riskLevelDesc.medium");
                            case 4 -> s = i18n("structuredProductDetailList.riskLevelDesc.medium2high");
                            case 5 -> s = i18n("structuredProductDetailList.riskLevelDesc.high");
                            default -> s = CommonConstant.NA;
                        }
                        res.setRiskLevelDesc(s);
                    }
                    //Issuer Name
                    res.setIssuer(stringLocaleConvert(locale, dto.getIssuerEN(), dto.getIssuerZHTW(), dto.getIssuerZHCN()));
                    //Tenor Period
                    if (!Objects.isNull(dto.getTenorFreq())) {

                        //TODO TEMP FIX
                        if (dto.getTenorFreq().contains(CommonConstant.DAY)) {
                            dto.setTenorFreq(CommonConstant.DAY);
                        } else if (dto.getTenorFreq().contains(CommonConstant.MONTH)) {
                            dto.setTenorFreq(CommonConstant.MONTH);
                        }

                        //TODO END OF TEMP FIX

                        String s;
                        switch (dto.getTenorFreq()) {
                            case CommonConstant.DAY -> s = i18n("structuredProductDetailList.days");
                            case CommonConstant.MONTH -> s = i18n("structuredProductDetailList.months");
                            default -> s = CommonConstant.EMPTY;
                        }
                        if (res.getTenor() != null)
                            res.setTenorPeriod(dto.getTenor() + s);
                        else
                            res.setTenorPeriod("?" + s);
                    }
                    //Principal Guaranteed
                    res.setPrincipalGuaranteed(dto.getPrincipalGuaranteed() ?
                            i18n("structuredProductDetailList.yes") :
                            i18n("structuredProductDetailList.no")
                    );
                    //Callable Frequency
                    if (!Objects.isNull(dto.getCallableFreq())) {
                        String s;
                        switch (dto.getCallableFreq()) {
                            case CommonConstant.DAY -> s = i18n("structuredProductDetailList.daily");
                            case CommonConstant.MONTH -> s = i18n("structuredProductDetailList.monthly");
                            default -> s = CommonConstant.EMPTY;
                        }
                        res.setCallableFreq(s);
                    }
                    //Airbag Level Frequency
                    if (!Objects.isNull(dto.getAirBagLevelFreq())) {
                        String s;
                        switch (dto.getAirBagLevelFreq()) {
                            case CommonConstant.DAY -> s = i18n("structuredProductDetailList.daily");
                            case CommonConstant.MONTH -> s = i18n("structuredProductDetailList.monthly");
                            case "At-Expiry" -> s = i18n("structuredProductDetailList.atExpiry");
                            default -> s = CommonConstant.EMPTY;
                        }
                        res.setAirBagLevelFreq(s);
                    }
                    //Accrued Coupon
                    if (!Objects.isNull(dto.getAccruedCoupon())) {
                        res.setAccruedCoupon(dto.getAccruedCoupon() ?
                                i18n("structuredProductDetailList.yes") :
                                i18n("structuredProductDetailList.no")
                        );
                    }
                    //Underlying Name (Stock Name. Max 60 characters)
                    res.setUnderlyingList(
                            dto.getUnderlyingList().stream().map(
                                    listDto -> listDto.toRes(locale)
                            ).toList()
                    );

                    return res;
                })
                .collect(Collectors.toList());
    }

    public static String stringLocaleConvert(Locale locale, String _defaultEN, String _langHK, String _langCN) {
        if (!Objects.isNull(_defaultEN)) {
            String out;
            switch (locale.toString()) {
                case CommonConstant.ZH_HK -> out = stringDefault(_langHK, _defaultEN);
                case CommonConstant.ZH_CN -> out = stringDefault(_langCN, _defaultEN);
                default -> out = trim(_defaultEN);
            }
            return out;
        }
        return null;
    }

    private String i18n(String code) {
        try {
            return StringUtils.defaultIfBlank(messgeSource.getMessage(code, null, locale), "");
        } catch (NoSuchMessageException e) {
            logger.error(e.getMessage() + ", " + Arrays.toString(e.getStackTrace()));
        }
        return CommonConstant.EMPTY;
    }

    private static String stringDefault(String _input, String _default) {
        return StringUtils.defaultString(trim(_input), trim(_default));
    }

    private static String trim(String s) {
        return StringUtils.trim(s);
    }
}