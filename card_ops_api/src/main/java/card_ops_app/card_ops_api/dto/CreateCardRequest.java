package card_ops_app.card_ops_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CreateCardRequest {
	@NotBlank(message = "Card number is required")
	@Size(min = 13, max = 19, message = "Card number must be between 13 and 19 digits")
	@Pattern(regexp = "\\d+", message = "Card number must contain only digits")
	private String cardNumber;

	@NotBlank(message = "Cardholder name is required")
	private String cardHolderName;

	@NotBlank(message = "Expiry date is required (format: MMYY)")
	@Pattern(regexp = "\\d{4}", message = "Expiry date must be MMYY format")
	private String expiryDate;

	@NotBlank(message = "CVV is required")
	@Size(min = 3, max = 4, message = "CVV must be 3 or 4 digits")
	private String cvv;
}
