import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Label;
import entities.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LabelTest {
    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiRequest apiRequest = new ApiRequest();
    ApiRequest apiRequest2 = new ApiRequest();
    Project project = new Project();
    Project project2 = new Project();
    Label testLabel = new Label();

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
        testProject.setName("New Task List");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("projects")
                .method(ApiMethod.POST)
                .body(new ObjectMapper().writeValueAsString(testProject))
                .build();
        project = ApiManager.executeWithBody(apiRequest).getBody(Project.class);
    }

    @BeforeMethod(dependsOnMethods = "createProject", onlyForGroups = "createProject")
    public void createLabel() throws JsonProcessingException {
        Label label = new Label();
        label.setName("new label");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("projects/{project_id}/labels")
                .method(ApiMethod.POST)
                .pathParms("project_id", project.getId().toString())
                .body(new ObjectMapper().writeValueAsString(label))
                .build();
        testLabel = ApiManager.executeWithBody(apiRequest).getBody(Label.class);
    }

    @BeforeMethod(onlyForGroups = "createAProject")
    public void createProject2() throws JsonProcessingException {
        System.out.println("------------------------- create a project");
        Project testProject = new Project();
        testProject.setName("Tasks Lists");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest2 = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("projects")
                .method(ApiMethod.POST)
                .body(new ObjectMapper().writeValueAsString(testProject))
                .build();
        project2 = ApiManager.executeWithBody(apiRequest2).getBody(Project.class);
    }

    @AfterMethod(onlyForGroups = {"createProject"})
    public void deleteProjectById() {
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
    public void getProjectLabelsTest() {
        apiRequest = requestBuilder
                .endpoint("projects/{project_id}/labels")
                .clearParams()
                .pathParms("project_id", project2.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"postRequests", "createAProject"})
    public void createProjectLabelTest() throws JsonProcessingException {
        Label label = new Label();
        label.setName("new label");
        apiRequest = requestBuilder.endpoint("projects/{project_id}/labels")
                .pathParms("project_id", project2.getId().toString())
                .body(new ObjectMapper().writeValueAsString(label))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        testLabel = apiResponse.getBody(Label.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(testLabel.getName(), "new label");
    }

    @Test(groups = {"getRequests", "createProject"})
    public void getAEpicTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/labels/{label_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("label_id", testLabel.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"putRequests", "createProject"})
    public void updateAEpicTest() throws JsonProcessingException {
        Label label = new Label();
        label.setName("new label name");
        apiRequest = requestBuilder.endpoint("projects/{project_id}/labels/{label_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("label_id", testLabel.getId().toString())
                .body(new ObjectMapper().writeValueAsString(label))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        testLabel = apiResponse.getBody(Label.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(testLabel.getName(), "new label name");
    }

    @Test(groups = {"deleteRequests", "createProject"})
    public void deleteAEpicTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/labels/{label_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("label_id", testLabel.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

}
