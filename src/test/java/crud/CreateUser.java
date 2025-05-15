package crud;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class CreateUser {

	@Test
	public void createUser(ITestContext context) {
		System.out.println("Create User...........................");
		Faker fake = new Faker();
		JSONObject reqBody = new JSONObject();
		reqBody.put("name", fake.name().fullName());
		reqBody.put("gender", "Male");
		reqBody.put("email", fake.internet().emailAddress());
		reqBody.put("status", "inactive");

		System.out.println("reqBody: "+reqBody);
		String bearerToken = "2e53eeafa4fc799e66936353bb339c846e1dca19ed46f2d86625878431f35081";
		
		int id = given()
		.headers("Authorization", "Bearer " + bearerToken)
		.contentType("application/json")
		.body(reqBody)
		.when()
		.post("https://gorest.co.in/public/v2/users").jsonPath().getInt("id");
		context.getSuite().setAttribute("id", id);
	}
}
