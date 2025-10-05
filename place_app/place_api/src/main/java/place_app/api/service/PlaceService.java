package place_app.api.service;

import java.util.List;

import place_app.api.dto.PlaceDTO;

public interface PlaceService {

	void saveFavourite(PlaceDTO dto);

	List<PlaceDTO> getAllFavourites();

	void removeFavourite(String placeId);

}