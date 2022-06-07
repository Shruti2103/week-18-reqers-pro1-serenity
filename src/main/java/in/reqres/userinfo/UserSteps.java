package in.reqres.userinfo;






import in.reqres.constants.EndPoints;
import in.reqres.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

/**
 * Created by Jay
 */
public class UserSteps {

    @Step("Creating User with name : {0}, job: {1}, email: {2}")
    public ValidatableResponse createUser(String name, String job, String email
                                           ) {
       UserPojo userPojo= new UserPojo();
       userPojo.setName(name);
        userPojo.setJob(job);
        userPojo.setEmail(email);



        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_SINGLE_USERS)
                .then();

    }

    @Step("Getting the user information with name: {0}")
    public HashMap<Object, Object> getUserInfoByID(int usersID) {

        HashMap<Object, Object> userMap = SerenityRest.given().log().all()
                .when()
                .pathParam("usersID",usersID)
                .get(EndPoints.GET_SINGLE_USERS_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");

        return userMap;
    }

    @Step("Updating User information with usersID:  {0}, name: {1}, job: {2}, email: {3} ")
    public ValidatableResponse updateUser(int usersID, String name, String job, String email){

        UserPojo userPojo= new UserPojo();
        userPojo.setName(name);
        userPojo.setJob(job);
        userPojo.setEmail(email);


        return SerenityRest.given().log().all()
                .pathParam("usersID",usersID)
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USERS_BY_ID)
                .then();

    }

    @Step("Deleting user information with userID: {0}")
    public ValidatableResponse deleteUser(int usersID){
        return SerenityRest.given().log().all()
                .pathParam("usersID", usersID)
                .when()
                .delete(EndPoints.DELETE_USERS_BY_ID)
                .then();
    }

    @Step("Getting user information with userId: {0}")
    public ValidatableResponse getusertById(int usersID){
        return SerenityRest.given().log().all()
                .pathParam("usersID", usersID)
                .when()
                .get(EndPoints.GET_SINGLE_USERS_BY_ID)
                .then();
    }

}
