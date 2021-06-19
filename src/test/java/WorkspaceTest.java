import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Project;
import entities.Workspaces;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class WorkspaceTest {
    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiRequest apiRequest = new ApiRequest();
    Project project = new Project();
    Workspaces testWorkspaces = new Workspaces();

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

    @BeforeMethod(onlyForGroups = "createWorkspace")
    public void createAWorkspace() throws JsonProcessingException {
        Workspaces workspaces = new Workspaces();
        workspaces.setName("Work group");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("my/workspaces")
                .method(ApiMethod.POST)
                .body(new ObjectMapper().writeValueAsString(workspaces))
                .build();
        testWorkspaces = ApiManager.executeWithBody(apiRequest).getBody(Workspaces.class);
    }

    @BeforeMethod(onlyForGroups = "createAWorkspaceProject")
    public void createWorkspace() throws JsonProcessingException {
        Workspaces workspaces = new Workspaces();
        workspaces.setName("Work group");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("my/workspaces")
                .method(ApiMethod.POST)
                .body(new ObjectMapper().writeValueAsString(workspaces))
                .build();
        testWorkspaces = ApiManager.executeWithBody(apiRequest).getBody(Workspaces.class);
    }

    @BeforeMethod(dependsOnMethods = "createWorkspace", onlyForGroups = "createAWorkspaceProject")
    public void createProject() throws JsonProcessingException {
        System.out.println("------------------------- create project");
        Project testProject = new Project();
        testProject.setName("Project Workspace v");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("projects")
                .method(ApiMethod.POST)
                .body(new ObjectMapper().writeValueAsString(testProject))
                .build();
        project = ApiManager.executeWithBody(apiRequest).getBody(Project.class);
    }

    @AfterMethod(onlyForGroups = {"deleteWorkspace"})
    public void deleteWorkspace() {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("my/workspaces/{workspace_id}")
                .method(ApiMethod.DELETE)
                .pathParms("workspace_id", testWorkspaces.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    @AfterGroups(groups = "createAWorkspaceProject")
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

    @Test(groups = {"getRequests"})
    public void getWorkspaces() {
        apiRequest = requestBuilder
                .endpoint("my/workspaces")
                .clearParams()
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"postRequests", "deleteWorkspace"})
    public void createWorkspaces() throws JsonProcessingException {
        Workspaces workspaces = new Workspaces();
        workspaces.setName("The workspace");
        apiRequest = requestBuilder
                .endpoint("my/workspaces")
                .body(new ObjectMapper().writeValueAsString(workspaces))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        testWorkspaces = apiResponse.getBody(Workspaces.class);
        Assert.assertEquals(workspaces.getName(),"The workspace");
    }

    @Test(groups = {"putRequests", "createAWorkspaceProject"})
    public void updateWorkspace() throws JsonProcessingException {
        Workspaces workspaces = new Workspaces();
        List<Object> project_ids=new ArrayList<>();
        project_ids.add(project.getId());
        workspaces.setProject_ids(project_ids);
        apiRequest = requestBuilder
                .endpoint("my/workspaces/{workspace_id}")
                .pathParms("workspace_id",testWorkspaces.getId().toString())
                .body(new ObjectMapper().writeValueAsString(workspaces))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        apiResponse.getResponse().then().log().body();
        testWorkspaces = apiResponse.getBody(Workspaces.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"deleteRequests", "createWorkspace"})
    public void deleteAWorkspace() {
        apiRequest = requestBuilder
                .endpoint("my/workspaces/{workspace_id}")
                .pathParms("workspace_id", testWorkspaces.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

}
