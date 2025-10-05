package card_ops_app.card_ops_api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class CreatedEntity {

	@Column(updatable = false)
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column(updatable = false)
	private String createdBy = "SYSTEM";

	private LocalDateTime updatedDate = LocalDateTime.now();
	private String updatedBy = "SYSTEM";
}
