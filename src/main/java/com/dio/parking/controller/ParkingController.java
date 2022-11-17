package com.dio.parking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dio.parking.controller.dto.ParkingCreateDTO;
import com.dio.parking.controller.dto.ParkingDTO;
import com.dio.parking.controller.mapper.ParkingMapper;
import com.dio.parking.models.Parking;
import com.dio.parking.service.ParkingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;

	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService = parkingService;
		this.parkingMapper = parkingMapper;
	}

	@GetMapping
	@ApiOperation("Find all parkings")
	public ResponseEntity<List<ParkingDTO>> findAll() {
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	@ApiOperation("Find parking by id")
	public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
		Parking parking = parkingService.findById(id);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.ok(result);
	}

	@PostMapping
	@ApiOperation("Post new parking")
	public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
		var parkingCreate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.create(parkingCreate);
		ParkingDTO result = parkingMapper.toParkingDTO(parking);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
}
