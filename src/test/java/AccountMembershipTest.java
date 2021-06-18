import api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.AccountMembership;
import entities.Person;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AccountMembershipTest {
    ApiRequestBuilder requestBuilder = new ApiRequestBuilder();
    ApiRequest apiRequest = new ApiRequest();
    AccountMembership accountMemberships = new AccountMembership();

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

    @Test(groups = "getRequests")
    public void getAccountMembership() {
        apiRequest = requestBuilder.endpoint("/accounts/{account_id}/memberships")
                .pathParms("account_id","1155186")
                .build();
        ApiResponse apiResponse = ApiManager.execute(apiRequest);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

    @Test(groups = "postRequests")
    public void createAccountMembership() throws JsonProcessingException {
        Person person = new Person();
        person.setEmail("example15@emil.com");
        person.setInitials("SCAA");
        person.setName("Danilles1");
        apiRequest = requestBuilder.endpoint("/accounts/{account_id}/memberships")
                .pathParms("account_id","1155186")
                .body(new ObjectMapper().writeValueAsString(person))
                .build();
        ApiResponse apiResponse = ApiManager.executeWithBody(apiRequest);
        apiResponse.getResponse().then().log().body();
        accountMemberships = apiResponse.getBody(AccountMembership.class);
        Assert.assertEquals(apiResponse.getStatusCode(), HttpStatus.SC_OK);
    }

}
