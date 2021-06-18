import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Epic;
import entities.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;

public class EpicTest {
    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiRequest apiRequest = new ApiRequest();
    ApiRequest apiRequest2 = new ApiRequest();
    ApiResponse apiResponse;
    Project project = new Project();
    Project project2 = new Project();
    Epic epics = new Epic();

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

    @BeforeSuite
    public void createProject() throws JsonProcessingException {
        Project testProject = new Project();
        testProject.setName("Task List 101");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("projects")
                .method(ApiMethod.POST)
                .body(new ObjectMapper().writeValueAsString(testProject))
                .build();
        project = ApiManager.executeWithBody(apiRequest).getBody(Project.class);
    }

    @BeforeMethod(onlyForGroups = "createAProject")
    public void createProject2() throws JsonProcessingException {
        Project testProject = new Project();
        testProject.setName("Task List 102");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("projects")
                .method(ApiMethod.POST)
                .body(new ObjectMapper().writeValueAsString(testProject))
                .build();
        project2 = ApiManager.executeWithBody(apiRequest).getBody(Project.class);
    }

    @BeforeMethod(onlyForGroups = "createEpic")
    public void createEpic() throws JsonProcessingException {
        Epic epic = new Epic();
        epic.setName("new epics");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("projects/{project_id}/epics")
                .method(ApiMethod.POST)
                .pathParms("project_id", project.getId().toString())
                .body(new ObjectMapper().writeValueAsString(epic))
                .build();
        epics = ApiManager.executeWithBody(apiRequest).getBody(Epic.class);
    }

    @AfterSuite
    public void deleteProjectsById() {
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
    public void getEpicsTest() {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest2 = requestBuilder
                .endpoint("projects/{project_id}/epics")
                .pathParms("project_id", project2.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"postRequests"})
    public void createEpicTest() throws JsonProcessingException {
        Epic epic = new Epic();
        epic.setName("New Epic");
        apiRequest = requestBuilder.endpoint("projects/{project_id}/epics")
                .pathParms("project_id", project.getId().toString())
                .body(new ObjectMapper().writeValueAsString(epic))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        epics = apiResponse.getBody(Epic.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(epics.getName(), "New Epic");
    }

    @Test(groups = {"getRequests", "createEpic"})
    public void getAEpicTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/epics/{epic_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("epic_id", epics.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

}
