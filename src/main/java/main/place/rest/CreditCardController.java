package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.Address;
import main.place.entity.CreditCard;
import main.place.entity.EntidadeDominio;
import main.place.entity.ReturnMessage;
import main.place.facade.Facade;
import main.place.repository.CreditCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/creditCard")

public class CreditCardController {
    private final Facade facade;
    private final CreditCardRepository creditCardRepository;

    @PostMapping
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody CreditCard creditCard){
        EntidadeDominio entity = facade.salvar(creditCard);
        if(entity instanceof ReturnMessage){
            return ((ReturnMessage) entity).getReturnMessage();
        }
        return "sucesso";
    }

    @PutMapping
    @CrossOrigin
    public String alterar(@RequestBody CreditCard creditCard){
        String res = facade.alterar(creditCard);
        return res;
    }

    @DeleteMapping("{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, CreditCard creditCard ){ facade.deletar(id,  creditCard);}

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> consultarPorParametro(@PathVariable Integer id, CreditCard creditCard){
        return facade.consultar(id, creditCard);
    }

    @GetMapping()
    @CrossOrigin
    public List<EntidadeDominio> mostrarTodos(CreditCard creditCard){
        return facade.mostrarTodos(creditCard);
    }

    @GetMapping("findByUserId/{id}")
    @CrossOrigin
    public List<CreditCard> mostrarPorIdUser(@PathVariable Integer id, CreditCard creditCard){return creditCardRepository.findByIdUser(id); }
}
