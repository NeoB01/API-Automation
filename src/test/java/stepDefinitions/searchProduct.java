package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.List;

public class searchProduct {

    private String baseUrl;
    private Response response;

    @Given("user clicks the search products link")
    public void userClicksTheSearchProductsLink() {
        baseUrl = "https://automationexercise.com/api";
    }

    @When("user passes the search product url")
    public void userPassesTheSearchProductUrl() {
        String endpoint = "/searchProduct";

        String searchProduct = "Half Sleeves Top Schiffli Detailing - Pink";

        response = RestAssured.given().log().all()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("search_product", searchProduct)
                .when().log().all()
                .post(baseUrl + endpoint);
    }

    @Then("user receives a post search product response")
    public void userReceivesAPostSearchProductResponse() {
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but found " + response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        JsonPath jsonPath = response.jsonPath();

        List<Object> products = jsonPath.getList("products");

        System.out.println("Products:");
        for (Object product : products) {
            System.out.println(product);
        }
    }
}