package com.agba.wealth.wrapper.controller;

import com.agba.wealth.wrapper.entity.record.ElnListDto;
import com.agba.wealth.wrapper.entity.response.ElnListRes;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Locale;
import java.util.Objects;

import static com.agba.wealth.wrapper.data.constant.Common.ACCESS_ELN_LIST;
import static com.agba.wealth.wrapper.data.constant.Common.PATH_ELN_LIST;

@Slf4j
@RestController
@RequestMapping("/")
public class ElnListEndpointController {

    private final Logger logger = LoggerFactory.getLogger(ElnListEndpointController.class);

    private WebClient webClient;

    @Value("${app.apim.subscriptionKey}")
    private String apimSubscriptionKey;
    @Value("${app.apim.wmsUrlDomain}")
    private String webClientDomain;

    @Autowired
    MessageSource messageSource;

    @Bean
    private WebClient getElnListWebClient() {
        if (Objects.isNull(webClient)) {
            return WebClient.builder()
                    .codecs(codecs -> codecs
                            .defaultCodecs()
                            .maxInMemorySize(500 * 1024))
                    .baseUrl(webClientDomain)
                    .defaultHeaders(header ->
                            header.add("Ocp-Apim-Subscription-Key", apimSubscriptionKey)
                    )
                    .build();
        }
        return webClient;
    }

    @GetMapping(ACCESS_ELN_LIST)
    public Mono<ElnListRes> elnList(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        if (Objects.isNull(locale))
            locale = Locale.ENGLISH;
        Locale finalLocale = locale;
        String wmsLang = locale.toString().replace("_", "-");
        logger.info("locale= {}", locale);
        Mono<ElnListRes> elnListMono = getElnListWebClient()
                .get()
                .uri(PATH_ELN_LIST + "&lang=" + wmsLang)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        Mono<ElnListDto> dto = response.bodyToMono(ElnListDto.class);
                        return dto.map(x -> x.toRes(finalLocale, messageSource));
                    } else {
                        return Mono.error(new Throwable("Error retrieving data from source"));
                    }
                })
                .retry(3);

        elnListMono.doOnSuccess(data -> logger.info("elnList={}", data))
                .subscribe();

        return elnListMono;
    }
}
