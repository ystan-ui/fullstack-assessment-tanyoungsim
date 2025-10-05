package card_ops_app.card_ops_api.service;

import org.springframework.data.domain.Page;

import card_ops_app.card_ops_api.dto.CardDTO;
import card_ops_app.card_ops_api.dto.CreateCardRequest;
import card_ops_app.card_ops_api.entity.Card;

public interface CardService {

	Card saveCard(Card card);

	CardDTO createCard(CreateCardRequest request);

	Page<CardDTO> getAllCards(int page, int size);

	CardDTO callExternalApi(String apiKey);

}