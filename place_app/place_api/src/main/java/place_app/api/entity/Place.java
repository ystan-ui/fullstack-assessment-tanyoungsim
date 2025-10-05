package place_app.api.entity;

import jakarta.persistence.Column;
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
@Table(name = "place")
@Data
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Place extends CreatedEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "place_id", nullable = false)
	private String placeId;

	private String name;
	private String address;
	private Double lat;
	private Double lng;

	public static Object builder() {
		// TODO Auto-generated method stub
		return null;
	}
}
