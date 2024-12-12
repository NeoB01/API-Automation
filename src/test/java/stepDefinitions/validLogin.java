package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class validLogin {

    private static final String BASE_URL = "https://automationexercise.com/api";
    private static final String ENDPOINT = "/verifyLogin";
    private static final String CONTENT_TYPE = "application/json";
    private Response response;

    @When("the user enters valid credentials")
    public void theUserEntersValidCredentials() {
        String requestBody = "email=neo.banda@example.com&password=Password123";

        System.out.println("Request Body: " + requestBody);

        response = RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(requestBody)
                .log().all()
                .when()
                .post(BASE_URL + ENDPOINT)
                .then()
                .log().all()
                .extract()
                .response();

        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Headers: " + response.getHeaders().toString());
    }


    @Then("the user receives a response indicating that the login is successful")
    public void theUserReceivesAResponseIndicatingThatTheLoginIsSuccessful() {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("responseCode");

        Assert.assertEquals(statusCode, 200, "Unexpected status code: " + statusCode);
        Assert.assertEquals(responseCode, 200, "Unexpected response code: " + responseCode);
        Assert.assertTrue(responseBody.contains("User exists!"), "Response does not indicate a successful login.");
    }
}
