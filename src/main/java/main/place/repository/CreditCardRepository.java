package main.place.repository;

import main.place.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
    List<CreditCard> findByIdUser(Integer idUser);
}
