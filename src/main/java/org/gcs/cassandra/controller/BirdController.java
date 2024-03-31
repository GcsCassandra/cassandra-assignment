package org.gcs.cassandra.controller;

import java.util.UUID;

import org.gcs.cassandra.entity.Bird;
import org.gcs.cassandra.repository.BirdRepository;
import org.gcs.cassandra.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BirdController {

	@Autowired
	BirdRepository birdRepo;

	@Autowired
	BirdService birdService;

	public BirdController() {
	}

	@GetMapping("/bird/list")
	public String listBirds(Model model) {
		model.addAttribute("birds", birdRepo.findAll());
		return "birds/list-birds";
	}

	@GetMapping("/bird/new")
	public String displayBirdForm(Model model) {
		Bird bird = new Bird();
		model.addAttribute("bird", bird);
		return "birds/new-bird";
	}

	@PostMapping("/bird/save")
	public String createBird(Bird bird, Model model) {
		// save to the database using an employee CRUD repository
		birdRepo.save(bird);

		// use a redirect to prevent duplicated submissions
		return "redirect:/bird/new";
	}

	@GetMapping("/birds/update")
	public String displayBirdUpdateForm(@RequestParam("id") UUID theId, Model model) {
		Bird theBird = birdService.findByBirdId(theId);
		model.addAttribute("bird", theBird);
		return "birds/new-bird";
	}

	@GetMapping("/birds/delete")
	public String deleteScanBird(@RequestParam("id") UUID theId, Model model) {
		Bird theBird = birdService.findByBirdId(theId);
		birdService.delete(theBird);
		return "birds/list-birds";
	}

}
