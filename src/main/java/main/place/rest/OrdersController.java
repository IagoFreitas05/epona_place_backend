package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.dto.OrderDTO;
import main.place.facade.Facade;
import main.place.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/order")
public class OrdersController {
    private final Facade facade;
    private final UserService userService;

    @PostMapping
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody OrderDTO orderDTO){


        return "sucesso";
    }
}
