package by.kvitki.api;

import by.kvitki.utils.LoginUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    @Test
    @DisplayName("Invalid email and password")
    public void test1() {
        LoginService service = new LoginService();
        service.doRequest(service.getValidCentreId(), LoginUtils.getRandomEmail(), LoginUtils.getRandomPassword());
        System.out.println(service.getInvalidBodyMessage());
        assertAll(
                () -> assertEquals(401, service.getStatusCode()),
                () -> assertEquals("web_login_email_or_password_invalid", service.getInvalidBodyMessage())
        );
    }

    @Test
    @DisplayName("Valid email and password")
    public void test2() {
        LoginService service = new LoginService();
        service.doRequest(service.getValidCentreId(), service.getValidEmail(), service.getValidPassword());
        System.out.println(service.getInvalidBodyMessage());
        assertAll(
                () -> assertEquals(200, service.getStatusCode()),
                () -> assertNotNull(service.getValidBodyToken())
        );
    }

    @ParameterizedTest
    @DisplayName("Empty email and filled password")
    @EmptySource
    public void test3(String email) {
        LoginService service = new LoginService();
        service.doRequest(service.getValidCentreId(), email, LoginUtils.getRandomPassword());
        System.out.println(service.getInvalidBodyMessage());
        assertAll(
                () -> assertEquals(400, service.getStatusCode()),
                () -> assertEquals("web_login_email_missing", service.getInvalidBodyMessage())
        );
    }

    @ParameterizedTest
    @DisplayName("Filled email and empty password")
    @EmptySource
    public void test4(String emptyPassword) {
        LoginService service = new LoginService();
        service.doRequest(service.getValidCentreId(), LoginUtils.getRandomEmail(), emptyPassword);
        System.out.println(service.getInvalidBodyMessage());
        assertAll(
                () -> assertEquals(400, service.getStatusCode()),
                () -> assertEquals("web_login_password_missing", service.getInvalidBodyMessage())
        );
    }

    @Test
    @DisplayName("Invalid centreId field")
    public void test5() {
        LoginService service = new LoginService();
        service.doRequest(LoginUtils.createRandomCentreId(), LoginUtils.getRandomEmail(), LoginUtils.getRandomPassword());
        assertAll(
                () -> assertEquals(400, service.getStatusCode()),
                () -> assertEquals("web_centre_id_invalid", service.getInvalidBodyMessage())
        );
    }

    @ParameterizedTest
    @DisplayName("Empty centreId field")
    @NullSource
    public void test6(Integer emptyId) {
        LoginService service = new LoginService();
        service.doRequest(emptyId, LoginUtils.getRandomEmail(), LoginUtils.getRandomPassword());
        assertAll(
                () -> assertEquals(400, service.getStatusCode()),
                () -> assertEquals("web_centre_id_invalid", service.getInvalidBodyMessage())
        );
    }

    @Test
    @DisplayName("Malformed json")
    public void test7() {
        LoginService service = new LoginService();
        service.doRequest();
        assertAll(
                () -> assertEquals(400, service.getStatusCode()),
                () -> assertEquals("api_request_malformed_body", service.getInvalidBodyMessage())
        );
    }
}
