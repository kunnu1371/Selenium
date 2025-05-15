package crud;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class UpdateUser {

	@Test
	public void createUser(ITestContext context) {
		System.out.println("Update User...........................");
		Faker fake = new Faker();
		JSONObject reqBody = new JSONObject();
		reqBody.put("status", "active");

		String bearerToken = "2e53eeafa4fc799e66936353bb339c846e1dca19ed46f2d86625878431f35081";
		
		int id = (Integer) context.getSuite().getAttribute("id");
		given()
		.headers("Authorization", "Bearer " + bearerToken)
		.pathParam("id", id)
		.contentType("application/json")
		.body(reqBody)
		.when()
		.put("https://gorest.co.in/public/v2/users/{id}").then().statusCode(200).log().all();
	}
}
