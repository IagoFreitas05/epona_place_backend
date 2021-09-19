package main.place.repository;

import main.place.entity.Cupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuponsRepository extends JpaRepository<Cupon, Integer> {
    List<Cupon> findByIdManager(Integer id);
}
