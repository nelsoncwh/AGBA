package com.agba.example.webflux.controller;

import com.agba.example.webflux.entity.request.InvestorRequest;
import com.agba.example.webflux.entity.response.InvestorResponse;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

import static java.util.Objects.nonNull;

@Slf4j
@RestController
@RequestMapping("/investor")
public class FunctionalEndpointController {

    private final Logger logger = LoggerFactory.getLogger(FunctionalEndpointController.class);
    private WebClient client;

    @Value("${app.apim.subscriptionKey}")
    private String apimSubscriptionKey;
    @Value("${app.apim.webClientDomain}")
    private String webClientDomain;

    @Bean
    private WebClient getClient() {
        return nonNull(client) ? client : WebClient.create(webClientDomain);
    }

    @PostMapping("/get-investor")
    private Mono<InvestorResponse> getInvestor(@RequestBody InvestorRequest investorRequest, @RequestHeader Map<String, String> headers) {
        Mono<InvestorResponse> investorResponseMono = getClient().post()
                .uri("/wms-rest/get-investor-by-account-number")
                .bodyValue(new InvestorRequest(investorRequest.accountNumber()))
                .header("Ocp-Apim-Subscription-Key", apimSubscriptionKey)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(InvestorResponse.class);
                    } else {
                        return response.createError();
                    }
                }).doOnNext(response -> {
//                            logger.info("response: " + response);
//                            headers.forEach((key, value) -> logger.info(String.format("Header '%s' = %s", key, value)));
                        }
                );
        Mono<InvestorResponse> returnMono = investorResponseMono.map(res -> {
            //Change data in obj
            res.investor().setClientNumber("newNum");
            res.investor().setLastName("Chan");
            //Create new Response with modified obj
            InvestorResponse _invResponse = new InvestorResponse(res.investor());
            return _invResponse;
        });

        investorResponseMono.subscribe(investorResponse -> {
//            investorResponse.investor().setClientNumber("NM");
//            investorResponse.investor().setFirstName("FIRST_NAME");
            logger.info(investorResponse.toString());
        });
//        investorResponseMono.subscribe(investorResponse -> logger.info("investorResponse: {}", investorResponse));
        return returnMono.doOnNext(s -> logger.info("returnMono: " + s.toString()));
    }

    @GetMapping("/get-investor/{accountNumber}")
    private Mono<InvestorResponse> updateEmployee(@PathVariable String accountNumber) {
        Mono<InvestorResponse> investorMono = client.post()
                .uri("/wms-rest/get-investor-by-account-number")
                .bodyValue(new InvestorRequest(accountNumber))
                .header("Ocp-Apim-Subscription-Key", apimSubscriptionKey)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(InvestorResponse.class);
                    } else {
                        return response.createError();
                    }
                }).doOnNext(response -> logger.debug(response.toString()));

        investorMono.subscribe(investorResponse -> logger.info("investorResponse: {}", investorResponse));
        return investorMono;
    }
}
