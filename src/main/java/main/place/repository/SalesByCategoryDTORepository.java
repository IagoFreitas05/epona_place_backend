package main.place.repository;

import main.place.dto.SalesByCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface SalesByCategoryDTORepository  extends JpaRepository<SalesByCategoryDTO, Integer> {

    @Query(value = "select category.id as id, COUNT(category.id) as quantity, category.category from category\n" +
            "    INNER JOIN product p on category.id = p.category\n" +
            "    INNER JOIN order_item oi on oi.id_produto = p.id\n" +
            "    GROUP BY category.category\n", nativeQuery = true)
     List<SalesByCategoryDTO>  findSalesByCategory();
}
