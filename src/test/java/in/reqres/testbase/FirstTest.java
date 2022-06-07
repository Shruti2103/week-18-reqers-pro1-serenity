package in.reqres.testbase;

import in.reqres.constants.EndPoints;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static net.serenitybdd.rest.SerenityRest.then;

public class FirstTest extends TestBase {
    @Test
    public void getSingleUser() {
        SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_USERS)
               .then();


    }
}