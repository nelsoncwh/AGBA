package com.agba.example.webflux;

import com.agba.example.webflux.entity.request.InvestorRequest;
import com.agba.example.webflux.entity.response.InvestorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class InvestorWebClient {

    WebClient client = WebClient.create("https://api.3601.com.hk/");
    Logger LOGGER = LoggerFactory.getLogger(InvestorWebClient.class);

    public void consume() {
        Mono<InvestorResponse> investorMono = client.post()
                .uri("/wms-rest/get-investor-by-account-number")
                .bodyValue(new InvestorRequest("CAM00019172"))
                .header("Ocp-Apim-Subscription-Key", "cd563a7eaace409a8cbce2a75aaa336b")
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(InvestorResponse.class);
                    } else {
                        return response.createError();
                    }
                });
        investorMono.subscribe(investorResponse -> LOGGER.info("investorResponse: {}", investorResponse));
    }
}

