package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class searchProductWithoutParameter {

    private String baseUrl;
    private Response response;

    @Given("user clicks the products link")
    public void userClicksTheSearchProductsLink() {
        baseUrl = "https://automationexercise.com/api";
    }

    @When("user passes the products url")
    public void userPassesTheSearchProductUrl() {
        String endpoint = "/searchProduct";

        response = RestAssured.given().log().all()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .when().log().all()
                .post(baseUrl + endpoint);
    }

    @Then("user receives a post response without product name or product parameter")
    public void userReceivesAPostSearchProductResponse() {
        System.out.println("Response Body: " + response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but found " + response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.getInt("responseCode"), 400, "Expected response code 400 but found " + jsonPath.getInt("responseCode"));
        Assert.assertEquals(jsonPath.getString("message"), "Bad request, search_product parameter is missing in POST request.", "Expected message 'Bad request, search_product parameter is missing in POST request.' but found " + jsonPath.getString("message"));
    }
}
