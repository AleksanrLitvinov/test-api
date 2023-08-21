package ru.maxima.rest_api.testapi.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class TestApi {

    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();

        String url = "https://reqres.in";
        String endPoint = "/api/users";
        String postEndPoint = "/api/users";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("name", "Aram");
        jsonData.put("job", "Java-developer");

        PersonApi person = new PersonApi();
        person.setName("Andrey");
        person.setJob("Kotlin-developer");

        HttpEntity<PersonApi> request = new HttpEntity<>(person, headers);

        PersonApi responsePerson = template.postForObject(url + postEndPoint, request, PersonApi.class);



//        String response = template.getForObject(url + endPoint, String.class);
        System.out.println(responsePerson);
    }













}
