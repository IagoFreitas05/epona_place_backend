package main.place.repository;

import main.place.dto.ProductsByPeriodDTO;
import main.place.dto.PurchaseOrderByPeriodDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsByPeriodDTORepository  extends JpaRepository<ProductsByPeriodDTO, Integer> {
    @Query(value = "select order_item.id as id, name,\n" +
            "       count(order_item.id_produto) as quantity,\n" +
            "       DATE_FORMAT(data,'%Y-%m-%d') as data\n" +
            "from order_item\n" +
            "    inner join purchase_order po on order_item.id_pedido = po.id\n" +
            "    inner join product p on order_item.id_produto = p.id\n" +
            "    group by DATE_FORMAT(data,'%Y-%m-%d')", nativeQuery = true)
    List<ProductsByPeriodDTO> findProductsByPeriod();

    @Query(value = "SELECT order_item.id as id, name,\n" +
            "       count(order_item.id_produto) as quantity,\n" +
            "       DATE_FORMAT(data,'%Y-%m-%d') as data\n" +
            "FROM order_item\n" +
            "    inner join purchase_order po on order_item.id_pedido = po.id\n" +
            "    inner join product p on order_item.id_produto = p.id\n" +
            " WHERE DATE_FORMAT(data,'%Y-%m-%d') > :startPeriod \n" +
            "    AND DATE_FORMAT(data,'%Y-%m-%d') < :endsPeriod \n"+
            "    group by DATE_FORMAT(data,'%Y-%m-%d')", nativeQuery = true)
    List<ProductsByPeriodDTO> findProductsByLimitedPeriod(@Param("startPeriod") String startPeriod, @Param("endsPeriod") String endsPeriod);
}
