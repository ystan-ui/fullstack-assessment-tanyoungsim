package card_ops_app.card_ops_api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import card_ops_app.card_ops_api.dto.CardDTO;
import card_ops_app.card_ops_api.dto.CreateCardRequest;
import card_ops_app.card_ops_api.entity.Card;
import card_ops_app.card_ops_api.repository.CardRepository;
import card_ops_app.card_ops_api.service.CardService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

	private static final Logger log = LoggerFactory.getLogger(CardService.class);

	@Autowired
	private CardRepository cardRepository;
	private final RestTemplate restTemplate = new RestTemplate();

	@Value("${external.api.url}")
	private String externalApiUrl;

	@Override
	@Transactional
	public Card saveCard(Card card) {
		return cardRepository.save(card);
	}

	@Override
	@Transactional
	public CardDTO createCard(CreateCardRequest request) {
		Card card = new Card();
		card.setCardNumber(request.getCardNumber());
		card.setCardHolderName(request.getCardHolderName());
		card.setExpiryDate(request.getExpiryDate());
		card.setCvv(request.getCvv());

		Card saved = cardRepository.save(card);

		return new CardDTO(saved.getCardNumber(), saved.getCardHolderName(), saved.getExpiryDate().toString());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CardDTO> getAllCards(int page, int size) {
		return cardRepository.findAll(PageRequest.of(page, size))
				.map(card -> new CardDTO(card.getCardNumber(), card.getCardHolderName(), card.getExpiryDate()));
	}

	@Override
	public CardDTO callExternalApi(String apiKey) {
		String url = externalApiUrl;

		if (apiKey != null && !apiKey.isEmpty()) {
			url = externalApiUrl.contains("?") ? externalApiUrl + "&key=" + apiKey : externalApiUrl + "?key=" + apiKey;
		}

		try {
			ResponseEntity<CardDTO> response = restTemplate.getForEntity(url, CardDTO.class);

			// Check if API returned an error instead of a real card
			if (response.getBody() == null) {
				log.error("External API Error return fallback Card. ");
			}

			// Simple check for "Invalid API Key" if API sends it as a JSON string
			if (response.getBody().getCardHolderName() == null && response.toString().contains("Invalid API Key")) {
				log.error("Invalid API Key, return fallback Card. ");
			}

			return response.getBody();

		} catch (Exception e) {
			log.error("External API failed: " + e.getMessage());
			return getFallbackCard();
		}
	}

	private CardDTO getFallbackCard() {
		CardDTO fallback = new CardDTO();
		fallback.setCardNumber("4000111122223333");
		fallback.setCardHolderName("Fallback User");
		fallback.setExpiryDate("0000");
		return fallback;
	}

}
