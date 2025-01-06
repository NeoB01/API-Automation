package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class getProducts {

    private String baseUrl;
    private Response response;

    @Given("user clicks the get all products url")
    public void userClicksTheGetAllProductsUrl() {
        baseUrl = "https://automationexercise.com/api";
    }

    @When("user passes the get all products url")
    public void userPassesTheGetAllProductsUrl() {
        // GET request
        response = RestAssured.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .get(baseUrl);
    }

    @Then("user receives a response of all the products")
    public void userReceivesAResponseOfAllTheProducts() {
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertNotNull(response.getBody().asString(), "Response body should not be null");
        System.out.println("Response Body: " + response.getBody().asString());
        List<Map<String, Object>> products = response.jsonPath().getList("products");

        Assert.assertFalse(products.isEmpty(), "No products found in the response");

    }
}