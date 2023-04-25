package com.agba.wealth.wrapper.entity.record;


import com.agba.wealth.wrapper.entity.response.AccountStatementRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;


public record AccountStatementDto(
        List<AccountStatementDocumentDto> documentList,
        String returnCode,
        String returnMsg,
        String processTime
) {

    static MessageSource messageSource;
    static Locale locale;
    static Logger logger = LoggerFactory.getLogger(AccountStatementDto.class);

    public AccountStatementRes toRes(Locale locale, MessageSource msgSource) {
        AccountStatementDto.messageSource = msgSource;
        return new AccountStatementRes(documentList, returnCode, returnMsg, processTime);
    }
}