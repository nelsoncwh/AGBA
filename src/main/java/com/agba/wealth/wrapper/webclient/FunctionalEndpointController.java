package com.agba.wealth.wrapper.webclient;

import com.agba.wealth.wrapper.entity.record.AdditionalInfoDto;
import com.agba.wealth.wrapper.entity.record.InvestorDto;
import com.agba.wealth.wrapper.entity.record.InvestorJointDto;
import com.agba.wealth.wrapper.entity.request.InvestorCashBalanceReq;
import com.agba.wealth.wrapper.entity.request.InvestorReq;
import com.agba.wealth.wrapper.entity.response.AdditionalRes;
import com.agba.wealth.wrapper.entity.response.InvestorBalanceStatusRes;
import com.agba.wealth.wrapper.entity.response.InvestorRes;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.agba.wealth.wrapper.data.constant.CommonConstant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/investor")
public class FunctionalEndpointController {

    private final Logger logger = LoggerFactory.getLogger(FunctionalEndpointController.class);

    private WebClient webClient;

    private final ModelMapper modelMapper = new ModelMapper();

    @Value("${app.apim.subscriptionKey}")
    private String apimSubscriptionKey;
    @Value("${app.apim.webClientDomain}")
    private String webClientDomain;

    @Bean
    private WebClient getWebClient() {
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

    @PostMapping(ACCESS_GET_INVESTOR_BY_AC_NUMBER)
    private Mono<InvestorRes> getInvestorByAccountNumber(@RequestBody InvestorReq investorReq, @RequestHeader Map<String, String> headers) {
        logger.info("getInvestorByAccountNumber->{}", investorReq.accountNumber());
        Mono<InvestorRes> investorResponseMono = getWebClient().post().uri(PATH_WMS_GET_INVESTOR_BY_AC_NUMBER)
                .bodyValue(investorReq)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(InvestorRes.class);
                    } else {
                        return Mono.error(new Throwable("Failed to retrieve data from source"));
                    }
                })
                .retry(3)
//                .doOnNext(response -> {
//                    logger.info("Original Obj: {}", response);
//                    StringBuilder sb = new StringBuilder();
//                    headers.forEach((key, value) -> sb.append(String.format("\nHeader '%s' = %s", key, value)));
//                    logger.info(sb.toString());
//                })
                ;
        return investorResponseMono;
    }

    @PostMapping(ACCESS_GET_ADDITIONAL_INFO_BY_AC_NUMBER)
    private Mono<AdditionalRes> getAdditionalInfoByAccountNumber2(@RequestBody InvestorReq investorReq, @RequestHeader Map<String, String> headers) {
        logger.info("getInvestorByAccountNumber->{}", investorReq.accountNumber());
        Mono<InvestorRes> investorResponseMono = getWebClient().post().uri(PATH_WMS_GET_INVESTOR_BY_AC_NUMBER)
                .bodyValue(investorReq)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(InvestorRes.class);
                    } else {
                        return Mono.error(new Throwable("Failed to retrieve data from source"));
                    }
                })
                .retry(3);

        Mono<AdditionalRes> returnMono = investorResponseMono.map(res -> {
            ArrayList<AdditionalInfoDto> additionInfoList = new ArrayList<>();

            //Add Investor into list
            InvestorDto invDto = res.investor();
            AdditionalInfoDto infoDto = modelMapper.map(invDto, AdditionalInfoDto.class);
            additionInfoList.add(infoDto);

            //Add Joint Investors into list
            List<InvestorJointDto> invJointList = res.jointInvestor();
            for (InvestorJointDto jointDto : invJointList) {
                infoDto = modelMapper.map(jointDto, AdditionalInfoDto.class);
                additionInfoList.add(infoDto);
            }

            return new AdditionalRes(additionInfoList, res.investorBankAccount());
        });

        return returnMono;
    }

    @PostMapping(ACCESS_GET_CASH_BALANCE)
    private Mono<InvestorBalanceStatusRes[]> getCashBalanceByClientNumber(@RequestBody InvestorCashBalanceReq cashBalanceReq, @RequestHeader Map<String, String> headers) {
        Mono<InvestorBalanceStatusRes[]> responseMono = getWebClient().post().uri(PATH_CP_GET_CASH_BALANCE)
                .bodyValue(cashBalanceReq)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(InvestorBalanceStatusRes[].class);
                    } else {
                        return Mono.error(new Throwable("Failed to retrieve data from source"));
                    }
                })
                .retry(3);
        return responseMono;
    }

    //Example for get method
//    @GetMapping("/get-investor/{accountNumber}")
//    private Mono<InvestorRes> updateEmployee(@PathVariable String accountNumber) {
//        Mono<InvestorRes> investorMono = webClient.post().uri("/wms-rest/get-investor-by-account-number").bodyValue(new InvestorReq(accountNumber))
//                .header("Ocp-Apim-Subscription-Key", apimSubscriptionKey).exchangeToMono(response -> {
//                    if (response.statusCode().equals(HttpStatus.OK)) {
//                        return response.bodyToMono(InvestorRes.class);
//                    } else {
//                        return Mono.error(new Throwable("Error retrieving data from source"));
//                    }
//                }).doOnNext(response -> logger.debug(response.toString()));
//
//        investorMono.subscribe(investorRes -> logger.info("investorResponse: {}", investorRes));
//        return investorMono;
//    }
}
