package code;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class Basic {

	 String placeId;


//	public static void main(String[] args)
	@Test
	public void extractPlaceIdTest()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";

		String response=given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(payload.payload1()).when().post("/maps/api/place/add/json")
		.then().statusCode(200).body("scope", equalTo("APP"))
				.header("server","Apache/2.4.18 (Ubuntu)").extract().response().asString();
		System.out.println("Response is " +response);

		JsonPath js= new JsonPath(response);
		placeId=js.getString("place_id");
		System.out.println("place id is :- " +placeId);


}
    @Test
	public void updatePlaceIdTest()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
				.body("{\r\n" +
						"  \"place_id\": \""+placeId+"\",\r\n" +
						"  \"address\": \"29, abc, cohen 09\",\r\n" +
						"  \"key\": \"qaclick123\"\r\n" +
						"}"

				).when().put("/maps/api/place/update/json")
				.then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

	}

	@Test
	public void validateNewPlaceTest()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().queryParam("key","qaclick123").queryParam("place_id",placeId)
				.when().get("/maps/api/place/get/json")
				.then().assertThat().statusCode(200).extract().response().asString();

		JsonPath js= new JsonPath(response);
		String address=js.getString("address");
//		System.out.println(address);
		Assert.assertEquals(address,"29, abc, cohen 09");
	}


}
