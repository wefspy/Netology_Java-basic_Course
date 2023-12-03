package ru.netology.zverev.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ru.netology.zverev.domain.Customer;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    int randomServerPort;


    @Test
    public void GetRequestGetCustomers() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/customers";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("[{\"id\":0,\"name\":\"Spring\"},{\"id\":1,\"name\":\"Boot\"}]", result.getBody());
    }

    @Test
    public void GetRequestGetCustomer() throws URISyntaxException {
        String id = "0";
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/customers/" + id;
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("{\"id\":0,\"name\":\"Spring\"}", result.getBody());
    }

    @Test
    public void PostRequestCreateCustomer() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/customers/";
        URI uri = new URI(baseUrl);
        String name = "Adam";

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> request = new HttpEntity<>(name, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        Assert.assertEquals(201, result.getStatusCodeValue());
//        Assert.assertEquals("/api/customers/" + testCustomer.getId(), result.getHeaders().getLocation().getPath());
        Assert.assertEquals("{\"id\":3,\"name\":\"Adam\"}", result.getBody());
    }
}