package card_ops_app.card_ops_api.entity;

import card_ops_app.card_ops_api.util.CryptoUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cards")
@Data
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Card extends CreatedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cardHolderName;

	private String encryptedCardNumber; // store encrypted value in DB

	private String encryptedExpiryDate; // MMYY

	private String encryptedCvv;

	public void setCardNumber(String cardNumber) {
		this.encryptedCardNumber = CryptoUtil.encrypt(cardNumber);
	}

	public String getCardNumber() {
		return CryptoUtil.decrypt(this.encryptedCardNumber);
	}

	public void setExpiryDate(String expiryDate) {
		this.encryptedExpiryDate = CryptoUtil.encrypt(expiryDate);
	}

	public String getExpiryDate() {
		return CryptoUtil.decrypt(this.encryptedExpiryDate);
	}

	public void setCvv(String cvv) {
		this.encryptedCvv = CryptoUtil.encrypt(cvv);
	}

	public String getCvv() {
		return CryptoUtil.decrypt(this.encryptedCvv);
	}
}
