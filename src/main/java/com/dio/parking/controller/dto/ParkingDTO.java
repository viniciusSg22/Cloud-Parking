package com.dio.parking.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingDTO {
	private String id;
	private String license;
	private String state;
	private String model;
	private String color;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime entryDate;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime exitDate;
	private Double bill;
}
