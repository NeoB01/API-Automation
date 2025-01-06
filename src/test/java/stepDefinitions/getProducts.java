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
        baseUrl = "https://automationexercise.com/api";    }


    @When("user passes the get all products url")
    public void userPassesTheGetAllProductsUrl() {
        // GET request
        response = RestAssured.given()
                .header("Content-Type", "application/json")
                .when()
                .get(baseUrl + "/productsList");

    }

    @Then("user receives a response of all the products")
    public void userReceivesAResponseOfAllTheProducts() {
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertNotNull(response.getBody().asString(), "Response body should not be null");
        List<Map<String, Object>> products = response.jsonPath().getList("products");

        System.out.println("Products:");
        for (Object brand : products) {
            System.out.println(brand);

            Assert.assertFalse(products.isEmpty(), "No products found in the response");
        }
    }

}
