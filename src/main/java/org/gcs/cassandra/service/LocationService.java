package org.gcs.cassandra.service;

import java.util.List;
import java.util.UUID;

import org.gcs.cassandra.entity.ScanLocation;
import org.gcs.cassandra.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LocationService {

	@Autowired
	LocationRepository locationRepo;

	public ScanLocation save(ScanLocation loc) {
		return locationRepo.save(loc);
	}

	public List<ScanLocation> getAll() {
		return (List<ScanLocation>) locationRepo.findAll();
	}

	public String searchedLocations(String theLocation) throws JsonProcessingException {
		String foundlocations = "";
		ObjectMapper mapper = new ObjectMapper();

		List<ScanLocation> scanLocations = (List<ScanLocation>) locationRepo.findAll();

		String location;

		for (ScanLocation loc : scanLocations) {
			location = loc.getLocation();

			if (location.equalsIgnoreCase(theLocation)) {
				foundlocations = mapper.writeValueAsString(loc);
			}
		}
		return foundlocations;
	}

	public void delete(ScanLocation theLocation) {
		locationRepo.delete(theLocation);
	}

	public ScanLocation findByLocationId(UUID id) {

		return locationRepo.findByLocationId(id);
	}

}
