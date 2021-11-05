package main.place.repository;

import main.place.dto.ProductsByPeriodDTO;
import main.place.dto.PurchaseOrderByPeriodDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsByPeriodDTORepository  extends JpaRepository<ProductsByPeriodDTO, Integer> {
    @Query(value = "select order_item.id as id, name,\n" +
            "       count(order_item.id_produto) as quantity,\n" +
            "       DATE_FORMAT(data,'%y-%m-%d') as data\n" +
            "from order_item\n" +
            "    inner join purchase_order po on order_item.id_pedido = po.id\n" +
            "    inner join product p on order_item.id_produto = p.id\n" +
            "    group by data", nativeQuery = true)
    List<ProductsByPeriodDTO> findProductsByPeriod();
}
