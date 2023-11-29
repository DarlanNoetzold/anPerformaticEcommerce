package tech.noetzold.anPerformaticEcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.json.JSONException;
import org.json.JSONObject;
import tech.noetzold.anPerformaticEcommerce.client.LoginClient;

import java.util.Base64;

@Service
public class LoginService {

    @Autowired
    private LoginClient tokenClient;

    @Value("${token.username}")
    private String username;

    @Value("${token.password}")
    private String password;

    @Value("${token.grant-type}")
    private String grantType;

    @Value("${token.client.username}")
    private String usernameAPI;

    @Value("${token.client.password}")
    private String passwordAPI;

    public String getToken() throws JSONException {
        String requestBody = "username=" + username + "&password=" + password + "&grant_type=" + grantType;

        String credentials = Base64.getEncoder().encodeToString((usernameAPI + ":" + passwordAPI).getBytes());

        String response = tokenClient.getToken(requestBody, "Basic " + credentials);

        String accessToken = extractAccessToken(response);

        return "Bearer " + accessToken;
    }

    private String extractAccessToken(String response) throws JSONException {
        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString("access_token");
        } catch (JSONException e) {
            throw e;
        }
    }
}
