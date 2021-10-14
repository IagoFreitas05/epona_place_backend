package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.Cupon;
import main.place.entity.OrderItem;
import main.place.facade.Facade;
import main.place.repository.OrderItemRepository;
import main.place.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/OrderItem")
public class OrderItemController {
    private final Facade facade;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;

    @GetMapping("cancelItem/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelItem(@PathVariable Integer id){
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(id);
        OrderItem orderItemSaved = orderItemOptional.get();
        orderItemSaved.setStatus("cancelado");

        /*create a cupon*/
        Cupon cupon = new Cupon();
        cupon.setName(UUID.randomUUID().toString().replace('-','p'));
        cupon.setQuantity(1);
        cupon.setCountUsing(0);
        cupon.setType("DEVOLUCAO");
        cupon.setIsValid("true");
        cupon.setIdUser(orderItemSaved.getIdUser());
        cupon.setValue(orderItemSaved.getValue());

        facade.salvar(cupon);
        facade.salvar(orderItemSaved);
    }
}
