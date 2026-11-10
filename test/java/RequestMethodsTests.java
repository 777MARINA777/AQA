import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class RequestMethodsTests {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

    @Test
    @DisplayName("GET Request")
    public void getRequest() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when().get("/get")
                .then()
                .assertThat() //убедиться,что
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.host", equalTo("postman-echo.com"));
    }

    @Test
    @DisplayName("POST Raw Text")
    public void postRawTextTest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when().post("/post")
                .then()
                .assertThat() //убедиться,что
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("json", nullValue());
    }

    @Test
    @DisplayName("POST Form Data")
    public void postFormData() {
        given()
                .config(RestAssured.config().encoderConfig(
                        EncoderConfig.encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false)
                ))
                .contentType("application/x-www-form-urlencoded")
                .body("foo1=bar1&foo2=bar2")
                .when().post("/post")
                .then()
                .assertThat()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("json.foo1", equalTo("bar1"))
                .body("json.foo2", equalTo("bar2"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.host", equalTo("postman-echo.com"));
    }

    @Test
    @DisplayName("PUT Request")
    public void putRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when().put("/put")
                .then()
                .assertThat() //убедиться,что
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/put"));

    }

    @Test
    @DisplayName("PATCH Request")
    public void patchRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when().patch("/patch")
                .then()
                .assertThat() //убедиться,что
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/patch"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-length", equalTo("58"));
    }

    @Test
    @DisplayName("DELETE Request")
    public void deleteRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";
        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when().delete("/delete")
                .then()
                .assertThat() //убедиться,что
                .statusCode(200)
                .body("data", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/delete"))
                .body("headers.x-forwarded-proto", equalTo("https"))
                .body("headers.host", equalTo("postman-echo.com"))
                .body("headers.content-length", equalTo("58"));
    }
}

