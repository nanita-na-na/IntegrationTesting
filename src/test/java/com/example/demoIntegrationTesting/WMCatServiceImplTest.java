package com.example.demoIntegrationTesting;

import com.example.demoIntegrationTesting.service.CatResponse;
import com.example.demoIntegrationTesting.service.CatService;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.jsonResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@WireMockTest(httpPort = 8080)
@TestPropertySource(properties = "cat.url = http://localhost:8080/catfact/")
public class WMCatServiceImplTest {

    @Autowired
    private CatService catService;

    @Test
    void testCatFact(WireMockRuntimeInfo wireMockRuntimeInfo) {
        CatResponse response = new CatResponse();
        response.setFact("Cats have supersonic hearing");
        response.setLength(28);
        stubFor(get("/catfact/").willReturn(jsonResponse(response, HttpStatus.OK.value())));
        String catFact = catService.getCatFact("Cats have supersonic hearing");
        assertThat(catFact).isGreaterThan("");
    }
}
