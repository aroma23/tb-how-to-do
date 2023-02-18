package test.bots;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ResReqSteps {
    private Response response;
    private final RequestSpecification request = given().baseUri("https://reqres.in");
    @Given("^User exist in system$")
    public void userExistInSystem() {
        System.out.println("Lets assume user always exists in mock");
    }

    @When("Get User api is called with userId: {string}")
    public void getUserApiIsCalledWithUserId(String userId) {
        response = request
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
        response.then()
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/schemas/getUser.json")));
    }

    @When("Get Users api is called")
    public void getUsersApiIsCalled() {
        response = request
                .get("api/users")
                .andReturn();
    }

    @Then("Get Users api should respond with response code: {int}")
    public void getUsersApiShouldRespondWithResponseCode(int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @Then("Get Users api response should have right schema")
    public void getUsersApiResponseShouldHaveRightSchema() {
        response.then()
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/schemas/getUsers.json")));
    }

    @When("Create User api is called with first name: {string}, last name: {string}, email: {string}, and job: {string}")
    public void createUserApiIsCalledWithFirstLastNameAndEmail(String firstName, String lastName, String email,
                                                               String job) throws IOException {
        String createUserBody = Files.readString(Paths.get("src/test/resources/payloads/createUser.json"));
        createUserBody =  createUserBody.replace("<<firstName>>", firstName)
                        .replace("<<lastName>>", lastName)
                        .replace("<<email>>", email)
                        .replace("<<job>>", job);
        System.out.println(createUserBody);
        response = request.body(createUserBody).post().andReturn();
    }

    @Then("Create User api should respond with response code: {int}")
    public void createUserApiShouldRespondWithResponseCode(int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @Then("Create User api response should have right schema")
    public void createUserApiResponseShouldHaveRightSchema() {
        response.then()
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/schemas/createUser.json")));
    }
}
