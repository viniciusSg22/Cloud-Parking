package com.dio.parking.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParkingCreateDTO {
	private String license;
	private String state;
	private String model;
	private String color;
}
