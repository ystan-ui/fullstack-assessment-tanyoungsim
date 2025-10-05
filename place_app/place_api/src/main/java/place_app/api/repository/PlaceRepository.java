package place_app.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import place_app.api.entity.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

	@Transactional
    @Modifying
	@Query("DELETE FROM Place p WHERE p.placeId = :placeId")
    void deleteByPlaceId(@Param("placeId") String placeId);

	boolean existsByPlaceId(String placeId);
}