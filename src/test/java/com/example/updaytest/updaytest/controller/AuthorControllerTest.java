package com.example.updaytest.updaytest.controller;

import com.example.updaytest.updaytest.model.Author;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testCreateAuthorSuccess() {
        Author author = new Author();
        author.setName("john");

        HttpEntity<Author> entity = new HttpEntity<>(author, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/authors"), HttpMethod.POST, entity, String.class);

        String expected = "{\"id\":1,\"name\":\"john\"}";

        //Verify request succeed
        Assert.assertEquals(201, response.getStatusCodeValue());
        Assert.assertEquals(expected, response.getBody());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
