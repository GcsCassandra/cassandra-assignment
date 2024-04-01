package org.gcs.cassandra.controller;

import org.gcs.cassandra.entity.Bird;
import org.gcs.cassandra.entity.ScanLocation;
import org.gcs.cassandra.repository.BirdRepository;
import org.gcs.cassandra.repository.LocationRepository;
import org.gcs.cassandra.service.BirdService;
import org.gcs.cassandra.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class SearchController {

	@Autowired
	BirdRepository birdRepo;

	@Autowired
	LocationRepository scanLocationRepo;

	@Autowired
	private BirdService birdService;

	@Autowired
	private LocationService locationService;

	@PostMapping(path = "/scan/result", produces = "application/json")
	public ResponseEntity<Bird> sendBirdResult(@RequestBody Bird bird) {

		Bird addedBird = birdRepo.save(bird);

		return new ResponseEntity<>(addedBird, HttpStatus.CREATED);
	}

	@PostMapping(path = "/scan/location", produces = "application/json")
	public ResponseEntity<ScanLocation> sendData(@RequestBody ScanLocation scanLocation) {

		ScanLocation addedLocation = scanLocationRepo.save(scanLocation);

		return new ResponseEntity<>(addedLocation, HttpStatus.CREATED);
	}

	@GetMapping("/scan/result/location/{location}/day/{day}")
	public ResponseEntity<String> searchBirds(@PathVariable("location") String birdLocation,
			@PathVariable("day") String scanDate) {
		try {
			String searchResults = birdService.searchedBirds(birdLocation, scanDate);

			if (searchResults == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No birds found for the given parameters.");
			}

			return ResponseEntity.ok(searchResults);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error searching records: " + e.getMessage());
		}
	}

	@GetMapping("/scan/location/{location}")
	public ResponseEntity<String> searchBirds(@PathVariable("location") String scanLocation) {
		try {
			String searchLocations = locationService.searchedLocations(scanLocation);

			if (searchLocations == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No locations found for the given parameters.");
			}

			return ResponseEntity.ok(searchLocations);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error searching records: " + e.getMessage());
		}
	}
}
