package org.gcs.cassandra.service;

import java.util.List;
import java.util.UUID;

import org.gcs.cassandra.entity.Bird;
import org.gcs.cassandra.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BirdService {

	@Autowired
	BirdRepository birdRepo;

	public Bird save(Bird bird) {
		return birdRepo.save(bird);
	}

	public List<Bird> getAll() {
		return (List<Bird>) birdRepo.findAll();
	}

	public Bird findByBirdId(UUID theId) {
		return birdRepo.findByBirdId(theId);
	}

	public void delete(Bird theBird) {
		birdRepo.delete(theBird);
	}

	public String searchedBirds(String theLocation, String theScanDay) throws JsonProcessingException {
		String foundBirds = "";
		ObjectMapper mapper = new ObjectMapper();

		List<Bird> birds = (List<Bird>) birdRepo.findAll();
		String location;
		String day;
		for (Bird bird : birds) {
			location = bird.getLocation();
			day = bird.getScanDay();
			if (location.equalsIgnoreCase(theLocation) && day.equalsIgnoreCase(theScanDay)) {
				foundBirds = mapper.writeValueAsString(bird);
			}
		}
		return foundBirds;
	}
}
