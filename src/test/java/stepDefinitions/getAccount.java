package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;


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
                .header("Content-Type", "application/json").body(email)
                .when().log().all()
                .get(baseUrl + endpoint).then().extract().response();
    }

    @Then("user receives a response of user account")
    public void userReceivesAResponseOfUserAccount() {

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.jsonPath().prettyPrint().toString());

    }
}