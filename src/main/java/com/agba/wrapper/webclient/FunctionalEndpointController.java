package com.agba.wrapper.webclient;


import com.agba.wrapper.entity.record.AdditionalInfoDto;
import com.agba.wrapper.entity.request.InvestorCashBalanceReq;
import com.agba.wrapper.entity.request.InvestorReq;
import com.agba.wrapper.entity.response.AdditionalRes;
import com.agba.wrapper.entity.response.InvestorBalanceStatusRes;
import com.agba.wrapper.entity.response.InvestorRes;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

import static com.agba.wrapper.data.constant.CommonConstant.*;
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

    @PostMapping(ACCESS_GET_INVESTOR_BY_AC_NUMBER)
    private Mono<InvestorRes> getInvestorByAccountNumber(@RequestBody InvestorReq investorReq, @RequestHeader Map<String, String> headers) {
        Mono<InvestorRes> investorResponseMono = getClient().post().uri(PATH_WMS_GET_INVESTOR_BY_AC_NUMBER).bodyValue(investorReq).header("Ocp-Apim-Subscription-Key", apimSubscriptionKey).exchangeToMono(response -> {
            if (response.statusCode().equals(HttpStatus.OK)) {
                return response.bodyToMono(InvestorRes.class);
            } else {
                return Mono.error(new Throwable("Failed to retrieve data from "));
            }
        }).doOnNext(response -> {
            logger.info("Original Obj: {}", response);
//                            StringBuilder sb = new StringBuilder();
//                            headers.forEach((key, value) -> sb.append(String.format("\nHeader '%s' = %s", key, value)));
//                            logger.info(sb.toString());
        });

        Mono<InvestorRes> returnMono = investorResponseMono.map(res -> {
            //Change data in obj
            res.investor().setClientNumber(res.investor().getClientNumber() + "_modified");
            res.investor().setFirstName(res.investor().getFirstName() + "_modified");

//            logger.info("Modified Obj: {}", res);
            return res;
        });

        investorResponseMono.subscribe(investorRes -> {
//            investorResponse.investor().setClientNumber(investorResponse.investor().getClientNumber() + "_subModified");
//            investorResponse.investor().setFirstName(investorResponse.investor().getFirstName() + "_subModified");
            logger.info(investorRes.toString());
        });
//        investorResponseMono.subscribe(investorResponse -> logger.info("investorResponse: {}", investorResponse));
        return returnMono/*.doOnNext(s -> logger.info("returnMono: " + s.toString()))*/;
    }

    @PostMapping(ACCESS_GET_ADDITIONAL_INFO_BY_AC_NUMBER)
    private Mono<AdditionalRes> getAdditionalInfoByAccountNumber(@RequestBody InvestorReq investorReq, @RequestHeader Map<String, String> headers) {
        Mono<AdditionalRes> responseMono = getClient().post().uri(PATH_WMS_GET_ADDITIONAL_INFO_BY_AC_NUMBER).bodyValue(investorReq).header("Ocp-Apim-Subscription-Key", apimSubscriptionKey).exchangeToMono(response -> {
            if (response.statusCode().equals(HttpStatus.OK)) {
                return response.bodyToMono(AdditionalRes.class);
            } else {
                return Mono.error(new Throwable("Error retrieving data from source"));
            }
        }).doOnNext(response -> {
            logger.info("Original Obj: {}", response);
        });

        Mono<AdditionalRes> returnMono = responseMono.map(res -> {
            //Change data in obj
            if (res.additionalInfo().size() > 0) {
                AdditionalInfoDto infoDto = res.additionalInfo().get(0);
                infoDto.setJobPosition(infoDto.getJobPosition() + "_modified");
            }
//            logger.info("Modified Obj: {}", res);
            return res;
        });

        responseMono.subscribe(investorRes -> {
            logger.info(investorRes.toString());
        });
        return returnMono;
    }

    @PostMapping(ACCESS_GET_CASH_BALANCE)
    private Mono<InvestorBalanceStatusRes[]> getCashBalanceByClientNumber(@RequestBody InvestorCashBalanceReq cashBalanceReq, @RequestHeader Map<String, String> headers) {
        Mono<InvestorBalanceStatusRes[]> responseMono = getClient().post().uri(PATH_CP_GET_CASH_BALANCE).bodyValue(cashBalanceReq).header("Ocp-Apim-Subscription-Key", apimSubscriptionKey).exchangeToMono(response -> {
            if (response.statusCode().equals(HttpStatus.OK)) {
                return response.bodyToMono(InvestorBalanceStatusRes[].class);
            } else {
                return Mono.error(new Throwable("Error retrieving data from source"));
            }
        }).doOnNext(response -> {
            logger.info("Original Obj: {}", response);
        });

        Mono<InvestorBalanceStatusRes[]> returnMono = responseMono.map(res -> {
            //Change data in obj
//            logger.info("Modified Obj: {}", res);
            return res;
        });

        responseMono.subscribe(investorRes -> {
            logger.info(investorRes.toString());
        });
        return returnMono;
    }

    //Example for get method
    @GetMapping("/get-investor/{accountNumber}")
    private Mono<InvestorRes> updateEmployee(@PathVariable String accountNumber) {
        Mono<InvestorRes> investorMono = client.post().uri("/wms-rest/get-investor-by-account-number").bodyValue(new InvestorReq(accountNumber)).header("Ocp-Apim-Subscription-Key", apimSubscriptionKey).exchangeToMono(response -> {
            if (response.statusCode().equals(HttpStatus.OK)) {
                return response.bodyToMono(InvestorRes.class);
            } else {
                return Mono.error(new Throwable("Error retrieving data from source"));
            }
        }).doOnNext(response -> logger.debug(response.toString()));

        investorMono.subscribe(investorRes -> logger.info("investorResponse: {}", investorRes));
        return investorMono;
    }
}
