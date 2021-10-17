package main.place.repository;

import main.place.entity.OrderItem;
import main.place.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    public List<OrderItem> findByIdPedido(Integer id);
    public List<OrderItem> findOrderItemByStatus(String status);
}
