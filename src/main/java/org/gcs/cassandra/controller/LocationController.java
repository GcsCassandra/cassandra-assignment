package org.gcs.cassandra.controller;

import java.util.UUID;

import org.gcs.cassandra.entity.ScanLocation;
import org.gcs.cassandra.repository.LocationRepository;
import org.gcs.cassandra.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LocationController {

	@Autowired
	LocationRepository locationRepo;

	@Autowired
	LocationService locationService;

	public LocationController() {

	}

	@GetMapping("/location/list")
	public String listLocations(Model model) {
		model.addAttribute("locations", locationRepo.findAll());
		return "locations/list-locations";
	}

	@GetMapping("/location/new")
	public String displayLocationForm(Model model) {
		ScanLocation scanLocation = new ScanLocation();
		model.addAttribute("scanLocation", scanLocation);
		return "locations/new-location";
	}

	@PostMapping("/location/save")
	public String createLocation(ScanLocation scanLocation, Model model) {
		// save to the database using an location CRUD repository
		locationRepo.save(scanLocation);

		// use a redirect to prevent duplicated submissions
		return "redirect:/location/new";
	}

	@GetMapping("/location/update")
	public String displayLocationUpdateForm(@RequestParam("locationId") UUID locationId, Model model) {
		ScanLocation theLocation = locationService.findByLocationId(locationId);
		model.addAttribute("scanLocation", theLocation);
		return "locations/new-location";
	}

	@GetMapping("/location/delete")
	public String deleteLocation(@RequestParam("locationId") UUID locationId, Model model) {
		ScanLocation theLocation = locationService.findByLocationId(locationId);
		locationService.delete(theLocation);
		return "birds/list-birds";
	}

}
