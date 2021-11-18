package main.place.repository;

import io.swagger.models.auth.In;
import main.place.entity.DeliverTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliverTaxRepository extends JpaRepository<DeliverTax, Integer> {
    @Query(value = "select * from deliver_tax where QUANTITY_ITEM = :quantity", nativeQuery = true)
    public DeliverTax findDeliverTaxByQuantityItem(@Param("quantity") Integer quantity);
}
