package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.List;

public class getBrands {

    private String baseUrl;
    private Response response;

    @Given("user clicks the get all brands url")
    public void userClicksTheGetAllBrandsUrl() {
        baseUrl = "https://automationexercise.com/api";
    }

    @When("user passes the get all brands url")
    public void userPassesTheGetAllBrandsUrl() {
        String endpoint = "/brandsList";

        // GET request
        response = RestAssured.given().log().all()
                .header("Content-Type", "application/json")
                .when().log().all()
                .get(baseUrl + endpoint);
    }

    @Then("user receives a response of all the brands")
    public void userReceivesAResponseOfAllTheBrands() {
        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 but found " + response.getStatusCode());
        Assert.assertNotNull(response.getBody().asString());
        JsonPath jsonPath = response.jsonPath();
        List<Object> brands = jsonPath.getList("brands");

        System.out.println("Brands:");
        for (Object brand : brands) {
            System.out.println(brand);
        }
    }
}