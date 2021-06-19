import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Project;
import entities.Story;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StoryTest {

    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiRequest apiRequest = new ApiRequest();
    ApiRequest apiRequest2 = new ApiRequest();
    Project project = new Project();
    Project project2 = new Project();
    Story stories = new Story();

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
    public void createStory() throws JsonProcessingException {
        System.out.println("---------------- create story");
        Story story = new Story();
        story.setName("First story");
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("projects/{project_id}/stories")
                .method(ApiMethod.POST)
                .pathParms("project_id", project.getId().toString())
                .body(new ObjectMapper().writeValueAsString(story))
                .build();
        stories = ApiManager.executeWithBody(apiRequest).getBody(Story.class);
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
    public void getProjectStoriesTest() {
        apiRequest = requestBuilder.endpoint("/projects/{project_id}/stories")
                .clearParams()
                .pathParms("project_id", project2.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"postRequests", "createAProject"})
    public void createProjectStoryTest() throws JsonProcessingException {
        Story story = new Story();
        story.setName("First story");
        apiRequest = requestBuilder.endpoint("projects/{project_id}/stories")
                .pathParms("project_id", project2.getId().toString())
                .body(new ObjectMapper().writeValueAsString(story))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        stories = apiResponse.getBody(Story.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(stories.getName(), "First story");
    }

    @Test(groups = {"getRequests", "createProject"})
    public void getAProjectStoryActivityTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/stories/{story_id}/activity")
                .pathParms("project_id", project.getId().toString())
                .pathParms("story_id", stories.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"getRequests", "createProject"})
    public void getAProjectStoryTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/stories/{story_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("story_id", stories.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = {"putRequests", "createProject"})
    public void updateAProjectStoryTest() throws JsonProcessingException {
        Story story = new Story();
        story.setName("New story name");
        apiRequest = requestBuilder.endpoint("projects/{project_id}/stories/{story_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("story_id", stories.getId().toString())
                .body(new ObjectMapper().writeValueAsString(story))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        stories = apiResponse.getBody(Story.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(stories.getName(), "New story name");
    }

    @Test(groups = {"deleteRequests", "createProject"})
    public void deleteAEpicTest() {
        apiRequest = requestBuilder.endpoint("projects/{project_id}/stories/{story_id}")
                .pathParms("project_id", project.getId().toString())
                .pathParms("story_id", stories.getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

}
