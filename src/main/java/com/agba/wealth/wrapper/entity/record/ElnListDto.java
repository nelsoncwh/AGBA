package com.agba.wealth.wrapper.entity.record;


import com.agba.wealth.wrapper.data.constant.Common;
import com.agba.wealth.wrapper.entity.response.ElnListRes;
import com.agba.wealth.wrapper.entity.response.StructuredProductRes;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


public record ElnListDto(
        String isTradeDate,
        List<StructuredProductDto> structuredProduct,
        String returnCode,
        String returnMsg,
        String processTime
) {

    static MessageSource messageSource;
    static Locale locale;
    static Logger logger = LoggerFactory.getLogger(ElnListDto.class);

    public ElnListRes toRes(Locale locale, MessageSource msgSource) {
        ElnListDto.messageSource = msgSource;
        return new ElnListRes(isTradeDate, convert2StructuredProductDetailListRes(locale), returnCode, returnMsg, processTime);
    }

    public List<StructuredProductRes> convert2StructuredProductDetailListRes(Locale locale) {
        ElnListDto.locale = locale;
        return (Objects.isNull(structuredProduct)) ?
                new ArrayList<>() :
                structuredProduct.stream()
                        .map(dto ->
                        {
                            ModelMapper mapper = new ModelMapper();
                            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                            StructuredProductRes res = mapper.map(dto, StructuredProductRes.class);
                            //Callable Frequency
                            if (!Objects.isNull(res.getCallableFreq()) && res.getCallable() && !Objects.isNull(res.getAutocallLevel())) {
                            	DecimalFormat df = new DecimalFormat("0.##");
                            	String autoCallFreq = df.format(res.getAutocallLevel()) + "% " + res.getCallableFreq();
                                res.setAutocallLevelFreq(autoCallFreq);
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
                case Common.ZH_HK -> out = stringDefault(_langHK, _defaultEN);
                case Common.ZH_CN -> out = stringDefault(_langCN, _defaultEN);
                default -> out = trim(_defaultEN);
            }
            return out;
        }
        return null;
    }

    private String i18n(String code) {
        try {
            return StringUtils.defaultIfBlank(messageSource.getMessage(code, null, locale), "");
        } catch (NoSuchMessageException e) {
            logger.error(e.getMessage() + ", " + Arrays.toString(e.getStackTrace()));
        }
        return Common.EMPTY;
    }

    private static String stringDefault(String _input, String _default) {
        return StringUtils.defaultString(trim(_input), trim(_default));
    }

    private static String trim(String s) {
        return StringUtils.trim(s);
    }
}