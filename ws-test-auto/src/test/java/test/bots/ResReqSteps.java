package test.bots;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ResReqSteps {
    private Response response;
    @Given("^User exist in system$")
    public void userExistInSystem() {
        System.out.println("Lets assume user always exists in mock");
    }

    @When("Get User api is called with userId: {string}")
    public void getUserApiIsCalledWithUserId(String userId) {
        System.out.println("hello: " + userId);
        response = given()
                .baseUri("https://reqres.in")
                .get("api/users/" + userId)
                .andReturn();
//        //below statement to log response on console
//        response.then().log().all();
    }

    @Then("Get User api should respond with response code: {int}")
    public void getUserApiShouldRespondWithResponseCode(int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @Then("Get User api response should have right schema")
    public void getUserApiResponseShouldHaveRightSchema() {
        System.out.println("hello");
    }
}
