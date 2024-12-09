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
        // Setting the base URL for the API
        baseUrl = "https://automationexercise.com/api/productsList";
    }

    @When("user passes the get all products url")
    public void userPassesTheGetAllProductsUrl() {
        // GET request to fetch all products
        response = RestAssured.given().log().all()
                .header("Content-Type", "application/json")
                .when()
                .get(baseUrl);
    }

    @Then("user receives a response of all the products")
    public void userReceivesAResponseOfAllTheProducts() {
        // Validate the response
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertNotNull(response.getBody().asString(), "Response body should not be null");

        // Print the entire response body for inspection
        System.out.println("Response Body: " + response.getBody().asString());

        // Extract the list of products from the response
        List<Map<String, Object>> products = response.jsonPath().getList("products");

        // Check if products are present
        Assert.assertTrue(products.size() > 0, "No products found in the response");


    }
}