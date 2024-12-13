package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class updateUser {

    private static final String BASE_URL = "https://automationexercise.com/api";
    private static final String ENDPOINT = "/updateAccount";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String FORM_URLENCODED = "application/x-www-form-urlencoded";
    private static final String EMAIL = "neo.banda@example.com";
    private static final String PASSWORD = "Password123";
    private static final String SUCCESS_MESSAGE = "User updated!";
    private Response response;

    @When("the user updates their account details with valid information")
    public void theUserUpdatesTheirAccountDetailsWithValidInformation() {
        response = RestAssured.given()
                .header(CONTENT_TYPE, FORM_URLENCODED)
                .formParam("email", EMAIL)
                .formParam("password", PASSWORD)
                .formParam("name", "Neo Banda")
                .when()
                .put(BASE_URL + ENDPOINT);

        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Headers: " + response.getHeaders().toString());
    }

    @Then("the user receives a response indicating that the account has been updated successfully")
    public void theUserReceivesAResponseIndicatingThatTheAccountHasBeenUpdatedSuccessfully() {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("responseCode");

        Assert.assertEquals(statusCode, 200, "Unexpected status code: " + statusCode);
        Assert.assertEquals(responseCode, 200, "Unexpected response code: " + responseCode);
        Assert.assertTrue(responseBody.contains(SUCCESS_MESSAGE), "Response does not indicate that the account has been updated successfully.");

        if (statusCode != 200 || responseCode != 200) {
            System.err.println("Failed to update account. Status Code: " + statusCode + ", Response Code: " + responseCode);
        }
    }
}
