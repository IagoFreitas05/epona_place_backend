package main.place.repository;

import io.swagger.models.auth.In;
import main.place.entity.DeliverTax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliverTaxRepository extends JpaRepository<DeliverTax, Integer> {
    DeliverTax findDeliverTaxByQuantityItem(Integer quantity);
}
