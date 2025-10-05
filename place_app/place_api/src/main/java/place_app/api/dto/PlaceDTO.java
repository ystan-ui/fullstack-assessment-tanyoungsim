package place_app.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceDTO {
	private String placeId;
	private String name;
	private String address;
	private Double lat;
	private Double lng;
}