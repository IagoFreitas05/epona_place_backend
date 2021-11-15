package main.place.repository;

import main.place.dto.MultipleItemsCancelDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MultipleItemsCancelDTORepository extends JpaRepository<MultipleItemsCancelDTO, Integer> {
    @Query(value = "select\n" +
            "       request_items_cancel.status,\n" +
            "       od.status as status,\n" +
            "       od.product_image,\n" +
            "       request_items_cancel.quantity,\n" +
            "       od.value as value,\n" +
            "       od.id_pedido,\n" +
            "       od.id as order_item_id,\n" +
            "       request_items_cancel.id as id\n" +
            "from request_items_cancel\n" +
            "    inner join order_item od on od.id = request_items_cancel.id_order_item\n" +
            "    where request_items_cancel.status != 'items_cancelados'\n", nativeQuery = true)
    List<MultipleItemsCancelDTO> findMultipleItemsCancelDTO();
}
