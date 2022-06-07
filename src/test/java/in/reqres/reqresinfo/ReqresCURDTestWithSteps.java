package in.reqres.reqresinfo;




import in.reqres.testbase.TestBase;
import in.reqres.userinfo.UserSteps;
import in.reqres.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

/**
 * Created by Jay
 */
@RunWith(SerenityRunner.class)
public class ReqresCURDTestWithSteps extends TestBase {

    static String name = "morpheus" + TestUtils.getRandomValue();
    static String job = "leader" + TestUtils.getRandomValue();
    static String email= "zarmartri@gmail.com" + TestUtils.getRandomValue();

   static int usersID;

    @Steps
    UserSteps userSteps;


    @Title("This will create a new user")
    @Test
    public void test001() {

        ValidatableResponse response = userSteps.createUser(name,job,email);
        response.log().all().statusCode(201);
        usersID=response.log().all().extract().path("id");
        System.out.println(usersID);
    }

    @Title("Verify if the user was added to the application")
    @Test
    public void test002() {
        HashMap<Object, Object> userMap = userSteps.getUserInfoByID(usersID);
        Assert.assertThat(userMap, hasValue(name));
        System.out.println(userMap);
    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {

        name = name + "_updated";

        userSteps.updateUser(usersID,name,job,email
              ).log().all().statusCode(200);

        HashMap<Object, Object> userMap = userSteps.getUserInfoByID(usersID);
        Assert.assertThat(userMap, hasValue(name));

    }

    @Title("Delete the product and verify if the student is deleted!")
    @Test
    public void test004() {
        userSteps.deleteUser(usersID).statusCode(200);
        userSteps.getusertById(usersID).statusCode(404);
    }

}
