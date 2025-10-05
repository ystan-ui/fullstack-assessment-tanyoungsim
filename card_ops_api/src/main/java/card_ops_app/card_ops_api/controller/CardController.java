package card_ops_app.card_ops_api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import card_ops_app.card_ops_api.dto.CardDTO;
import card_ops_app.card_ops_api.dto.CreateCardRequest;
import card_ops_app.card_ops_api.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

	private static final Logger log = LoggerFactory.getLogger(CardController.class);

	@Autowired
	private CardService cardService;

	@GetMapping("/test")
	public String test() {
		return "Card API is up!";
	}

	// INSERT
	@PostMapping
	public ResponseEntity<CardDTO> createCard(@Valid @RequestBody CreateCardRequest request) {
		log.info("Incoming CreateCard request: {}", request);
		CardDTO response = cardService.createCard(request);
		log.info("Outgoing CreateCard response: {}", response);
		return ResponseEntity.ok(response);
	}

	// GET with pagination
	@GetMapping
	public ResponseEntity<Page<CardDTO>> getAllCards(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		log.info("Fetching cards with pageable: {}", PageRequest.of(page, size));
		Page<CardDTO> cardDTOs = cardService.getAllCards(page, size);
		log.info("Returning {} cards", cardDTOs.getNumberOfElements());
		return ResponseEntity.ok(cardDTOs);
	}

	// External API call (nested API example)
	@GetMapping("/external")
	public ResponseEntity<CardDTO> getExternalApiData(@RequestParam(required = false) String apiKey) {
		log.info("Fetching external API data with apiKey:: {}", apiKey);
		CardDTO response = cardService.callExternalApi(apiKey);
		log.info("Returning card data: {}", response);
		return ResponseEntity.ok(response);
	}
}
