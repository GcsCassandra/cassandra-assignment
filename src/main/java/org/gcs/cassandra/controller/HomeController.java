package org.gcs.cassandra.controller;

import java.util.List;

import org.gcs.cassandra.entity.Bird;
import org.gcs.cassandra.entity.ScanLocation;
import org.gcs.cassandra.repository.BirdRepository;
import org.gcs.cassandra.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class HomeController {

	@Value("${version}")
	private String ver;

	@Autowired
	LocationRepository locationRepo;

	@Autowired
	BirdRepository birdRepo;

	@GetMapping("/")
	public String displayHomePage(Model model) throws JsonProcessingException {

		model.addAttribute("versionNumber", ver);

		List<ScanLocation> scanLocations = (List<ScanLocation>) locationRepo.findAll();
		model.addAttribute("locations", scanLocations);

		List<Bird> birds = (List<Bird>) birdRepo.findAll();
		model.addAttribute("birds", birds);
		return "main/home";
	}

}
