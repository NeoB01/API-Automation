package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class invalidLogin {

    private String baseUrl;
    private Response response;

    public invalidLogin() {
        this.baseUrl = "https://automationexercise.com/api";
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        // Base URL is already initialized in the constructor
    }

    @When("the user enters invalid credentials with email {string} and password {string}")
    public void theUserEntersInvalidCredentials(String email, String password) {
        String endpoint = "/verifyLogin";

        // Create the JSON request body
        String requestBody = String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);

        // POST request
        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(baseUrl + endpoint);
    }

    @Then("the user receives a response indicating that the login has failed")
    public void theUserReceivesAResponseIndicatingThatTheLoginHasFailed() {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("responseCode");

        if (statusCode == 200 && responseCode == 400) {
            // Check for the correct error message
            Assert.assertTrue(responseBody.contains("Bad request"), "Response does not indicate a bad request.");
        } else {
            Assert.fail("Unexpected status code: " + statusCode + " with response: " + responseBody);
        }
    }
}

