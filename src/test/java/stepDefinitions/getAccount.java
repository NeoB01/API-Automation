package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.Map;

public class getAccount {

    private String baseUrl;
    private Response response;

    @Given("user clicks the get user url")
    public void userClicksTheGetUserUrl() {
        baseUrl = "https://automationexercise.com/api";
    }

    @When("user passes the url of get user")
    public void userPassesTheUrlOfGetUser () {
        String email = "neo.banda@example.com";
        String endpoint = "/getUserDetailByEmail?email=" + email;

        // GET request
        response = RestAssured.given().log().all()
                .header("Content-Type", "application/json")
                .when().log().all()
                .get(baseUrl + endpoint);
    }

    @Then("user receives a response of user account")
    public void userReceivesAResponseOfUserAccount() {
        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.getBody().asString());

        // Extract user details from the response
        Map<String, Object> user = response.jsonPath().getMap("user");

        // Display user account details in a formatted way
        System.out.println("User Account Details:");
        System.out.println("==================================");
        System.out.println("User ID: " + user.get("id"));
        System.out.println("Email: " + user.get("email"));
        System.out.println("First Name: " + user.get("first_name"));
        System.out.println("Last Name: " + user.get("last_name"));
        System.out.println("Address: " + user.get("address1"));
        System.out.println("City: " + user.get("city"));
        System.out.println("State: " + user.get("state"));
        System.out.println("Country: " + user.get("country"));
        System.out.println("==================================");
    }
}