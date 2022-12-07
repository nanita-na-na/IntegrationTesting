package com.example.demoIntegrationTesting;

import com.example.demoIntegrationTesting.service.CatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RealCatServiceImplTest {

    @Autowired
    private CatService catService;

    @Test
    void testCatFact(){
        String catFact = catService.getCatFact("Cats have supersonic hearing");
        assertThat(catFact).isGreaterThan("");
    }
}
