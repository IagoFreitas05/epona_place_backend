package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.DeliverTax;
import main.place.facade.Facade;
import main.place.repository.DeliverTaxRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/tax")
public class DeliverTaxController {
    private final Facade facade;
    private final DeliverTaxRepository deliverTaxRepository;

    @RequestMapping("returnTaxByQuantity/{quantity}")
    public DeliverTax returnByQuantity(@PathVariable Integer quantity){
        return deliverTaxRepository.findDeliverTaxByQuantityItem(quantity);
    }
}
