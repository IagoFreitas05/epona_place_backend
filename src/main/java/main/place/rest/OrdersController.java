package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.adapter.PurchaseOrderAdapter;
import main.place.dto.OrderDTO;
import main.place.entity.EntidadeDominio;
import main.place.entity.OrderItem;
import main.place.entity.PurchaseOrder;
import main.place.entity.User;
import main.place.facade.Facade;
import main.place.repository.OrderItemRepository;
import main.place.repository.PurchaseOrderRepository;
import main.place.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/order")
public class OrdersController {
    private final Facade facade;
    private final UserService userService;
    private final PurchaseOrderAdapter orderAdapter;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final OrderItemRepository orderItemRepository;

    @PostMapping
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody OrderDTO orderDTO){
        orderDTO.setIdUser(userService.getLoggedUser().getId());
        PurchaseOrder order =  orderAdapter.adapt(orderDTO);
        EntidadeDominio entityClient = facade.salvar(order);
        PurchaseOrder orderSaved = (PurchaseOrder) entityClient;

        for(int i = 0; i < orderDTO.getOrderItems().size(); i++){
            orderDTO.getOrderItems().get(i).setIdUser(orderDTO.getIdUser());
            orderDTO.getOrderItems().get(i).setIdPedido(orderSaved.getId());
            facade.salvar(orderDTO.getOrderItems().get(i));
        }
        return " ";
    }

    @GetMapping
    @CrossOrigin
    public List<EntidadeDominio> index(PurchaseOrder purchaseOrder){
        return facade.mostrarTodos(purchaseOrder);
    }

    @GetMapping("findByIdUser/{id}")
    @CrossOrigin
    public List<PurchaseOrder> findByIdUser(@PathVariable Integer id){
        return purchaseOrderRepository.findByIdUser(id);
    }

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> findById(@PathVariable Integer id, PurchaseOrder purchaseOrder){
        return facade.consultar(id, purchaseOrder );
    }

    @GetMapping("findOrderItemsByIdOrder/{id}")
    @CrossOrigin
    public List<OrderItem> findOrderItemsByIdOrder(@PathVariable Integer id){
        return orderItemRepository.findByIdPedido(id);
    }
}
