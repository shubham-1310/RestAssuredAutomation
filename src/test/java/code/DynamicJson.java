package code;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {


    @Test
    public void addBook(){

        RestAssured.baseURI="http://216.10.245.166";
        String response=given().log().all().header( "Content-Type","application/json").body(payload.addBook("t1esqt","48"))
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200).extract().response().asString();

        JsonPath js=ReusableMethods.rawToJson(response);
        String id=js.get("ID");
        System.out.println(id);


    }

}
