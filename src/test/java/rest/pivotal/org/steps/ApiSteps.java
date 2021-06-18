package rest.pivotal.org.steps;

import api.ApiRequestBuilder;
import api.ApiRequest;
import api.ApiResponse;
import api.ApiMethod;
import api.ApiManager;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Project;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.testng.Assert;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiSteps {

    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    private ApiRequest apiRequest = new ApiRequest();
    private ApiResponse apiResponse;
    Project project = new Project();

    private String userToken = "ab535e3e5e63442f37c020243e5360eb";
    private String baseUri = "https://www.pivotaltracker.com/services/v5";

    @Before
    public void createProject() throws JsonProcessingException {
        Project projectTemp = new Project();
        projectTemp.setName("Task List 3");
        apiRequest = requestBuilder.baseUri(baseUri)
                .header("X-TrackerToken","ab535e3e5e63442f37c020243e5360eb")
                .endpoint("projects")
                .method(ApiMethod.valueOf("POST"))
                .body(new ObjectMapper().writeValueAsString(projectTemp))
                .build();
        project = ApiManager.executeWithBody(apiRequest).getBody(Project.class);
    }

    @Given("I build {string} request")
    public void iBuildRequest(String method) {
        apiRequest = requestBuilder.baseUri(baseUri)
                .token(userToken)
                .method(ApiMethod.valueOf(method))
                .build();
    }

    @When("I execute {string} request")
    public void iExecuteRequest(String endpoint) {
        apiRequest = requestBuilder.endpoint(endpoint)
                .pathParms("projectId", project.getId().toString())
                .build();
        apiResponse = ApiManager.execute(apiRequest);
    }

    @Then("the response status code should be {string}")
    public void theResponseStatusCodeShouldBe(String statusCode) {
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        apiResponse.getResponse().then().log().body();
    }
}
