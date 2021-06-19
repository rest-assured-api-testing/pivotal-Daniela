import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.AccountMembership;
import entities.Person;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AccountMembershipTest {
    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiRequest apiRequest = new ApiRequest();
    AccountMembership accountMemberships = new AccountMembership();
    Person person = new Person();

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

    @AfterMethod(onlyForGroups = "deleteMembership")
    public void deleteMembership() {
        ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
        apiRequest = requestBuilder.header("X-TrackerToken", "ab535e3e5e63442f37c020243e5360eb")
                .baseUri("https://www.pivotaltracker.com/services/v5")
                .endpoint("/accounts/{account_id}/memberships/{id}")
                .method(ApiMethod.DELETE)
                .pathParms("account_id","1155186")
                .pathParms("id", accountMemberships.getPerson().getId().toString())
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NO_CONTENT);
    }

    @Test(groups = "getRequests")
    public void getAccountMembership() {
        apiRequest = requestBuilder.endpoint("/accounts/{account_id}/memberships")
                .pathParms("account_id","1155186")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = "getRequests")
    public void getWrongAccountMembership() {
        apiRequest = requestBuilder.endpoint("/accounts/{account_id}/memberships")
                .pathParms("account_id","0000000")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test(groups = "getRequests")
    public void getForbiddenAccountMembership() {
        apiRequest = requestBuilder.endpoint("/accounts/{account_id}/memberships")
                .pathParms("account_id","1155100")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_FORBIDDEN);
    }

    @Test(groups = {"postRequests", "deleteMembership"})
    public void createAccountMembership() throws JsonProcessingException {
        person.setEmail("example@email.com");
        person.setInitials("MM");
        person.setName("Michael");
        apiRequest = requestBuilder.endpoint("/accounts/{account_id}/memberships")
                .pathParms("account_id","1155186")
                .body(new ObjectMapper().writeValueAsString(person))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        accountMemberships = apiResponse.getBody(AccountMembership.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

}
