package com.candywrapper.candywrapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
public class CandywrapperRequestTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getProductsTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/products/",
                String.class)).contains("Products");
    }

    @Test
    public void getSignupTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/accounts/signup/", String.class))
                .contains("Role");
    }    
}