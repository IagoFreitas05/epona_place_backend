package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.CreditCard;
import main.place.entity.EntidadeDominio;
import main.place.entity.ReturnMessage;
import main.place.facade.Facade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/creditcard")

public class CreditCardController {
    private final Facade facade;

    @PostMapping
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody CreditCard creditCard){
        EntidadeDominio entity = facade.salvar(creditCard);
        if(entity instanceof ReturnMessage){
            return ((ReturnMessage) entity).getReturnMessage();
        }
        return "salvo com sucesso";
    }
}
