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

    @BeforeMethod(onlyForGroups = "createProject")
    public void createProject() throws JsonProcessingException {
        System.out.println("------------------------- create project");
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
        System.out.println("------------------------- create a project");
        Project testProject = new Project();
        testProject.setName("Task List 102");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest2 = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("projects")
                .method(ApiMethod.POST)
                .body(new ObjectMapper().writeValueAsString(testProject))
                .build();
        project2 = ApiManager.executeWithBody(apiRequest2).getBody(Project.class);
    }

    @BeforeMethod(dependsOnMethods = "createProject", onlyForGroups = "createProject")
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

    @AfterMethod(onlyForGroups = {"createProject"})
    public void deleteProjectById() {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("/projects/{projectId}")
                .method(ApiMethod.DELETE)
                .clearQueryParams()
                .clearParams()
                .pathParms("projectId", project.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    @AfterMethod(onlyForGroups = {"createAProject"})
    public void deleteProject2ById() {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("/projects/{projectId}")
                .method(ApiMethod.DELETE)
                .pathParms("projectId", project2.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    @Test(groups = {"getRequests", "createAProject"})
    public void getEpicsTest() {
        apiRequest = requestBuilder
                .endpoint("projects/{project_id}/epics")
                .clearParams()
                .pathParms("project_id", project2.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"postRequests", "createProject"})
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

    @Test(groups = {"getRequests", "createProject"})
    public void getAEpicTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/epics/{epic_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("epic_id", epics.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"getRequests", "createProject"})
    public void getAEpicActivityTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/epics/{epic_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("epic_id", epics.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"putRequests", "createProject"})
    public void updateAEpicTest() throws JsonProcessingException {
        Epic epic = new Epic();
        epic.setDescription("new description");
        apiRequest = requestBuilder.endpoint("projects/{project_id}/epics/{epic_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("epic_id", epics.getId().toString())
                .body(new ObjectMapper().writeValueAsString(epic))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        epics = apiResponse.getBody(Epic.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(epics.getDescription(), "new description");
    }

    @Test(groups = {"deleteRequests", "createProject"})
    public void deleteAEpicTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/epics/{epic_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("epic_id", epics.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }
}
