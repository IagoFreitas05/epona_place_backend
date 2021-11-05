package main.place.repository;

import main.place.dto.ProductsSaleQuantityDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsSaleQuantityDTORepository extends JpaRepository<ProductsSaleQuantityDTO, Integer> {
    @Query(value = "select product.id as id, COUNT(product.id) as quantity, product.name from product\n" +
            "    INNER JOIN order_item oi on oi.id_produto = product.id\n" +
            "    INNER JOIN category c on c.id = product.category\n" +
            "    GROUP BY product.name\n" +
            "\n", nativeQuery = true)

    public List<ProductsSaleQuantityDTO> findProductBySaleQuantity();
}
