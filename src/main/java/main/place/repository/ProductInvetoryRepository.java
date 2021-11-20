package main.place.repository;

import main.place.entity.ProductInvetory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInvetoryRepository extends JpaRepository<ProductInvetory, Integer> {
    List<ProductRepository> findByIdManager(Integer idManager);
    ProductInvetory findProductInvetoryByIdProduct(Integer idProduct);
}
