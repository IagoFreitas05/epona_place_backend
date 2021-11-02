package main.place.repository;

import main.place.entity.EntidadeDominio;
import main.place.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
    public List<PurchaseOrder> findByIdUserOrderByIdDesc(Integer id);
    public List<PurchaseOrder> findPurchaseOrderByStatus(String status);
}
