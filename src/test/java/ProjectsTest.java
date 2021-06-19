import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;

public class ProjectsTest {

    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiRequest apiRequest = new ApiRequest();
    ApiRequest apiRequest2 = new ApiRequest();
    Project project = new Project();

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

    @BeforeMethod(onlyForGroups = "putRequests")
    public void putRequests() {
        apiRequest = requestBuilder
                .method(ApiMethod.PUT)
                .build();
    }

    @BeforeMethod(onlyForGroups = "getRequests")
    public void getRequests() {
        apiRequest = requestBuilder
                .method(ApiMethod.GET)
                .build();
    }

    @BeforeMethod(onlyForGroups = "deleteRequests")
    public void deleteRequests() {
        apiRequest = requestBuilder
                .method(ApiMethod.DELETE)
                .build();
    }

    @BeforeMethod(onlyForGroups = {"updateProject", "deleteProject"})
    public void createProject() throws JsonProcessingException {
        Project testProject = new Project();
        testProject.setName("Task List 1000");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("projects")
                .method(ApiMethod.POST)
                .body(new ObjectMapper().writeValueAsString(testProject))
                .build();
        project = ApiManager.executeWithBody(apiRequest).getBody(Project.class);
    }

    @AfterMethod(onlyForGroups = "updateProject")
    public void deleteProjects() {
        apiRequest = requestBuilder.endpoint("/projects/{projectId}")
                .method(ApiMethod.DELETE)
                .clearParams()
                .clearQueryParams()
                .pathParms("projectId", project.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    @AfterMethod(onlyForGroups = "deleteProjectById")
    public void deleteProjectsById() {
        apiRequest = requestBuilder.endpoint("/projects/{projectId}")
                .method(ApiMethod.DELETE)
                .pathParms("projectId", project.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    @AfterMethod(onlyForGroups = "deleteAfterProject")
    public void deleteProjectsByIdAfter() {
        apiRequest = requestBuilder.endpoint("/projects/{projectId}")
                .method(ApiMethod.DELETE)
                .pathParms("projectId", project.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    @Test(groups = "getRequests")
    public void getAllProjectTest() {
        apiRequest2 = requestBuilder
                .endpoint("/projects")
                .clearParams()
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest2);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"getRequests", "updateProject"})
    public void getAProjectTest() {
        apiRequest2 = requestBuilder.endpoint("/projects/{projectId}")
                .method(ApiMethod.GET)
                .pathParms("projectId", project.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest2);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        apiResponse.validateBodySchema("schemas/project.json");
    }

    @Test(groups = {"getRequests", "updateProject"})
    public void getAProjectWithWrongIdTest() {
        apiRequest2 = requestBuilder.endpoint("/projects/{projectId}")
                .method(ApiMethod.GET)
                .pathParms("projectId", "0")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest2);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test(groups = {"getRequests", "updateProject"})
    public void getProjectActivityTest() {
        apiRequest2 = requestBuilder.endpoint("projects/{project_id}/activity")
                .method(ApiMethod.GET)
                .pathParms("project_id", project.getId().toString())
                .queryParams("limit","1")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest2);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"getRequests", "updateProject"})
    public void getProjectActivityWithWrongLimitTest() {
        apiRequest2 = requestBuilder.endpoint("projects/{project_id}/activity")
                .method(ApiMethod.GET)
                .clearParams()
                .pathParms("project_id", project.getId().toString())
                .queryParams("limit","a")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest2);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
    }

    @Test(groups = {"postRequests", "deleteProjectById"})
    public void createProjectTest() throws JsonProcessingException {
        Project testProject = new Project();
        testProject.setName("Task Listas");
        apiRequest = requestBuilder.endpoint("projects")
                .clearParams()
                .body(new ObjectMapper().writeValueAsString(testProject))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        project = apiResponse.getBody(Project.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        apiResponse.validateBodySchema("schemas/project.json");
    }

    @Test(groups = {"postRequests", "deleteProjectById"})
    public void createProjectAndCompareNameTest() throws JsonProcessingException {
        Project testProject = new Project();
        testProject.setName("Task Listas");
        apiRequest = requestBuilder.endpoint("projects")
                .body(new ObjectMapper().writeValueAsString(testProject))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        project = apiResponse.getBody(Project.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertNotEquals(project.getName(),"");
    }

    @Test(groups = {"updateProject", "putRequests"})
    public void updateProjectTest() throws JsonProcessingException {
        Project projectTemp = new Project();
        projectTemp.setName("Task Lists");
        apiRequest = requestBuilder.endpoint("/projects/{projectId}")
                .pathParms("projectId", project.getId().toString())
                .body(new ObjectMapper().writeValueAsString(projectTemp))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        project = apiResponse.getBody(Project.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(project.getName(), "Task Lists");
    }

    @Test(groups = {"updateProject", "putRequests"})
    public void updateProjectAndCompareNameTest() throws JsonProcessingException {
        Project projectTemp = new Project();
        projectTemp.setName("Task Lists");
        apiRequest = requestBuilder.endpoint("/projects/{projectId}")
                .pathParms("projectId", project.getId().toString())
                .body(new ObjectMapper().writeValueAsString(projectTemp))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        project = apiResponse.getBody(Project.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertNotEquals(project.getName(), "Task Listas");
    }

    @Test(groups = {"deleteRequests", "deleteProject"})
    public void deleteProjectTest() {
        apiRequest = requestBuilder.endpoint("/projects/{projectId}")
                .pathParms("projectId", project.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    @Test(groups = {"deleteRequests", "deleteProject", "deleteAfterProject"})
    public void deleteProjectWithWrongIdTest() {
        apiRequest = requestBuilder.endpoint("/projects/{projectId}")
                .pathParms("projectId", "0000000")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

}
