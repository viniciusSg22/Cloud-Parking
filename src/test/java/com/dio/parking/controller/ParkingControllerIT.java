package com.dio.parking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.dio.parking.controller.dto.ParkingCreateDTO;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerIT {

	@LocalServerPort
	private int randomPort;

	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	};

	@Test
	void whenFindAllThenCheckResult() {
		RestAssured.given().when().get("/parking").then().statusCode(HttpStatus.OK.value());
	}

	@Test
	void whenCreateThenCheckIsCreated() {
		var createDTO = new ParkingCreateDTO();
		createDTO.setColor("Roxo");
		createDTO.setLicense("YXW-9Z87");
		createDTO.setModel("Mercedes");
		createDTO.setState("CE");
		RestAssured.given().when().contentType(MediaType.APPLICATION_JSON_VALUE).body(createDTO).post("/parking").then()
				.statusCode(HttpStatus.CREATED.value()).body("license", Matchers.equalTo("YXW-9Z87"))
				.body("color", Matchers.equalTo("Roxo"));
	}
}
