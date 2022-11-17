package com.dio.parking.models;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Parking {
	private String id;
	private String license;
	private String state;
	private String model;
	private String color;
	private LocalDateTime entryDate;
	private LocalDateTime exitDate;
	private String bill;
	
	public Parking(String id, String license, String state, String model, String color) {
		this.id = id;
		this.license = license;
		this.state = state;
		this.model = model;
		this.color = color;
	}
}
