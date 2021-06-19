import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Analytics;
import entities.CycleTimeDetail;
import entities.Iteration;
import entities.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IterationTest {
    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiRequest apiRequest = new ApiRequest();
    ApiRequest apiRequest2 = new ApiRequest();
    Project project = new Project();
    Iteration testIteration = new Iteration();
    Analytics analytics = new Analytics();


    @BeforeTest
    public void initialConfiguration() {
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .build();
    }

    @BeforeMethod(onlyForGroups = "postRequests")
    public void postRequests() {
        apiRequest = requestBuilder
                .method(ApiMethod.POST)
                .build();
    }

    @BeforeMethod(onlyForGroups = "getRequests")
    public void getRequests() {
        apiRequest = requestBuilder
                .method(ApiMethod.GET)
                .build();
    }

    @BeforeMethod(onlyForGroups = "putRequests")
    public void putRequests() {
        apiRequest = requestBuilder
                .method(ApiMethod.PUT)
                .build();
    }

    @BeforeMethod(onlyForGroups = "deleteRequests")
    public void deleteRequests() {
        apiRequest = requestBuilder
                .method(ApiMethod.DELETE)
                .build();
    }

    @BeforeMethod(onlyForGroups = "createAProject")
    public void createProject2() throws JsonProcessingException {
        System.out.println("------------------------- create a project");
        Project testProject = new Project();
        testProject.setName("New project");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest2 = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("projects")
                .method(ApiMethod.POST)
                .body(new ObjectMapper().writeValueAsString(testProject))
                .build();
        project = ApiManager.executeWithBody(apiRequest2).getBody(Project.class);

    }

    @AfterMethod(onlyForGroups = {"createAProject"})
    public void deleteProject2ById() {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("/projects/{projectId}")
                .method(ApiMethod.DELETE)
                .pathParms("projectId", project.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    @Test(groups = {"getRequests", "createAProject"})
    public void getProjectIterationsTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/iterations")
                .clearParams()
                .pathParms("project_id", project.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"getRequests", "createAProject"})
    public void getAProjectIterationTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/iterations/{number}")
                .clearParams()
                .pathParms("project_id", project.getId().toString())
                .pathParms("number", "1")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"putRequests", "createAProject"})
    public void updateAProjectStoryTest() throws JsonProcessingException {
        Iteration iteration = new Iteration();
        iteration.setLength(8);
        iteration.setTeam_strength(5);
        apiRequest = requestBuilder.endpoint("projects/{project_id}/iteration_overrides/{number}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("number", "1")
                .body(new ObjectMapper().writeValueAsString(iteration))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        testIteration = apiResponse.getBody(Iteration.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(testIteration.getLength(), 8);
        Assert.assertEquals(testIteration.getTeam_strength(),5);
    }

    @Test(groups = {"getRequests", "createAProject"})
    public void getIterationsAnalyticsOfAProjectTest() {
        apiRequest = requestBuilder.endpoint("/projects/{projectId}/iterations/{number}/analytics")
                .clearParams()
                .pathParms("projectId", project.getId().toString())
                .pathParms("number", "1")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        analytics = apiResponse.getBody(Analytics.class);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        Assert.assertEquals(analytics.getKind(), "analytics");
    }

    @Test(groups = {"getRequests", "createAProject"})
    public void getIterationsAnalyticsDetailsOfAProjectTest() {
        apiRequest = requestBuilder.endpoint("projects/{projectId}/iterations/{number}/analytics/cycle_time_details")
                .clearParams()
                .pathParms("projectId", project.getId().toString())
                .pathParms("number", "1")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

}
