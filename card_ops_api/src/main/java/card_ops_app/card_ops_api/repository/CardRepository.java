package card_ops_app.card_ops_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import card_ops_app.card_ops_api.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}