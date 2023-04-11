package com.agba.wrapper.web;

import com.agba.wealth.wrapper.entity.request.InvestorReq;
import com.agba.wealth.wrapper.webclient.FunctionalEndpointController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = FunctionalEndpointController.class)
public class WrapperApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void EndpointWithRequestBody() {

        InvestorReq req = new InvestorReq("CAM00019172");

        WebTestClient.ResponseSpec response = webTestClient.post().uri("/investor/get-investor")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(req), InvestorReq.class)
                .exchange();

        response.expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.investor.investorId").isEqualTo("6065804")
                .jsonPath("$.investor.accountNumber").isEqualTo("CAM00019172")
                .jsonPath("$.investor.accountType").isEqualTo("individual");
    }
}

