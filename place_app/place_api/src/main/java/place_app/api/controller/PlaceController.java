package place_app.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import place_app.api.dto.PlaceDTO;
import place_app.api.service.PlaceService;

@RestController
@RequestMapping("/places")
@CrossOrigin(origins = "http://localhost:3000")
public class PlaceController {

	private static final Logger log = LoggerFactory.getLogger(PlaceController.class);

	@GetMapping("/test")
	public String test() {
		return "Place APP is up!";
	}

	@Autowired
	private PlaceService placeService;

	@PostMapping("/favourite")
	public ResponseEntity<?> saveFavourite(@RequestBody PlaceDTO place) {
		log.info("Incoming saveFavourite : " + place);
		placeService.saveFavourite(place);
		log.info("Successful saveFavourite ! ");
		return ResponseEntity.ok("Favourite saved!");
	}

	@GetMapping("/favourite")
	public ResponseEntity<List<PlaceDTO>> getAllFavourites() {
		log.info("Incoming getAllFavourites. ");
		List<PlaceDTO> placeDTOs = placeService.getAllFavourites();
		log.info("Successful getAllFavourites : " + placeDTOs);
		return ResponseEntity.ok(placeDTOs);
	}

	@DeleteMapping("/favourite/{placeId}")
	public ResponseEntity<?> removeFavourite(@PathVariable String placeId) {
		log.info("Incoming removeFavourite for placeId: {}", placeId);
		try {
			placeService.removeFavourite(placeId);
			log.info("Successful removeFavourite for placeId: {}", placeId);
			return ResponseEntity.ok("Favourite removed successfully!");
		} catch (RuntimeException e) {
			log.error("Failed to removeFavourite for placeId: {}", placeId, e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
