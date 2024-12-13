package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class updateAccount {

    private String baseUrl;
    private Response response;
    private String email;
    private String password;
    private String newName;
    private String newEmail;

    public updateAccount() {
        this.baseUrl = "https://automationexercise.com/api";
    }
//
//    @Given("the user is logged in with a valid account")
//    public void theUserIsLoggedInWithAValidAccount() {
//        email = "neo.banda@example.com";
//        password = "Password123";
//        newName = "Neo Updated";
//        newEmail = "neo.updated@example.com";
//    }

    @When("the user updates their account details")
    public void theUserUpdatesTheirAccountDetails() {
        String endpoint = "/updateAccount";

        try {
            response = RestAssured.given()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .formParam("email", email)
                    .formParam("password", password)
                    .formParam("newName", newName)
                    .formParam("newEmail", newEmail)
                    .when()
                    .put(baseUrl + endpoint);

            System.out.println("Update Request: email=" + email + "&password=****&newName=" + newName + "&newEmail=" + newEmail);
            System.out.println("Response Body: " + response.getBody().asString());
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response Headers: " + response.getHeaders().toString());
        } catch (Exception e) {
            System.err.println("Error occurred while updating account: " + e.getMessage());
        }
    }

    @Then("the user receives a response indicating that the account has been updated")
    public void theUserReceivesAResponseIndicatingThatTheAccountHasBeenUpdated() {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("responseCode");

        if (statusCode == 200 && responseCode == 200) {
            Assert.assertTrue(responseBody.contains("Account updated!"), "Response does not indicate account update success.");
        } else {
            Assert.fail("Unexpected status code: " + statusCode + " with response: " + responseBody);
        }
    }
}
