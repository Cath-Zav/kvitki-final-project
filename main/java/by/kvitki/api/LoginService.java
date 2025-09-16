package by.kvitki.api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

    public class LoginService {
        private final String URL = "https://store.kvitki.by/web-api/customer/public/auth/login";
        private final String VALID_EMAIL = "cathzavizion@gmail.com";
        private final String VALID_PASSWORD = "itAcademy2025&";
        private final int VALID_CENTRE_ID = 1873;
        private final String MALFORMED_BODY = "{\"centreId\":0000,\"email\":\"test@mail.com\",\"password\":\"password\"}";
        private final String BODY_TEMPLATE= "{\"centreId\":%s,\"email\":\"%s\",\"password\":\"%s\"}";

        public String getValidEmail() {
            return VALID_EMAIL;
        }

        public String getValidPassword() {
            return VALID_PASSWORD;
        }

        public int getValidCentreId() {
            return VALID_CENTRE_ID;
        }

        private Response response;

        public String createBody(Integer centreId, String username, String password) {
            return String.format(BODY_TEMPLATE, centreId, username, password);
        }

        private Map<String, String> createHeaders() {
            Map<String, String> headers = new HashMap<>();
            headers.put("content-type", "application/json");
            return headers;
        }

        public void doRequest(Integer centreId, String email, String password) {
            response = given()
                    .headers(createHeaders())
                    .body(createBody(centreId, email, password))
                    .when().post(URL);
        }

        public void doRequest() {
            response = given()
                    .headers(createHeaders())
                    .body(MALFORMED_BODY)
                    .when().post(URL);
        }

        public int getStatusCode() {
            return response.getStatusCode();
        }

        public String getInvalidBodyMessage() {
            return response.getBody().path("message");
        }

        public String getValidBodyToken() {
            return response.getBody().path("accessToken");
        }
    }
