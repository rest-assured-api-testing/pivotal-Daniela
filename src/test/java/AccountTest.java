import api.*;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AccountTest {
    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiRequest apiRequest = new ApiRequest();

    @BeforeTest
    public void initialConfiguration() {
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .build();
    }

    @BeforeMethod(onlyForGroups = "getRequests")
    public void getRequests() {
        apiRequest = requestBuilder
                .method(ApiMethod.GET)
                .build();
    }

    @Test(groups = "getRequests")
    public void getAccounts() {
        apiRequest = requestBuilder.endpoint("accounts")
                .clearParams()
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = "getRequests")
    public void getAccountSummaries() {
        apiRequest = requestBuilder.endpoint("account_summaries")
                .clearParams()
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = "getRequests")
    public void getAAccount() {
        apiRequest = requestBuilder.endpoint("accounts/{id}")
                .pathParms("id", "1155186")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
        apiResponse.validateBodySchema("schemas/account.json");
    }

    @Test(groups = "getRequests")
    public void getAWrongAccount() {
        apiRequest = requestBuilder.endpoint("accounts/{id}")
                .pathParms("id", "0000000")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test(groups = "getRequests")
    public void getAForbiddenAccount() {
        apiRequest = requestBuilder.endpoint("accounts/{id}")
                .pathParms("id", "1155100")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_FORBIDDEN);
    }

    @Test(groups = "getRequests")
    public void getAForbiddenIdAccount() {
        apiRequest = requestBuilder.endpoint("accounts/{id}")
                .pathParms("id", "aaaaaaaa")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

}
