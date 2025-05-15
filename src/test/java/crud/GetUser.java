package crud;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class GetUser {

	@Test
	public void getUser(ITestContext context) {
		System.out.println("Get User...........................");
		int id = (Integer) context.getSuite().getAttribute("id");
		String bearerToken = "2e53eeafa4fc799e66936353bb339c846e1dca19ed46f2d86625878431f35081";
		
		given()
		.headers("Authorization", "Bearer " + bearerToken)
		.pathParam("id", id)
		.contentType("application/json")
		.when()
		.get("https://gorest.co.in/public/v2/users/{id}")
		.then().statusCode(200).log().all();
		
	}
}
