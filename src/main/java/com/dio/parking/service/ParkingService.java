package com.dio.parking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dio.parking.exception.ParkingNotFoundException;
import com.dio.parking.models.Parking;
import com.dio.parking.repository.ParkingRepository;

@Service
public class ParkingService {

	@Autowired
	private ParkingRepository parkingRepository;

	/**
	 * public ParkingService(ParkingRepository parkingRepository){
	 * this.parkingRepository = parkingRepository; }
	 **/

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Parking> findAll() {
		return parkingRepository.findAll();
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Parking findById(String id) {
		return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
	}

	@Transactional
	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingRepository.save(parkingCreate);
		return parkingCreate;
	}

	@Transactional
	public void delete(String id) {
		findById(id);
		parkingRepository.deleteById(id);
	}

	@Transactional
	public Parking update(String id, Parking parkingCreate) {
		Parking parkingById = findById(id);
		parkingById.setColor(parkingCreate.getColor());
		parkingById.setState(parkingCreate.getState());
		parkingById.setModel(parkingCreate.getModel());
		parkingById.setLicense(parkingCreate.getLicense());

		parkingRepository.save(parkingById);
		return parkingById;
	}

	@Transactional
	public Parking checkOut(String id) {
		Parking parking = findById(id);
		parking.setExitDate(LocalDateTime.now());
		parking.setBill(ParkingCheckOut.getBill(parking));
		parkingRepository.save(parking);
		return parking;
	}

}
