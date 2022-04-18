package agh.sigmagit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Controller
@RestController
public class OAuthController {

    @GetMapping("/auth")
    public static void authorize(@RequestParam String code) throws IOException {
        String clientId = "d280af0562c1f960a43f";
        String clientSecret = "c52d14f4e3d754ade8baaf579706c19fa6ff7174";
        String redirectUri = "http://localhost:3000";

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        //map.put("Content-Type", "application/json");

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("client_id", clientId);
        req_payload.put("client_secret", clientSecret);
        req_payload.put("client_code", code);
        req_payload.put("redirect_uri", redirectUri);

        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
        String url = "https://github.com/login/oauth/access_token";

        ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);
        ServiceResponse entityResponse = (ServiceResponse) response.getBody();
        System.out.println(entityResponse.getData());

    }
}
