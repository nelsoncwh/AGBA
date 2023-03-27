package com.agba.wrapper.webclient;

import com.agba.wrapper.entity.record.AdditionalInfoDto;
import com.agba.wrapper.entity.record.InvestorDto;
import com.agba.wrapper.entity.record.InvestorJointDto;
import com.agba.wrapper.entity.request.InvestorCashBalanceReq;
import com.agba.wrapper.entity.request.InvestorReq;
import com.agba.wrapper.entity.response.AdditionalRes;
import com.agba.wrapper.entity.response.InvestorBalanceStatusRes;
import com.agba.wrapper.entity.response.InvestorRes;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.agba.wrapper.data.constant.CommonConstant.*;
import static java.util.Objects.nonNull;

@Slf4j
@RestController
@RequestMapping("/investor")
public class FunctionalEndpointController {

    private final Logger logger = LoggerFactory.getLogger(FunctionalEndpointController.class);
    private WebClient client;

    private final ModelMapper modelMapper = new ModelMapper();

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
        logger.info("getInvestorByAccountNumber->{}", investorReq.accountNumber());
        Mono<InvestorRes> investorResponseMono = getClient().post().uri(PATH_WMS_GET_INVESTOR_BY_AC_NUMBER).bodyValue(investorReq).header("Ocp-Apim-Subscription-Key", apimSubscriptionKey).exchangeToMono(response -> {
            if (response.statusCode().equals(HttpStatus.OK)) {
                return response.bodyToMono(InvestorRes.class);
            } else {
                return Mono.error(new Throwable("Failed to retrieve data from source"));
            }
        }).doOnNext(response -> {
//            logger.info("Original Obj: {}", response);
//                            StringBuilder sb = new StringBuilder();
//                            headers.forEach((key, value) -> sb.append(String.format("\nHeader '%s' = %s", key, value)));
//                            logger.info(sb.toString());
        });
        return investorResponseMono;
    }

    @PostMapping(ACCESS_GET_ADDITIONAL_INFO_BY_AC_NUMBER)
    private Mono<AdditionalRes> getAdditionalInfoByAccountNumber2(@RequestBody InvestorReq investorReq, @RequestHeader Map<String, String> headers) {
        logger.info("getInvestorByAccountNumber->{}", investorReq.accountNumber());
        Mono<InvestorRes> investorResponseMono = getClient().post().uri(PATH_WMS_GET_INVESTOR_BY_AC_NUMBER).bodyValue(investorReq)
                .header("Ocp-Apim-Subscription-Key", apimSubscriptionKey).exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(InvestorRes.class);
                    } else {
                        return Mono.error(new Throwable("Failed to retrieve data from source"));
                    }
                });

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
        Mono<InvestorBalanceStatusRes[]> responseMono = getClient().post().uri(PATH_CP_GET_CASH_BALANCE).bodyValue(cashBalanceReq)
                .header("Ocp-Apim-Subscription-Key", apimSubscriptionKey).exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(InvestorBalanceStatusRes[].class);
                    } else {
                        return Mono.error(new Throwable("Failed to retrieve data from source"));
                    }
                });
        return responseMono;
    }

    //Example for get method
    @GetMapping("/get-investor/{accountNumber}")
    private Mono<InvestorRes> updateEmployee(@PathVariable String accountNumber) {
        Mono<InvestorRes> investorMono = client.post().uri("/wms-rest/get-investor-by-account-number").bodyValue(new InvestorReq(accountNumber))
                .header("Ocp-Apim-Subscription-Key", apimSubscriptionKey).exchangeToMono(response -> {
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
