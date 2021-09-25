package main.place.repository;

import main.place.entity.CardFlag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardFlagRepository extends JpaRepository<CardFlag, Integer> {
}
