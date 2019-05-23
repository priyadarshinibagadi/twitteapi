package com.TwitterAPIProject.goodpractice;

import org.testng.annotations.Test;

import com.TwitterAPIProject.commonMethods.CommonFunctions;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UpdateTweets {
	 String ConsumerKey = "RZ8qVeAYpQMjoXkLsoHkL8Y11";
	 String ConsumerSecret = "oCRROaf6YDYyX1f7LlHS6luG1uMeQ6Tww6JA0LZsEQ1TRjk6b5";
	 String AccessToken = "1131244144179982336-Q9Pu0x69amVgVDhNLOVUKgJofMVASc";
	 String TokenSecret = "XZX0RLB0RzOIV6gEhjn5iZ5G2rSPgMUAUF9a9jD8Thuey";
	 String tweetid ;
	@Test
	public  void addTweets(){
		CommonFunctions cf = new CommonFunctions();
		RestAssured.baseURI = "https://api.twitter.com/1.1";
		Response res = given().
		queryParam("status","hello this is my tweet using automation").auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret)
		.when().post("/statuses/update.json")
		.then().assertThat().statusCode(200).and().extract().response();
	   JsonPath jp = cf.commonMethodsForJson(res);
	   String updatetweet = jp.get("text").toString();
	   System.out.println(updatetweet);
	   System.out.println("updated the tweet");
	   tweetid = jp.get("id").toString();
	   System.out.println(tweetid);
	   
	   
	}
	@Test
	public void deleteUpdatedTweet(){
		CommonFunctions cf = new CommonFunctions();
		
		RestAssured.baseURI = "https://api.twitter.com/1.1";
		Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret)
		.when().post("/statuses/destroy/"+tweetid+".json")
		.then().assertThat().statusCode(200).and().extract().response();
		JsonPath jp = cf.commonMethodsForJson(res);
		String textdeleted = jp.get("text").toString();
		System.out.println(textdeleted);
		System.out.println("delete the text");
		String deletetweetid = jp.get("id").toString();
		System.out.println(deletetweetid);
		
	}
}
