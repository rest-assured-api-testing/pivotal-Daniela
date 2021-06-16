import api.ApiManager;
import api.ApiMethod;
import api.ApiRequest;
import api.ApiResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProjectsTest {

    ApiRequest apiRequest = new ApiRequest();

    @BeforeTest
    public void createProjects() {
        apiRequest.addHeader("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb");
        apiRequest.setBaseUri("https://www.pivotaltracker.com/services/v5");
        apiRequest.setMethod(ApiMethod.GET);
    }

    @Test
    public void getMe() {
        apiRequest.setEndpoint("/me");
        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void getAllProjectTest() {
        apiRequest.setEndpoint("/projects");
        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
    }

    @Test
    public void getProjectTest() {
        apiRequest.setEndpoint("/projects/{projectId}");
        apiRequest.setMethod(ApiMethod.GET);
        apiRequest.addPathParam("projectId", "2504456");
        ApiResponse apiResponse = new ApiResponse(ApiManager.execute(apiRequest));
        Assert.assertEquals(apiResponse.getStatusCode(), 200);
        apiResponse.validateBodySchema("schemas/project.json");
    }
}
