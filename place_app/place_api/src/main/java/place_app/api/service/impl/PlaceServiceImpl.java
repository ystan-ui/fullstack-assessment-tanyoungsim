package place_app.api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import place_app.api.dto.PlaceDTO;
import place_app.api.entity.Place;
import place_app.api.repository.PlaceRepository;
import place_app.api.service.PlaceService;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

	private static final Logger log = LoggerFactory.getLogger(PlaceService.class);

	@Autowired
	private PlaceRepository placeRepository;

	@Override
	public void saveFavourite(PlaceDTO dto) {
		log.info("Start saveFavourite : " + dto);
		Place place = new Place();
		place.setPlaceId(dto.getPlaceId());
		place.setName(dto.getName());
		place.setAddress(dto.getAddress());
		place.setLat(dto.getLat());
		place.setLng(dto.getLng());
		Place savedPlace = placeRepository.save(place);
		log.info("Success saveFavourite : " + savedPlace);
	}

	@Override
	public List<PlaceDTO> getAllFavourites() {
		return placeRepository.findAll().stream()
				.map(p -> new PlaceDTO(p.getPlaceId(), p.getName(), p.getAddress(), p.getLat(), p.getLng()))
				.collect(Collectors.toList());
	}

	@Override
	public void removeFavourite(String placeId) {
		log.info("Start removeFavourite : " + placeId);
		if (placeRepository.existsByPlaceId(placeId)) {
			placeRepository.deleteByPlaceId(placeId);
			log.info("Success removeFavourite : " + placeId);
		} else {
			log.info("Failed to removeFavourite : " + placeId);
			throw new RuntimeException("Place not found for ID: " + placeId);
		}
	}
}
