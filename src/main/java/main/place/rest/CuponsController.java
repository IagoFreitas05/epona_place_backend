package main.place.rest;

import lombok.RequiredArgsConstructor;
import main.place.entity.*;
import main.place.facade.Facade;
import main.place.repository.CuponsRepository;
import main.place.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("place/cupons")
public class CuponsController {
    private final Facade facade;
    private final CuponsRepository cuponsRepository;
    private final UserService userService;

    @PostMapping
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Cupon cupons){
        cupons.setIdManager(userService.getLoggedUser().getId());
        EntidadeDominio entity = facade.salvar(cupons);
        if(entity instanceof ReturnMessage){
            return ((ReturnMessage) entity).getReturnMessage();
        }
        return "sucesso";
    }

    @PutMapping
    @CrossOrigin
    public String alterar(@RequestBody Cupon cupons){
        cupons.setIdManager(userService.getLoggedUser().getId());
        String res = facade.alterar(cupons);
        return res;
    }

    @DeleteMapping("{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, Cupon cupon ){ facade.deletar(id,  cupon);}

    @GetMapping("{id}")
    @CrossOrigin
    public Optional<EntidadeDominio> consultarPorParametro(@PathVariable Integer id, Cupon cupon){
        return facade.consultar(id, cupon);
    }

    @GetMapping()
    @CrossOrigin
    public List<EntidadeDominio> mostrarTodos(Cupon cupon){
        return facade.mostrarTodos(cupon);
    }

    @GetMapping("findByIdManager/{id}")
    @CrossOrigin
    public List<Cupon> mostrarPorIdUser(@PathVariable Integer id, Cupon cupon){return cuponsRepository.findByIdManager(id); }

    /*criar método de usar cupon*/
}
