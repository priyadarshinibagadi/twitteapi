package com.TwitterAPIProject.goodpractice;

import org.testng.annotations.Test;

import com.TwitterAPIProject.commonMethods.CommonFunctions;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RetrieveTweets {
	String ConsumerKey = "RZ8qVeAYpQMjoXkLsoHkL8Y11";
	String ConsumerSecret = "oCRROaf6YDYyX1f7LlHS6luG1uMeQ6Tww6JA0LZsEQ1TRjk6b5";
	String AccessToken = "1131244144179982336-Q9Pu0x69amVgVDhNLOVUKgJofMVASc";
	String TokenSecret = "XZX0RLB0RzOIV6gEhjn5iZ5G2rSPgMUAUF9a9jD8Thuey";
	@Test
	public void getTweets(){
		CommonFunctions cf = new CommonFunctions();
		RestAssured.baseURI = "https://api.twitter.com/1.1";
		Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret)
		.when()
		.get("/statuses/home_timeline.json")
		.then().assertThat().statusCode(200).and().extract().response();
		JsonPath jp = cf.commonMethodsForJson(res);
		
		String tweet =jp.get("text").toString();
		System.out.println(tweet);
		
		String id = jp.get("id").toString();
		System.out.println(id);
		
		
		}


}
