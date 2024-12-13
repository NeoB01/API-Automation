package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class deleteAccount {

    private String baseUrl;
    private Response response;
    private String email;
    private String password;

    public deleteAccount() {
        this.baseUrl = "https://automationexercise.com/api";
    }

    @Given("the user is logged in with a valid account")
    public void theUserIsLoggedInWithAValidAccount() {
        email = "neo.banda@example.com";
        password = "Password123";
    }

    @When("the user deletes their account")
    public void theUserDeletesTheirAccount() {
        String endpoint = "/deleteAccount";

        try {
            response = RestAssured.given()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .formParam("email", email)
                    .formParam("password", password)
                    .when()
                    .delete(baseUrl + endpoint);

            System.out.println("Delete Request: email=" + email + "&password=****");
            System.out.println("Response Body: " + response.getBody().asString());
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Headers: " + response.getHeaders().toString());
        } catch (Exception e) {
            System.err.println("Error occurred while deleting account: " + e.getMessage());
        }
    }

    @Then("the user receives a response indicating that the account has been deleted")
    public void theUserReceivesAResponseIndicatingThatTheAccountHasBeenDeleted() {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("responseCode");

        if (statusCode == 200 && responseCode == 200) {
            Assert.assertTrue(responseBody.contains("Account deleted!"), "Response does not indicate account deletion success.");
        } else {
            Assert.fail("Unexpected status code: " + statusCode + " with response: " + responseBody);
        }
    }
}

