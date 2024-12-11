package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class createAccount {

    private String baseUrl;
    private Response response;

    public createAccount() {
        this.baseUrl = "https://automationexercise.com/api";
    }

    @Given("user clicks the create account link")
    public void userClicksTheCreateAccountLink() {
        // Base URL is already initialized in the constructor
    }

    @When("user passes the create account url with name {string} and form data")
    public void userPassesTheCreateAccountUrlWithNameAndFormData(String name) {
        String endpoint = "/createAccount";

        // Generate a unique email address using UUID
        String uniqueEmail = "unique_email_" + UUID.randomUUID().toString() + "@example.com";

        Map<String, String> formData = new HashMap<>();
        formData.put("name", name);
        formData.put("email", uniqueEmail);
        formData.put("password", "Password123");
        formData.put("firstname", "Neo");
        formData.put("lastname", "Banda");
        formData.put("address1", "12 wattle street");
        formData.put("country", "South Africa");
        formData.put("state", "Gauteng");
        formData.put("city", "Johannesburg");
        formData.put("zipcode", "1819");
        formData.put("mobile_number", "0735373313");

        // POST request
        response = RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParams(formData)
                .when()
                .post(baseUrl + endpoint);
    }

    @Then("user receives a response showing that the account has been created")
    public void userReceivesAResponseShowingThatTheAccountHasBeenCreated() {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        int statusCode = response.getStatusCode();
        int responseCode = response.jsonPath().getInt("responseCode");

        if (statusCode == 201 || (statusCode == 200 && responseCode == 201)) {
            // Check for the correct success message
            Assert.assertTrue(responseBody.contains("User created!"), "Response does not indicate account creation success.");
        } else if (statusCode == 400 && responseBody.contains("Email already exists!")) {
            Assert.fail("Email already exists. Please use a unique email address.");
        } else {
            Assert.fail("Unexpected status code: " + statusCode + " with response: " + responseBody);
        }
    }
}
