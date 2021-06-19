import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Project;
import entities.ProjectWebHooks;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProjectWebHooksTest {
    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiRequest apiRequest = new ApiRequest();
    ApiRequest apiRequest2 = new ApiRequest();
    Project project = new Project();
    Project project2 = new Project();
    ProjectWebHooks testWebHook = new ProjectWebHooks();

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
        testProject.setName("Project Lists");
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
        testProject.setName("Project List");
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
    public void createProjectWebHook() throws JsonProcessingException {
        System.out.println("---------------- create WebHook");
        ProjectWebHooks projectWebHooks = new ProjectWebHooks();
        projectWebHooks.setWebhook_url("https:///story/show/561");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("projects/{project_id}/webhooks")
                .method(ApiMethod.POST)
                .pathParms("project_id", project.getId().toString())
                .body(new ObjectMapper().writeValueAsString(projectWebHooks))
                .build();
        testWebHook = ApiManager.executeWithBody(apiRequest).getBody(ProjectWebHooks.class);
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
    public void getProjectWebHooksTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/webhooks")
                .clearParams()
                .pathParms("project_id", project2.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"getRequests", "createAProject"})
    public void getProjectWebHooksWithWrongIdTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/webhooks")
                .clearParams()
                .pathParms("project_id", project2.getId().toString() + "0")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test(groups = {"postRequests", "createAProject"})
    public void createProjectWebHooksTest() throws JsonProcessingException {
        ProjectWebHooks projectWebHooks = new ProjectWebHooks();
        projectWebHooks.setWebhook_url("https:///story/show/560");
        apiRequest = requestBuilder.endpoint("projects/{project_id}/webhooks")
                .pathParms("project_id", project2.getId().toString())
                .body(new ObjectMapper().writeValueAsString(projectWebHooks))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        testWebHook = apiResponse.getBody(ProjectWebHooks.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(testWebHook.getWebhook_url(), "https:///story/show/560");
    }

    @Test(groups = {"postRequests", "createAProject"})
    public void createProjectWebHooksAndCompareUrlTest() throws JsonProcessingException {
        ProjectWebHooks projectWebHooks = new ProjectWebHooks();
        projectWebHooks.setWebhook_url("https:///story/show/560");
        apiRequest = requestBuilder.endpoint("projects/{project_id}/webhooks")
                .pathParms("project_id", project2.getId().toString())
                .body(new ObjectMapper().writeValueAsString(projectWebHooks))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        testWebHook = apiResponse.getBody(ProjectWebHooks.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertNotEquals(testWebHook.getWebhook_url(), "");
    }

    @Test(groups = {"getRequests", "createProject"})
    public void getAProjectWebHookTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/webhooks/{webhook_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("webhook_id", testWebHook.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"getRequests", "createProject"})
    public void getAProjectWebHookWithWrongIdTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/webhooks/{webhook_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("webhook_id", testWebHook.getId().toString() + "0")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test(groups = {"putRequests", "createProject"})
    public void updateAProjectWebHookTest() throws JsonProcessingException {
        ProjectWebHooks projectWebHooks = new ProjectWebHooks();
        projectWebHooks.setWebhook_url("https:///story/show/555");
        apiRequest = requestBuilder.endpoint("projects/{project_id}/webhooks/{webhook_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("webhook_id", testWebHook.getId().toString())
                .body(new ObjectMapper().writeValueAsString(projectWebHooks))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        testWebHook = apiResponse.getBody(ProjectWebHooks.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(testWebHook.getWebhook_url(), "https:///story/show/555");
    }

    @Test(groups = {"putRequests", "createProject"})
    public void updateAProjectWebHookAndCompareUrlTest() throws JsonProcessingException {
        ProjectWebHooks projectWebHooks = new ProjectWebHooks();
        projectWebHooks.setWebhook_url("https:///story/show/555");
        apiRequest = requestBuilder.endpoint("projects/{project_id}/webhooks/{webhook_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("webhook_id", testWebHook.getId().toString())
                .body(new ObjectMapper().writeValueAsString(projectWebHooks))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        testWebHook = apiResponse.getBody(ProjectWebHooks.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertNotEquals(testWebHook.getWebhook_url(), "https:///story/show/561");
    }

    @Test(groups = {"deleteRequests", "createProject"})
    public void deleteAProjectWebHookTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/webhooks/{webhook_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("webhook_id", testWebHook.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    @Test(groups = {"deleteRequests", "createProject"})
    public void deleteAProjectWebHookWithWrongIdTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/webhooks/{webhook_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("webhook_id", testWebHook.getId().toString() + "0")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

}
