import api.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProjectsTest {

    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiRequest apiRequest = new ApiRequest();

    @BeforeTest
    public void createProjects() {
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .method(ApiMethod.GET)
                .build();
    }

    @Test
    public void getMe() {
        apiRequest = requestBuilder.endpoint("/me")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void getAllProjectTest() {
        apiRequest = requestBuilder.endpoint("/projects")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void getProjectTest() {
        apiRequest = requestBuilder.endpoint("/projects/{projectId}")
                .method(ApiMethod.GET)
                .pathParms("projectId", "2504456")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        apiResponse.validateBodySchema("schemas/project.json");
    }
}
