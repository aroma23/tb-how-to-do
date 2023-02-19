package test.bots;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ResReqSteps {
    private Response response;
//    private final RequestSpecification request = given().baseUri("https://reqres.in");
    private final RequestSpecification request = given().baseUri("https://reqres.in").contentType(ContentType.JSON);
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

//        response = request.body(createUserBody).log().all().post("/api/users").andReturn();
        response = request.body(createUserBody).post("/api/users").andReturn();
//        response.then().log().all();
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

    @When("Delete User api is called with userId: {string}")
    public void deleteUserApiIsCalledWithUserIdId(String userId) {
        response = request.delete("api/users/" + userId).andReturn();
    }

    @Then("Delete User api should respond with response code: {int}")
    public void deleteUserApiShouldRespondWithResponseCodeExpectedCode(int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @When("Create User api is called with first name: {string}, last name: {string}, and job: {string}")
    public void createUserApiIsCalledWithFirstNameLastNameAndJob(String firstName, String lastName,
                                                                 String job) throws IOException {
        String createUserBody = Files.readString(Paths.get("src/test/resources/payloads/createUserNegative.json"));
        createUserBody =  createUserBody.replace("<<firstName>>", firstName)
                .replace("<<lastName>>", lastName)
                .replace("<<job>>", job);
        response = request.body(createUserBody).post("/api/users").andReturn();
    }

    @Then("Get User api response should have correct firstName: {string}, and lastName: {string}")
    public void getUserApiResponseShouldHaveCorrectFirstNameAndLastName(String firstName, String lastName) {
        response.then()
                .body("data.first_name", equalTo(firstName))
                .body("data.last_name", equalTo(lastName));
    }
}
