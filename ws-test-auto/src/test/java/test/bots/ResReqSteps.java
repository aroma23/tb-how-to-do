package test.bots;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author  Muthukumar Ramaiyah (@testbots-tamil youtube channel)
 * @version 1.0
 * @since   2023-02-01
 */
public class ResReqSteps {
    private Response response;
//    private final RequestSpecification request = given().contentType(ContentType.JSON).log().all();
    private final RequestSpecification request = given().contentType(ContentType.JSON);
//    private final RequestSpecification request = given().log().all();
    private String userId;
    private Properties properties;

    public ResReqSteps() throws IOException {
        String testEnv = System.getProperty("test.env", "mock").toLowerCase();
        FileReader reader=new FileReader(String.format("src/test/resources/%s.properties", testEnv));
        properties = new Properties();
        properties.load(reader);
        properties.putAll(System.getProperties());
        request.baseUri(properties.getProperty("host.url", "https://reqres.in"));
    }
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
//        response.body().prettyPrint();
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
//        response.body().prettyPrint();
//        this.userId = response.then().extract().path("id");
//        System.out.println("Created user's id: " + userId);
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
//        response.then().log().all();
    }

    @Then("Get User api response should have correct firstName: {string}, and lastName: {string}")
    public void getUserApiResponseShouldHaveCorrectFirstNameAndLastName(String firstName, String lastName) {
        response.then()
                .body("data.first_name", equalTo(firstName))
                .body("data.last_name", equalTo(lastName));
    }

    @Then("Update User api should respond with response code: {int}")
    public void updateUserApiShouldRespondWithResponseCode(int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @Then("Update User api response should have right schema")
    public void updateUserApiResponseShouldHaveRightSchema() {
        response
                .then()
                .body(JsonSchemaValidator
                        .matchesJsonSchema(new File("src/test/resources/schemas/updateUser.json")));
    }

    @When("Update User api is called to update user: {string} info email: {string}, and job: {string}")
    public void updateUserApiIsCalledToUpdateUserInfoEmailAndJob(String id, String email,
                                                                 String job) throws IOException {
        String updateUserBody = Files.readString(Paths.get("src/test/resources/payloads/updateUser.json"));
        updateUserBody =  updateUserBody
                .replace("<<email>>", email)
                .replace("<<job>>", job);
//        response = request.body(updateUserBody).log().all().put("/api/users/" + id);
        response = request.body(updateUserBody).put("/api/users/" + id).andReturn();
//        response.then().log().all();
    }

    @Then("Update User api response should have updated email: {string}, and job: {string}")
    public void updateUserApiResponseShouldHaveUpdatedEmailAndJob(String email, String job) {
        response.then()
                .body("email", equalTo(email))
                .body("job", equalTo(job));
    }

    @Then("Login User api should respond with response code: {int}")
    public void loginUserApiShouldRespondWithResponseCode(int expectedCode) {
//        response.body().prettyPrint();
        response.then().statusCode(expectedCode);
    }

    @Then("Login User api response should have right schema")
    public void loginUserApiResponseShouldHaveRightSchema() {
        response.then()
                .body(JsonSchemaValidator.matchesJsonSchema(
                        new File("src/test/resources/schemas/loginUser.json")));
    }

    @When("Login User api is called with email: {string} and password: {string}")
    public void loginUserApiIsCalledWithEmailEmailAndPasswordPassword(String email, String pwd) throws IOException {
        String loginUserBody = Files.readString(Paths.get("src/test/resources/payloads/loginUser.json"));
        loginUserBody = loginUserBody.replace("<<email>>", email).replace("<<pwd>>", pwd);
        response = request.body(loginUserBody).post("/api/login").andReturn();
    }
}
