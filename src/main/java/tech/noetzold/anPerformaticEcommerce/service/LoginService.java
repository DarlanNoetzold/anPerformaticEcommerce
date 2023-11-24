package tech.noetzold.anPerformaticEcommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import tech.noetzold.anPerformaticEcommerce.client.LoginClient;
import org.json.JSONException;
import org.json.JSONObject;

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

    public String getToken() throws JSONException {
        String requestBody = "username=" + username + "&password=" + password + "&grant_type=" + grantType;

        String response = tokenClient.getToken(requestBody);

        String accessToken = extractAccessToken(response);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        return headers.toString();
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
