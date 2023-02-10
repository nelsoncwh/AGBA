package com.agba.example.webflux.functional;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;


import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@Configuration
public class InvestorFunctionalConfig {
    Logger logger = LoggerFactory.getLogger(InvestorFunctionalConfig.class);
    final String uriPrefix = "/investor2";

    @Value("${app.apim.subscriptionKey}")
    private String apimSubscriptionKey;
    @Value("${app.apim.webClientDomain}")
    private String webClientDomain;

    @Bean
    RouterFunction<ServerResponse> getInvestor() {
//        RouterFunction<ServerResponse> resp = route(POST(uriPrefix + "/get-investor"), req -> req.body(toMono(InvestorRequest.class))
//                .doOnNext(r -> logger.info(">>>r: " + r.toString()))
//                .then(ok().build()));

//        RouterFunction<ServerResponse> resp = route(POST(uriPrefix + "/get-investor"), req -> ok().body(fromValue("123")));
        RouterFunction<ServerResponse> resp = route(POST(uriPrefix + "/get-investor"),
                req -> {
//                    req.body(toMono(InvestorRequest.class));
                    logger.info("req.headers(): {}", req.headers());
                    //temporaryRedirect will redirect the whole request with 403, adding header is not possible in this approach
                    return ServerResponse.temporaryRedirect(URI.create(webClientDomain + "/wms-rest/get-investor-by-account-number")).build();
                }
        );
        logger.info(">resp: {}", resp);
        return resp;
    }

}
