package com.agba.wealth.wrapper.controller;

import com.agba.wealth.wrapper.entity.record.AccountStatementDto;
import com.agba.wealth.wrapper.entity.response.AccountStatementRes;
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
                                              @RequestParam("dateTo") String dateTo) {
        if (Objects.isNull(locale))
            locale = Locale.ENGLISH;
        Locale finalLocale = locale;
        String wmsLang = locale.toString().replace("_", "-");
        logger.debug("locale= {}", locale.toString());
        Mono<AccountStatementRes> accountStatementResMono = getAccountWebClient()
                .get()
                .uri(PATH_ACCOUNT_STATEMENT +
                        "?clientId=" + clientId +
                        "&dateFrom=" + dateFrom +
                        "&dateTo=" + dateTo +
                        "&lang=" + wmsLang)
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
    }
}
