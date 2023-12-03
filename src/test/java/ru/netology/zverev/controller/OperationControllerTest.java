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
import ru.netology.zverev.domain.operation.Operation;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class OperationControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    int randomServerPort;
    @Test
    public void GetRequestGetAllOperations() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/operations";
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("{0=[Operation(operationID=0, customerID=0, sum=100, currency=Euro, merchant=eBay)], 1=[Operation(operationID=1, customerID=1, sum=100, currency=Euro, merchant=Amazon)]}", result.getBody());
    }

    @Test
    public void GetRequestGetOperationsByCustomerID() throws URISyntaxException {
        String id = "0";
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/operations/" + id;
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();

        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("[{\"operationID\":0,\"customerID\":0,\"sum\":100,\"currency\":\"Euro\",\"merchant\":\"eBay\"}]", result.getBody());
    }

    @Test
    public void PostRequestDeleteOperation() throws URISyntaxException {
        String id = "0";
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/operations/" + id;
        URI uri = new URI(baseUrl);

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("[]", result.getBody());
    }

    @Test
    public void PostRequestSaveOperation() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/api/operations/";
        URI uri = new URI(baseUrl);
        Operation operation = new Operation(2, 3, 300, "Euro", "Etsy");

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Operation> request = new HttpEntity<>(operation, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        Assert.assertEquals(201, result.getStatusCodeValue());
        Assert.assertEquals("[{\"operationID\":2,\"customerID\":3,\"sum\":300,\"currency\":\"Euro\",\"merchant\":\"Etsy\"}]", result.getBody());
    }
}