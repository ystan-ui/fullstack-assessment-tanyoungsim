package card_ops_app.card_ops_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardDTO {
	public CardDTO() {
	}

	private String cardNumber;
	private String cardHolderName;
	private String expiryDate;
}
