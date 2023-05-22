package com.agba.wealth.wrapper.controller;

import com.agba.wealth.wrapper.entity.record.AccountPositionListDto;
import com.agba.wealth.wrapper.entity.record.AccountStatementDto;
import com.agba.wealth.wrapper.entity.response.AccountDetailsRes;
import com.agba.wealth.wrapper.entity.response.AccountPositionListRes;
import com.agba.wealth.wrapper.entity.response.AccountStatementRes;
import com.agba.wealth.wrapper.entity.response.SettleAccountListRes;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.Objects;

import static com.agba.wealth.wrapper.data.constant.Common.*;

@Slf4j
@RestController
@RequestMapping("/")
public class AccountEndpointController {

    private final Logger logger = LoggerFactory.getLogger(AccountEndpointController.class);

    private WebClient webClient;

    @Value("${app.apim.subscriptionKey}")
    private String apimSubscriptionKey;
    @Value("${app.apim.wmsUrlDomain}")
    private String webClientDomain;

    @Autowired
    MessageSource messageSource;

    @Bean
    private WebClient getAccountWebClient() {
        if (Objects.isNull(webClient)) {
            return WebClient.builder()
                    .baseUrl(webClientDomain)
                    .defaultHeaders(header ->
                            header.add("Ocp-Apim-Subscription-Key", apimSubscriptionKey)
                    )
                    .build();
        }
        return webClient;
    }

    @GetMapping(ACCESS_ACCOUNT_STATEMENT)
    private Mono<AccountStatementRes> accountStatement(@RequestHeader(name = "Accept-Language", required = false) Locale locale,
                                                       @RequestParam("clientId") String clientId,
                                                       @RequestParam("dateFrom") String dateFrom,
                                                       @RequestParam("dateTo") String dateTo,
                                                       @RequestParam(value = "productType", required = false) String productType) {
        try {
            if (Objects.isNull(locale))
                locale = Locale.ENGLISH;
            Locale finalLocale = locale;
            String wmsLang = locale.toString().replace("_", "-");
            logger.info("locale={}, clientId={}, dateFrom={}, dateTo={}, productType={}", locale.toString(), clientId, dateFrom, dateTo, productType);
            String qs = "clientId=" + clientId
                    + "&dateFrom=" + dateFrom
                    + "&dateTo=" + dateTo
                    + "&lang=" + wmsLang;
            if (productType != null && productType.trim().length() >= 0) {
                qs += "&productType=" + productType;
            }
            logger.info("qs={}", qs);
            Mono<AccountStatementRes> accountStatementResMono = getAccountWebClient()
                    .get()
                    .uri(PATH_ACCOUNT_STATEMENT + "?" + qs)
                    .exchangeToMono(response -> {
                        if (response.statusCode().equals(HttpStatus.OK)) {
                            Mono<AccountStatementDto> dto = response.bodyToMono(AccountStatementDto.class);
                            return dto.map(x -> x.toRes(finalLocale, messageSource));
                        } else {
                            return Mono.error(new Throwable("Error retrieving data from source"));
                        }
                    })
                    .retry(3);
            return accountStatementResMono;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(ACCESS_SETTLE_ACCOUNT_LIST)
    private Mono<SettleAccountListRes> settleAccountList(@RequestParam("accountId") String accountId) {
        logger.info("accountId={}", accountId);
        Mono<SettleAccountListRes> resMono = getAccountWebClient()
                .get()
                .uri(PATH_SETTLE_ACCOUNT_LIST +
                        "?accountId=" + accountId)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        Mono<SettleAccountListRes> dto = response.bodyToMono(SettleAccountListRes.class);
                        return dto.map(x -> x.toRes());
                    } else {
                        return Mono.error(new Throwable("Error retrieving data from source"));
                    }
                })
                .retry(3);
        return resMono;
    }

    @GetMapping(ACCESS_ACCOUNT_POSITION_LIST)
    private Mono<AccountPositionListRes> accountPositionList(@RequestHeader(name = "Accept-Language", required = false) Locale locale,
                                                             @RequestParam("accountId") String accountId,
                                                             @RequestParam(value = "asOfDate", required = false) Integer asOfDate,
                                                             @RequestParam(value = "ccy", required = false) String ccy,
                                                             @RequestParam(value = "productType", required = false) String productType) {
        logger.info("accountId={}, asOfDate={}, ccy={}, productType={}", accountId, asOfDate, ccy, productType);
        if (Objects.isNull(locale))
            locale = Locale.ENGLISH;
        String wmsLang = locale.toString().replace("_", "-");
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("?accountId=%s", accountId));
        sb.append(String.format("&lang=%s", wmsLang));
        if (asOfDate != null) {
            sb.append(String.format("&asOfDate=%s", asOfDate));
        }
        if (ccy != null) {
            sb.append(String.format("&ccy=%s", ccy));
        }
        if (productType != null) {
            sb.append(String.format("&productType=%s", productType));
        }
        Mono<AccountPositionListRes> resMono = getAccountWebClient()
                .get()
                .uri(PATH_ACCOUNT_POSITION_LIST + sb)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        Mono<AccountPositionListDto> dto = response.bodyToMono(AccountPositionListDto.class);
                        return dto.map(x -> x.toRes());
                    } else {
                        return Mono.error(new Throwable("Error retrieving data from source"));
                    }
                })
                .retry(3);
        return resMono;
    }

    @GetMapping(ACCESS_ACCOUNT_DETAILS)
    private Mono<AccountDetailsRes> accountDetail(@RequestParam("accountId") String accountId) {
        logger.info("accountId={}", accountId);
        Mono<AccountDetailsRes> resMono = getAccountWebClient()
                .get()
                .uri(PATH_ACCOUNT_DETAILS + String.format("?accountId=%s", accountId))
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(AccountDetailsRes.class);
                    } else {
                        return Mono.error(new Throwable("Error retrieving data from source"));
                    }
                })
                .retry(3);
        return resMono;
    }
}
