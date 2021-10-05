package main.place.repository;

import main.place.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
    public List<PurchaseOrder> findByIdUser(Integer id);
    public List<PurchaseOrder> findPurchaseOrderByStatus(String status);
}
