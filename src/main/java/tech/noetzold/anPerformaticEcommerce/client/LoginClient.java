package tech.noetzold.anPerformaticEcommerce.client;

import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(url= "http://localhost:8180" , name = "login")
public interface LoginClient {

    @RequestMapping(value = "/realms/quarkus1/protocol/openid-connect/token", method = RequestMethod.POST,
            consumes = "application/x-www-form-urlencoded")
    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    String getToken(@RequestBody String requestBody, @RequestHeader("Authorization") String authorizationHeader);}