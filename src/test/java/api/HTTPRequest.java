package api;

import org.apache.ws.commons.schema.XmlSchema;
import org.apache.xerces.impl.xs.XMLSchemaValidator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HTTPRequest {
	int id;

//	given()
//		content type, set cookies, add auth, add param, set headers, etc..
//	when()
//		set req type - get, put, post, delete
//	then()	
//		validate status code, extract response, extract data like headers, cookies, response body

	@Test(priority = 1)
	void getUsers() {
		System.out.println("getUsers-------->");
		given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2)).log()
				.all();

	}

	@Test(priority = 1)
	void getUsers2() {
		given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200)
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("users.json"));
		Response res = given().when().get("https://reqres.in/api/users?page=2");
		JSONObject response = new JSONObject(res.getBody().asString());
		System.out.println(response);
		JsonPath path = res.jsonPath();
		String name = path.get("data[0].last_name");
		System.out.println(name);

	}

	@Test(priority = 1)
	void getUsers3() {
		given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2)).and()
				.body("data[0].last_name", equalTo("Lawson"));

	}

	@Test(priority = 1)
	void getUsersValidateSchema() {
		System.out.println("getUsers-------->");		
		given().when().get("https://reqres.in/api/users?page=2").then().statusCode(200).assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("users.json")).log().all();
	}

	@Test(priority = 2)
	void createUser() {
		System.out.println("createUser-------->");
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "john doe");
		data.put("job", "serial killer");

		id = given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");
//		.then().statusCode(201).log().all();		
		System.out.println("id: " + id);
	}

	@Test(priority = 3, dependsOnMethods = "createUser")
	void updateUser() {
		System.out.println("updateUser-------->");
		HashMap<String, String> data = new HashMap();

		data.put("name", "bob");
		data.put("job", "bob");
		given().contentType("application/json").body(data).when().put("https://reqres.in/api/users/" + id).then()
				.statusCode(200).log().all();
	}

	@Test(priority = 4)
	void deleteUser() {
		System.out.println("deleteUser-------->");

		given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();
	}

//	Ways to create a request body
//	1. Hashmap
//	2. Org.json
//	3. using POJO(plain old java object)
//	4. using external json file

//	Post req body using Hashmap
	@Test
	void postAPITestWithHashMap() {
		HashMap<String, Object> reqBody = new HashMap<String, Object>();

		reqBody.put("name", "bob");
		reqBody.put("job", "bob");
		ArrayList<String> list = new ArrayList<String>();
		list.add("python");
		list.add("java, c++");
//		String skill[] = { "python", "c++", "java", "Software development" };
		reqBody.put("skills", list);

		given().body(reqBody).contentType("application/json").when().post("https://reqres.in/api/users").then()
				.statusCode(201).body("name", equalTo("bob")).body("skills[0]", equalTo("python"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();
	}

//	Post req body using org.json
	@Test
	void postAPITestWithJsonObject() {
		JSONObject reqBody = new JSONObject();

		reqBody.put("name", "bob");
		reqBody.put("job", "bob");
		String skill[] = { "python", "c++", "java", "Software development" };
		reqBody.put("skills", skill);

		given().body(reqBody).contentType("application/json").when().post("https://reqres.in/api/users").then()
				.statusCode(201).body("name", equalTo("bob")).body("skills[0]", equalTo("python"))
				.body("skills[1]", equalTo("c++")).header("Content-Type", "application/json; charset=utf-8").log()
				.all();
	}

//	Post req body using pojo
	@Test
	void postAPITestWithPOJO() {
		ReqBody body = new ReqBody();
		body.setName("bob");
		body.setId("9");
		body.setJob("mechanic");
		String skills[] = { "python", "c++", "java" };
		body.setSkills(skills);

		given().body(body).contentType("application/json").when().post("https://reqres.in/api/users").then()
				.statusCode(201).body("name", equalTo("bob")).body("job", equalTo("mechanic"))
				.body("skills[0]", equalTo("python")).body("skills[1]", equalTo("c++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();

		System.out.println("Deletion--------->");
		given().pathParam("id", "9").when().delete("https://reqres.in/api/users/{id}").then().statusCode(204).log()
				.all();

	}

//	Post req body using external json file
	@Test
	void postAPITestWithExternalJSonFile() throws IOException {
		
        String jsonBody = new String(Files.readAllBytes(Paths.get("C:\\\\Users\\\\gautam.kunal\\\\eclipse-workspace\\\\DemoSelenium\\\\DemoSelenium\\\\src\\\\test\\\\resources\\\\data.json")));

		given().body(jsonBody).contentType("application/json").when().post("https://reqres.in/api/users").then()
				.statusCode(201).body("name", equalTo("bob")).body("job", equalTo("mechanic")).and()
				.body("skills[0]", equalTo("python")).and().body("skills[1]", equalTo("c++"))
				.header("Content-Type", "application/json; charset=utf-8").log().all();

		System.out.println("Deletion--------->");
		given().pathParam("id", "9").when().delete("https://reqres.in/api/users/{id}").then().statusCode(204).log()
				.all();

	}

	@Test
	void testQueryAndPathParam() {

		given().pathParam("mypath", "users").queryParam("page", 2).queryParam("id", 9).when()
				.get("https://reqres.in/api/{mypath}").then().statusCode(200).log().all();

		System.out.println("Response--------->");

	}

	@Test(priority = 5)
	String getCookie() {

		Response res = given().when().get("https://www.google.com");

		return res.getCookie("AEC");
	}

	@Test(priority = 5)
	void getCookies() {

		Response res = given().when().get("https://www.naukri.com");

//		String cookie = res.getCookie("AEC");
		Map<String, String> cookies = res.getCookies();
		System.out.println("cookies map: " + cookies);
		Cookies detailedCookies = res.getDetailedCookies();
		System.out.println("detailedCookies: " + detailedCookies);

		String test = detailedCookies.getValue("test");
		Cookie testDetailed = detailedCookies.get("test");
		System.out.println("test: " + test);
		System.out.println("testDetailed: " + testDetailed.getValue());

		for (String k : cookies.keySet()) {
			String cookie = res.getCookie(k);
			System.out.println(k + " ======> " + cookie);

		}

	}

	@Test(priority = 5)
	void getHeaders() {

		Response res = given().when().get("https://www.naukri.com");
		System.out.println(res.getHeaders());
		Headers headers = res.getHeaders();
		for (Header hd : headers) {

			System.out.println(hd.getName() + " ======> " + hd.getValue());

		}

	}

	@Test(priority = 6)
	void testCookiesAndHeaders() {

		given().when().get("https://www.google.com").then()
				.header("Content-Type", equalTo("text/html; charset=ISO-8859-1")).and()
				.header("Cache-Control", equalTo("private, max-age=0"));
//		.cookie("AEC",getCookie());

	}

	@Test(priority = 6)
	void testLog() {

		given().when().get("https://www.google.com").then().log().cookies().and().log().headers();

	}

	@Test
	void testJSONResponseBody() throws org.json.simple.parser.ParseException {
		given().contentType("application/json").when().get("https://reqres.in/api/users").then().body("data[4].email",
				equalTo("charles.morris@reqres.in"));

		Response res = given().contentType("application/json").when().get("https://reqres.in/api/users");

		System.out.println(res.prettyPrint());
		int statusCode = res.getStatusCode();
		String first_name = res.jsonPath().getString("data[4].first_name");
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(first_name, "Charles");

		JSONObject jsonResponse = new JSONObject(res.getBody().asString());
		System.out.println(jsonResponse.getJSONArray("data"));
		JSONArray data = jsonResponse.getJSONArray("data");
		for (int i = 0; i < data.length(); i++) {
			JSONObject obj = (JSONObject) data.get(i);
			System.out.println(obj.get("first_name") + " " + obj.get("last_name"));

		}

	}

	@Test
	public void testXMLResponse() {
		given().when().get("https://mocktarget.apigee.net/xml").then().body("root.city", equalTo("San Jose"))
				.body(RestAssuredMatchers.matchesXsdInClasspath("schema-definition.xsd")).statusCode(200)
				.header("Content-Type", "application/xml; charset=utf-8").log().all();
		Response res = given().when().get("https://mocktarget.apigee.net/xml");
		String name = res.xmlPath().get("root.firstName").toString();
		Assert.assertEquals(name, "John");
		XmlPath xmlPath = res.xmlPath();
		Assert.assertEquals(xmlPath.get("root.state"), "CA");
		XmlPath path = new XmlPath(res.getBody().asString());
		System.out.println(path.get("root.lastName"));
	}

	@Test
	public void testAPI() throws ParseException {
		Response res = given().when().get("https://jsonplaceholder.typicode.com/posts");

		Assert.assertEquals(res.getStatusCode(), 200);
//		System.out.println(res.getBody().asPrettyString());
		String title = res.jsonPath().getString("[0].title");
		Assert.assertEquals(title, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");

		ArrayList list = (ArrayList) res.jsonPath().getList("");
		System.out.println(list);

		for (int i = 0; i < list.size(); i++) {
			HashMap<String, Object> map = (HashMap<String, Object>) list.get(i);
//  			System.out.println(map);
			int id = (Integer) map.get("id");
//  			System.out.println(res.jsonPath().getString("["+ i+ "]"+".id"));
			if (id == 11) {
				Assert.assertEquals(map.get("title"), "et ea vero quia laudantium autem");
			}
		}
	}
	
//	Serialization and deserialization in REST Assured refer to the process of converting Java objects to and from data formats like JSON or XML, which are commonly used in API communications.

//	Serialization
//	Serialization is the process of converting a Java object into a JSON or XML format, which can then be sent as the request body in an API call.

//	Deserialization
//	Deserialization is the process of converting JSON or XML responses from an API into Java objects for easier manipulation.
	
	
	@Test
	public void serializationDeserialization() throws JsonProcessingException {
		Student stu = new Student();
		stu.setName("Rohit");
		stu.setGender('M');
		stu.setRollno("123456");
		String[] course = { "HTML", "CSS", "Java", "Web Development" };
		stu.setCourses(course);
		System.out.println("Serialization...");
		ObjectMapper obj = new ObjectMapper();
		String jsonData = obj.writerWithDefaultPrettyPrinter().writeValueAsString(stu);
		System.out.println(jsonData);
		System.out.println("DeSerialization...");
		Student stu2 = obj.readValue(jsonData, Student.class);
		System.out.println(stu2.getName());

		String jsonData2 = "{\r\n"
				+ "  \"name\" : \"Akshaya\",\r\n"
				+ "  \"rollno\" : \"xxxx233\",\r\n"
				+ "  \"gender\" : \"F\",\r\n"
				+ "  \"courses\" : [ \"HTML\", \"CSS\", \"Java\", \"Web Development\" ]\r\n"
				+ "}";
		Student stu3 = obj.readValue(jsonData2, Student.class);
		System.out.println(stu3.getName());
		System.out.println(stu3.getCourses()[0]);

	}
	

//	Authentication - Verifies the identity of a user or system.
//	Authorization - Determines what resources or actions the authenticated user can access.
	
//	Types of Auth - Basic, Digest, Preemptive, Oauth1.0/2.0, Bearer token
	
	@Test
	public void testBasicAuth() {
		given().auth().basic("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true)).log().all();
	}
	
	@Test
	public void testDigestAuth() {
		given().auth().digest("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true)).log().all();
	}
	
	@Test
	public void testPreemptiveAuth() {
		given().auth().preemptive().basic("postman", "password").when().get("https://postman-echo.com/basic-auth")
				.then().statusCode(200).body("authenticated", equalTo(true)).log().all();
	}
	
	@Test
	public void testBearerTokenAuth() {
		String bearerToken = "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";
		given().headers("Authorization", "Bearer " + bearerToken).when().get("https://api.github.com/user/repos").then()
				.statusCode(200).log().all();
	}
	
	@Test
	public void testOAuth1Auth() {
		given().auth().oauth(DEFAULT_URI, DEFAULT_SESSION_ID_VALUE, DEFAULT_PATH, DEFAULT_BODY_ROOT_PATH).when().get("https://api.github.com/user/repos").then()
				.statusCode(200).log().all();
	}
	
	@Test
	public void testAPIKeyAuth() {
		given().queryParam("q", "Delhi").and().queryParam("appid", "24facefa4b51c32258ee4dbb5cd02fb9").when()
				.get("https://api.openweathermap.org/data/2.5/weather").then().statusCode(200).log().all();
	}
	
	@Test
	public void testGenerateDummyData() {
		Faker faker = new Faker();

		String name = faker.name().fullName(); // Miss Samanta Schmidt
		String firstName = faker.name().firstName(); // Emory
		String lastName = faker.name().lastName(); // Barton

		String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
		String phone = faker.phoneNumber().phoneNumber();
		System.out.println(name);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(streetAddress);
		System.out.println(phone);
	}


	@Test
	public void test2API() {
		Response res = given().header("Accept", "application/json").when().get("https://broking-identity-api.stablemoney.in/v1/bond-details/51b6cf3c-3f5b-49ab-a093-26bc60b9c680");
		Assert.assertEquals(res.getStatusCode(), 200);
		System.out.println(res.getBody().prettyPrint());
		Assert.assertEquals(res.jsonPath().getString("bond_details.about_the_institution.title"), "Government of Maharashtra");
		Assert.assertEquals(res.jsonPath().getString("bond_details.about_the_institution.sector"), "financial services");
		
//		JSONObject responseJson = new JSONObject(res.getBody().toString());
//		System.out.println(responseJson);
	}
}
