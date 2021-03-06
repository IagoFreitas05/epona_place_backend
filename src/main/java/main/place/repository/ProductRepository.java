package main.place.repository;

import main.place.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByIdManager(Integer idManager);
    List<Product> findByStatus(String status);
}
