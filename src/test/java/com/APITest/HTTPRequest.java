package com.APITest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequest {

	int id;
	
	@Test
	void getUser(){
		
		given()
		.header("x-api-key", "reqres-free-v1")
		
		.when()
		  .get("https://reqres.in/api/users?page=2")
		
		
		.then()
		  
		  .statusCode(200)
		  .body("page",equalTo(2))
		  .log().all();
		}
	
	//-----POST request------------//
	@Test(priority=1)
	void createUser() {
		
		HashMap data = new HashMap();
		data.put("name","pavan");
		data.put("job","trainer");
		
	  id=given()
		.header("x-api-key", "reqres-free-v1")
		.contentType("application/json")
	    .body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
	/*	.then()
		.statusCode(201)
		.log().all();*/    //--commented so that we have to get an 'id' for next validation i.e. update
	}
	
	@Test(priority=2,dependsOnMethods={"createUser"})
	void updateUser() {
		HashMap data = new HashMap();
		data.put("name","pavan");
		data.put("job","Teacher");
		
		given()
		.header("x-api-key", "reqres-free-v1")
		.contentType("application/json")
	    .body(data)
	    
		.when()
		.put("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(200)
		.log().all();	
	}

	
	@Test(priority=3)
	void deleteUser() {
		
		given()
		.header("x-api-key", "reqres-free-v1")

		.when()
		.delete("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(204)
		.log().all();	
	}

	
	
	
	
	
}
